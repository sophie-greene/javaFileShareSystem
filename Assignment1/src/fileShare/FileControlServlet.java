package fileShare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.DefaultFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 * Servlet implementation class FileControlServlet
 */
public class FileControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private FileModel file;

	private ArrayList<TagModel> tags;

	private UserModel user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			FileModel file = new FileModel();
			file.setInode(Integer.parseInt(req.getParameter("d")));
			// delete file tags from record database
			FileHasTagDAO.delete(file.getInode());
			// delete file record from database
			boolean status = FileDAO.delete(file);
			if (status) {
				// delete file physically from servers file system
				// if error occur print a msg to system
				System.out.println(Misc.FileExists(file.getFpath()
						+ file.getFname()));
				if (Misc.FileExists(file.getFpath() + file.getFname()).equals(
						""))
					Misc.deleteFile(file.getFpath() + file.getFname());
				session.setAttribute("DeleteMessage",
						"file deleted successfuly");
				resp.sendRedirect("files.jsp");
			} else {
				session.setAttribute("DeleteMessage",
						"problems encoutered deleting the file");
				resp.sendRedirect("files.jsp");
			}
		} catch (IOException e) {

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int uid = 0;
		if (request.getParameter("d") != null) {
			doDelete(request, response);
		} else {

			HttpSession session = request.getSession(true);
			try {
				if (session.getAttribute("currentSessionUser") != null) {
					UserModel user = (UserModel) session
							.getAttribute("currentSessionUser");

					// set uid to -1 if user is admin and hence all files can be
					// accessed
					if (user.getGroup().equals("admin"))
						uid = -1;
					else
						uid = user.getUid();
					ArrayList<FileModel> file = FileDAO.findAll(uid);

					session.setAttribute("allfiles", file);
					response.sendRedirect("files.jsp"); // logged-in page
				} else
					response.sendRedirect("files.jsp");
			} catch (Throwable theException) {

				System.out.println(theException);
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		file = new FileModel();
		tags = new ArrayList<TagModel>();
		// get logged user email
		HttpSession session = request.getSession(true);

		if (session.getAttribute("currentSessionUser") != null) {
			user = (UserModel) session.getAttribute("currentSessionUser");
			file.setEmail(user.getEmail());
		}
		// Check that we have a file upload request
		if (FileUpload.isMultipartContent(request)) {

			// Create a new file upload handler
			FileUpload upload = new FileUpload(new DefaultFileItemFactory());

			try {
				for (Object item : upload.parseRequest(request)) {
					processFileItem((FileItem) item);// call method to process
					// each item
					// get file info
				}
				// check if file to be uploaded exceeds the set file size

				if (file.getFsize() > Float.parseFloat((String) session
						.getAttribute("maxsize"))) {
					session.setAttribute("UploadMessage",
							"The size of the file ("
									+ (file.getFsize() / 1024)
									+ "KB) Exceeds the maximum size allowed ("
									+ Float.parseFloat((String) session
											.getAttribute("maxsize")) / 1024
									+ "KB)");
					response.sendRedirect("upload.jsp");
				} else if (file.getFname().length() == 0) {
					session.setAttribute("UploadMessage",
							"Please choose a file to upload");
					response.sendRedirect("upload.jsp");
				} else if (tags.size() == 0) {
					session.setAttribute("UploadMessage",
							"Please enter at least one tag");
					response.sendRedirect("upload.jsp");
				} else {
					file.setTupload(Misc.getDateTime());
					// store file meta data in database
					boolean result = FileDAO.upload(file);

					// store file-tag combinations in database
					FileHasTagModel tf = new FileHasTagModel();
					if (result) {
						// if upload successful store tags and file tag
						// combinations in database

						// store tags in tags database

						for (int i = 0; i < tags.size(); i++) {
							TagDAO.store(tags.get(i));
							tf.setFile(file.getFname());
							tf.setTag(tags.get(i).getTname());
							FileHasTagDAO.store(tf, user.getUid());
						}
						// store tagfile combinations
						session.setAttribute("UploadMessage",
								"You have uploaded the file " + file.getFname()
										+ " successfully");
						response.sendRedirect("upload.jsp"); // registration
						// successful

					} else {
						if (file.doesExist()) {
							session.setAttribute("UploadMessage",
									"File already exists");
						}
						response.sendRedirect("upload.jsp"); // back to upload
						// with an error
						// msg

					}
				}
			} catch (FileUploadException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	private void processFileItem(FileItem item) {
		TagModel temp1 = new TagModel();
		// Can access all sorts of useful info. using FileItem methods

		if (item.isFormField()) {
			// TODO process input field of form depending on the field name!

			if (item.getFieldName().equals("access")) {
				file.setAccess(Integer.parseInt(item.getString()));
			}
			if (item.getFieldName().equals("description")) {
				file.setDescription(item.getString());
			}

			if (item.getFieldName().contains("tag")) {
				if (item.getString().length() != 0) {
					temp1.setTname(item.getString());
					tags.add(temp1);
				}
			}
		} else {
			// Is an uploaded file, so get name & store on local filesystem

			String uploadedFileName = new File(item.getName()).getName();
			String path = "C:\\uploads" + "\\" + file.getEmail();// 
			File savedFile = new File(path + "\\" + uploadedFileName);

			file.setFname(uploadedFileName);
			file.setFpath("c:\\\\uploads\\\\" + file.getEmail() + "\\\\");
			file.setType(item.getContentType());
			file.setFsize(item.getSize());

			try {
				// for security reasons
				savedFile.setExecutable(false);
				item.write(savedFile);// write uploaded file to local storage

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// convert long to date and then store
			String temp = String.valueOf(savedFile.lastModified());
			file.setMtime(temp);
		}
	}

}

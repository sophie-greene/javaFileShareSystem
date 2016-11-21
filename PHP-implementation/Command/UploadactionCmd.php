<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UploadactionCmd
 *
 * @author Somoud
 */
require_once 'Command/Command.php';

class UploadactionCmd extends Command {

    function execute(Request $request) {

        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);

        $Auth = TRUE;
        $time = date("h:ia d/m/Y");
        $description = $_POST["description"];
        $tag1 = $_POST["tag1"];
        $tag2 = $_POST["tag2"];
        $tag3 = $_POST["tag3"];
        $tag4 = $_POST["tag4"];
        $access = $_POST["access"];
        $upload_dir = "/uploads";
        $att = $_FILES['att'];
        $att_name = $_FILES['att']['name'];
        $fmsg = "";
        $emsg = "";
        $umsg = "";
        if ($att_name != "") {
            if (($_FILES["att"]["size"] < 10000000)) {
                if ($_FILES["att"]["error"] > 0) {
                    $umsg = "Return Code: " . $_FILES["att"]["error"] . "<br />";
                } else {

//require_once 'Model/loggedSinglton.php';
                    $file_obj = array(
                        'Fname' => $att_name,
                        'Tupload' => $time,
                        'Fpath' => '/uploads',
                        'access' => $access
                    );
                    try {
                        require_once 'Model/UserDAO.php';
                        $DAO = new UserDAO();
                        $res = $DAO->write($file_obj, "file");

//upload successfull
                        if ($res) {

//upload in
//  print $upload_dir.$_FILES["att"]["name"];


                            if (file_exists($upload_dir . $_FILES["att"]["name"])) {
                                $umsg = $_FILES["att"]["name"] . " already exists. ";
                            } else {

                                move_uploaded_file($_FILES["att"]["tmp_name"],
                                        $upload_dir . $_FILES["att"]["name"]);
                            }


                            $request->addMessage("Your file " . $_FILES["att"]["name"] . " has been uploaded");
                            //update file list
                            $res = $DAO->findAll('file');

                            $request->setObject($res);
                            $request->setProperty("view", "filesView.php");
                        } else {
                            $request->addMessage("could not upload");
                            $request->setProperty('view', 'errorView.php');
                        }
                    } catch (Exception $e) {
                        $request->addMessage($umsg);
                        $request->setProperty('view', 'errorView.php');
                    }
                }
            }
        } else {
            $umsg = "Invalid file";
        }

      
    }

}

?>

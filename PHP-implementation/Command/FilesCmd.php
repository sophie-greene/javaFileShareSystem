<?php
/**
 * Description of RegisterCmd
 *
 * @author somoud
 */
require_once 'Command/Command.php';
class FilesCmd extends Command{
 function execute(Request $request) {

        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);
        //get files from database
        try {
            require_once 'Model/UserDAO.php';
            $DAO = new UserDAO();
            $res = $DAO->findAll('file');
            
            $request->setObject($res);
            $request->setProperty('view', 'filesView.php');
        }catch(Exception $e){

        }
        
    }
}
?>

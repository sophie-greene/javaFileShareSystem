<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UploadCmd
 *
 * @author Somoud
 */
require_once 'Command/Command.php';
class UploadCmd  extends Command{

     function execute(Request $request) {

        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);


        $request->setProperty('view', 'uploadView.php');
    }

}
?>

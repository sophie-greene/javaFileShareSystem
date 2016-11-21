<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of errorCmd
 *
 * @author somoud
 */
require_once 'Command/Command.php';
class ErrorCmd extends Command{

     function execute(Request $request) {

        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);
        $request->setProperty('view', 'errorView.php');
    }

}
?>

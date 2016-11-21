<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of LogoutCmd
 *
 * @author Somoud
 */
require_once 'Command.php';
class LogoutCmd extends Command {

    function execute(Request $request) {
        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);
        $request->setProperty("user", "notlogged");
        $request->setProperty("view", "indexView.php");
    }

}

?>

<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of registerCmd
 *
 * @author somoud
 */
require_once 'Command.php';

class RegisterCmd extends Command {

    function execute(Request $request) {
        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);

        //get data from http post and validate before store
        if ($_POST['email'] != "") {

            if ($_POST['password'] != "" && $_POST['password'] == $_POST['cpassword']) {

                $user_obj = array(
                    'Email' => $_POST['email'],
                    'Password' => $_POST['password'],
                    'Firstname' => $_POST['firstname'],
                    'Lastname' => $_POST['lastname']);
                try {
                    require_once 'Model/UserDAO.php';
                    $DAO = new UserDAO();
                    $res = $DAO->write($user_obj, "user");
                    //login successfull
                    if ($res) {
                        //log in successful
                        $request->addMessage("you have been registered successfully");
                        $request->setProperty("view", "indexView.php");
                    } else {
                        $request->addMessage("could not register");
                        $request->setProperty('view', 'indexView.php');
                    }
                } catch (Exception $e) {
                    $request->addMessage("exception occured");
                    $request->setProperty('view', 'errorView.php');
                }
            } else {
                $request->addMessage("passwords dont match");
                $request->setProperty('view', 'indexView.php');
            }
        } else {
            $request->addMessage("you much provide email");
            $request->setProperty('view', 'indexView.php');
        }
    }

}

?>

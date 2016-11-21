<?php

session_start();
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of ProcessLoginCmd
 *
 * @author Somoud
 */
require_once 'Command.php';

class LoginCmd extends Command {

    function execute(Request $request) {
        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);
        //get data from http post

        if (isset($_POST['name'])) {
            $user = $_POST['name'];
            $pass = $_POST['password'];

            try {
                require_once 'Model/UserDAO.php';
                $DAO = new UserDAO();
                $user_obj = $DAO->login($user, $pass);
                
                //print_r($user_obj);
                //login successfull
                if ($user_obj == "success") {
                    //log in successful
                    $request->setProperty("user", "logged");
                    $request->setProperty("view", "filesView.php");
                } else {
                    $request->addMessage( $user_obj);
                    $request->setProperty('view', 'indexView.php');
                }
            } catch (Exception $e) {
                $request->addMessage( $user_obj);
                $request->setProperty('view', 'errorView.php');
            }
        }else{
            $request->setProperty('view', "indexView.php");
        }
    }

}

?>

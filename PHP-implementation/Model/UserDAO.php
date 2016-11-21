<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UserDAO
 * implements the interface IUserDAO
 * contains all functions needed to access the details of class UserC
 * @author somoud
 */
require_once 'IDAO.php';

class UserDAO implements IDAO {

    public function write($rec, $table) {
        require_once '/home/leedstec/public_html/patternsfront/Model/FDB.php';

        if ($table == "user") {
            $sql = 'INSERT INTO ' . $table . ' (email ,password ,firstname ,lastname,fk1_gid)VALUES("'
                    . $rec['Email'] . '","' . $rec['Password'] . '","' .
                    $rec['Firstname'] . '","' . $rec['Lastname'] . '",2)';
        } else if ($table == "file") {
            $sql = 'INSERT INTO '.$table.' ( fname,fpath, access, tupload)VALUES("'
                    . $rec['Fname'] . '","' . $rec['Fpath'] . '",' .
                    $rec['access'] . ',"' . $rec['Tupload'] . '")';
        }

        $preparedStmt = FDB::prepare($sql);
        return $preparedStmt->execute();
    }

    public function update($rec, $table) {

    }

    public function find($id, $table) {
       require_once '/home/leedstec/public_html/patternsfront/Model/FDB.php';
        $sql = 'SELECT * FROM $table WHERE id = $id';
        $preparedStmt = FDB::prepare($sql);
        $preparedStmt->execute(array($id));
        return $preparedStmt->fetch();
    }

    public function findAll($table) {
       require_once '/home/leedstec/public_html/patternsfront/Model/FDB.php';
        $sql = 'SELECT * FROM ' . $table;
        $preparedStmt = FDB::prepare($sql);
        $preparedStmt->execute();

        return $preparedStmt->fetchAll();
    }

    function login($user, $pass) {
        $msg = -199383;
        if (($user) != "") {

            //validate email format
            if (preg_match('/^[^@]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$/', $user)) {

                if (($pass) != "") {

                    //connect to database through singleton
                   require_once '/home/leedstec/public_html/patternsfront/Model/FDB.php';
                    $sql = "SELECT * from user WHERE email='$user' AND password='$pass'";
                    $preparedStmt = FDB::prepare($sql);
                    try {
                        $status = $preparedStmt->execute();
                    } catch (Exception $e) {
                        $msg = "invalid user/pass comb";
                    }


                    $result = $preparedStmt->fetch();
                    //double check user/pass combination exists
                    if ($user == $result['email'] && $pass = $result['password'])
                        $msg = "success";
                    //check if any records found i.e user/password match

                    /* define userc object to store user details if user and pass
                     * match a combination in the database
                     */
                    // print_r($result);

                    require_once '/home/leedstec/public_html/patternsfront/Model/loggedSinglton.php';
                    loggedSinglton::setUid($result['uid']);
                    loggedSinglton::setEmail($result['email']);
                    loggedSinglton::setFirstname($result['firstname']);
                    loggedSinglton::setLastname($result['lastname']);
                    loggedSinglton::setValid(true);
                    loggedSinglton::setExists(true);
                    //get group using the gid foriegn key
                    $temp = $result['fk1_gid'];
                    $sql = "SELECT * from cat WHERE gid=$temp";
                    $preparedStmt = FDB::prepare($sql);
                    try {
                        $status = $preparedStmt->execute();
                    } catch (Exception $e) {
                        $msg = "invalid user/pass comb";
                    }

                    $result = $preparedStmt->fetch();
                    loggedSinglton::setGroup($result['gname']);
                } else {
                    $msg = "empty password field";
                }
            } else {
                $msg = "invalid email format someone@example.com";
            }
        } else {
            $msg = "empty email field";
        }
        return $msg;
    }

}

?>

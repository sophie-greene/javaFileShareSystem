<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Forum
 *
 * @author Somoud
 */
class ForumModel {

    private $dbXML;

    function __construct() {

        $xmlfile = '/home/leedstec/public_html/patternsfront/Model/Forum.xml';
        if (file_exists($xmlfile)) {
            $this->dbXML = simplexml_load_file($xmlfile);
            // print_r($this->dbXML); // uncomment for debugging
        } else {
            throw new Exception("Invalid File name or path");
        }
    }

    public function getSubjectMenu() {
        //print_r($this->dbXML[0]);
        return $this->dbXML[0];
    }
    public function getMessages($subjectNumber) {

        return $this->dbXML->subject[$subjectNumber]->messages;
    }

}

?>

<?php

/**
 * Description of Request
 * helper class for Request as well as application data
 * @author somoud
 */
class Request {

    private $properties = array();
    private $messages = array();
    private $object=array();
    function __construct() {

        // we are using the request to also contain any view data
        $this->iniDispatchData();

        if ($_SERVER['REQUEST_METHOD']) {
            $this->properties = $_REQUEST;
            return;
        }

        if (isset($_SERVER['argv'])) {
            foreach ($_SERVER['argv'] as $arg) {
                if (strpos($arg, "=")) {
                    list( $key, $val ) = explode("=", $arg);
                    $this->setProperty($key, $val);
                }
            }
        }
    }

    function getProperty($key) {
        return $this->properties[$key];
    }

    function setProperty($key, $val) {
        $this->properties[$key] = $val;
    }

    function issetCmd() {
        return array_key_exists('cmd', $this->properties) ? true : false;
    }

    /*
     * Being a general request object we can include any
     * non http request parameters, for example the main application menu
     * the same request object can then be used to pass the parameters to the view
     */

    function iniDispatchData() {
        /**
         * get application wide main navigation menu
         */
        require_once  '/home/leedstec/public_html/patternsfront/Model/ForumModel.php';
        $forum = new ForumModel();
        $this->setProperty('mainMenu', $forum->getSubjectMenu());
        $this->setProperty('messages', $forum->getMessages(1));
        require_once 'Model/UserDAO.php';
            $DAO = new UserDAO();
            $res = $DAO->findAll('file');
            
            $this->setObject($res);
       
    }

// add a message element to the end of messages array
    function addMessage($msg) {
        array_push($this->messages, $msg);
    }

//gets the last message added to the messages array
    function getMessage() {
        return array_pop($this->messages);
    }
    function setObject($obj){
        $this->object=$obj;
    }
   
    function getObject() {
        return $this->object;
    }
}

?>

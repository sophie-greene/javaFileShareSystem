<?php

/*
 * Description of ForumFrontController
 *
 * @author somoud
 */

class ForumFrontController {

    /**
     * initialise application
     * handle client request
     */
    private $request;

    private function __construct() {

    }

    /**
     * the static run method and the private constructor ensures
     * the application can only run through this method
     */
    static function run() {

        $instance = new ForumFrontController();

        $instance->init();

        $instance->handleRequest();
    }

    /**
     * initialise the application
     */
    private function init() {

        require_once '/home/leedstec/public_html/patternsfront/Controller/Request.php';
        $this->request = new Request();
    }

    private function handleRequest() {

        require_once '/home/leedstec/public_html/patternsfront/Controller/CommandHandler.php';

        $cmdHandler = new CommandHandler();      
        $cmd = $cmdHandler->getCmd($this->request);
        $cmd->execute($this->request);


        $this->invokeView($this->request);
    }

    private function invokeView(Request $request) {
        $view = $request->getProperty('view');

        require_once "/home/leedstec/public_html/patternsfront/View/" . $view;
        exit;
    }

}

?>

<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of loggedSinglton
 *
 * @author somoud
 */
class loggedSinglton {
    private static $userInstance = null;

    protected final function __construct() {}

    public function __clone() {
        trigger_error('This is a singleton, __clone is not allowed.', E_USER_ERROR);
    }

    public function __wakeup() {
        trigger_error('This is a singleton, __wakeup is not allowed.', E_USER_ERROR);
    }

    private static function getInstance() {
        // a singleton, so, do this if no existing instance
        if (!self :: $userInstance instanceof self) {
            self::newSelf();
        }
        return self :: $userInstance;
    }

    /*
     * Overloading method using __callStatic magic method
     * allows access UserC methods dynamically
     */

    final public static function __callStatic($method, $args) {

        $Instance = self::getInstance();
        return call_user_func_array(array($Instance, $method), $args);
    }

    protected static final function newSelf() {


        // set class instance ($userInstance) to a new userC instance
        require_once '/home/leedstec/public_html/patternsfront/Model/UserC.php';
        self::$userInstance = new userC();

    }


}
?>

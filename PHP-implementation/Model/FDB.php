<?php

class FDB {

    private static $pdoInstance = null;

    protected final function __construct() {

    }

    public function __clone() {
        trigger_error('This is a singleton, __clone is not allowed.', E_USER_ERROR);
    }

    public function __wakeup() {
        trigger_error('This is a singleton, __wakeup is not allowed.', E_USER_ERROR);
    }

    private static function getInstance() {
        // a singleton, so, do this if no existing instance
        if (!self :: $pdoInstance instanceof self) {
            self::newSelf();
        }
        return self :: $pdoInstance;
    }

    /*
     * Overloading method using __callStatic magic method
     * allows access PDO methods dynamically
     */

    final public static function __callStatic($method, $args) {
        // for debugging use an IDE :)
        echo "Calling static method '$method' " . implode(', ', $args) . "\n";
        $dbInstance = self::getInstance();
        return call_user_func_array(array($dbInstance, $method), $args);
    }

    protected static final function newSelf() {
        // read DB config parameters from .ini file
        $ini = "db.ini";
        $parse = parse_ini_file($ini, true);
        $driver = $parse ["db_driver"];
        $dsn = "${driver}:";
        $user = $parse ["db_user"];
        $password = $parse ["db_password"];
        $options = $parse ["db_options"];
        $attributes = $parse ["db_attributes"];
        foreach ($parse ["dsn"] as $k => $v) {
            $dsn .= "${k}=${v};";
        }

        try {
            // set class instance ($pdoInstance) to a new PDO instance
            self::$pdoInstance = new PDO($dsn, $user, $password, $options);
            // set any PDO parameters specified in the .ini file
            foreach ($attributes as $k => $v) {
                self :: $pdoInstance->setAttribute(constant("PDO::{$k}")
                        , constant("PDO::{$v}"));
            }
        } catch (Exception $e) {

        }
    }

}

?>
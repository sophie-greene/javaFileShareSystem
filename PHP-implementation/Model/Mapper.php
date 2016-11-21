<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Mapper
 *
 * @author somoud
 */
abstract class Mapper {

    protected static $PDO;

    function __construct() {
        if (!isset(self::$PDO)) {
            $dsn = \woo\base\ApplicationRegistry::getDSN( );
            if (is_null($dsn)) {
                throw new \woo\base\AppException("No DSN");
            }
            self::$PDO = new \PDO($dsn);
            self::$PDO->setAttribute(\PDO::ATTR_ERRMODE, \PDO::ERRMODE_EXCEPTION);
        }
    }

    function find($id) {
        $this->selectStmt()->execute(array($id));
        $array = $this->selectStmt()->fetch();
        $this->selectStmt()->closeCursor();
        if (!is_array($array)) {
            return null;
        }
        if (!isset($array['id'])) {
            return null;
        }
        $object = $this->createObject($array);
        return $object;
    }

    function createObject($array) {
        $obj = $this->doCreateObject($array);
        return $obj;
    }

    function insert(\woo\domain\DomainObject $obj) {
        $this->doInsert($obj);
    }

    abstract function update(\woo\domain\DomainObject $object);

    protected abstract function doCreateObject(array $array);

    protected abstract function doInsert(\woo\domain\DomainObject $object);

    protected abstract function selectStmt();
}

?>

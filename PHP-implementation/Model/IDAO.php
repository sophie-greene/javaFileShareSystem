<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of IUserDAO
 *
 * @author somoud
 */
interface IDAO {
    public function write($rec,$table);
    public function update($rec,$table);
    public function find($id,$table);
    public function findAll($table);
    public function login($user,$pass);
}
?>

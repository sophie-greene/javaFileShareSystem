<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UserC
 *
 * @author somoud
 */
class UserC {
   	private $uid;
	private $email;
	private $password;
	private $firstname;
	private $lastname;
	private $group;
	private $valid;
	private $exists;

	/**
	 * @return the exists
	 */
	public function doesExist() {
		return $exists;
	}

	/**
	 * @return the email
	 */
	public function getEmail() {
		return $email;
	}

	/**
	 * @return the firstname (String)
	 */
	public function getFirstname() {
		return $firstname;
	}

	/**
	 * @return the group
	 */
	public function getGroup() {
		return $group;
	}

	/**
	 * @return the lastname
	 */
	public function getLastname() {
		return $lastname;
	}

	/**
	 * @return the password
	 */
	public function getPassword() {
		return $password;
	}

	/**
	 * @return the uid
	 */
	public function getUid() {
		return $uid;
	}

	/**
	 * @return the valid
	 */
	public function isValid() {
		return $valid;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public function setEmail( $email) {
		$this->email = $email;
	}

	/**
	 * @param exists
	 *            the exists to set
	 */
	public function setExists( $exists) {
		$this->exists = $exists;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public function setFirstname($firstname) {
		$this->firstname = $firstname;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public function setGroup($group) {
		$this->group = $group;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public function setLastname($lastname) {
		$this->lastname = $lastname;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public function setPassword($password) {
		$this->password = $password;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public function setUid($uid) {
		$this->uid = $uid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public function setValid($valid) {
		$this->valid = $valid;
	}

}
?>

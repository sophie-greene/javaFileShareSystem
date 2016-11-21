<?php

/**
 * Description of Command
 *
 * @author somoud
 */
abstract class Command {

    final function __construct() {}

    abstract function execute( Request $request );
}
?>

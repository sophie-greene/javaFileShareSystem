<?php

/**
 * Description of CommandHandler
 *
 * @author somoud
 */
class CommandHandler {

    function getCmd(Request $request) {

        if (!$request->issetCmd()) {
            $request->setProperty('cmd', 'index');
        }
         

        $class = ucfirst(strtolower($request->getProperty('cmd'))) . 'Cmd';
        //check for invalid cmd input and raise exception if illegal name used
        if ( preg_match( '[0-9a-zA-Z_\-]', $class ) ) {
            throw new Exception("illegal characters in command name");
        }
        $file = ' /home/leedstec/public_html/patternsfront/Command/'.$class . ".php";
            if (!file_exists($file)) {
                $request->addMessage("'$file' not found");
                $request->setProperty('view', 'error');
                $request->setProperty('cmd', 'error');
                $file = "Command/ErrorCmd.php";
                $class = "ErrorCmd";
            }
                require_once  $file;
                $cmd = new $class();
                if (!class_exists($class)) {
                    $request->addMessage("'$class' not found ");
                    $request->setProperty('view', 'error');
                }
        return $cmd;
    }

}

?>

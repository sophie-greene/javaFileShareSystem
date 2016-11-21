<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of indexCmd
 *
 * @author somoud
 */
require_once 'Command/Command.php';
class IndexCmd extends Command{
    function  execute(Request $request) {
        
        require_once 'Model/ForumModel.php';
        $forum=new ForumModel();
        $subjects=$forum->getSubjectMenu();
        $request->setProperty('mainMenu',$subjects);
        $request->setProperty('view','indexView.php');
    }
}
?>

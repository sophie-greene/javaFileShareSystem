<?php
/**
 * Description of MessagesCmd
 *
 * @author somoud
 */
require_once 'Command/Command.php';
class MessagesCmd extends Command{

     function execute(Request $request) {

        require_once 'Model/ForumModel.php';
        $forum = new ForumModel();
        $subjects = $forum->getSubjectMenu();
        $request->setProperty('mainMenu', $subjects);
        $messages = $forum->getMessages(2);
        $request->setProperty('messages', $messages);

        $request->setProperty('view', 'messagesView.php');
    }

}
?>

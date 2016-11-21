<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>File Sharing Web Site</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/global.css" type="text/css" />
    </head>
    <body>
        <div id="wrapper">
            <div id="header">

                <div class="search">
                    <form action="searchActionController.php">
                        <fieldset>
                            <label for="search">Search</label>
                            <input id="search" name="search" type="text" />
                            <input id="submit" value="search" type="submit" class="submit_button"/>
                        </fieldset>
                    </form>
                </div><!--search div ends!-->

                <div class="login">
                    <?php echo "TODO: display User "; ?><a href="index.php?cmd=logout">logout</a>
                </div><!--login div ends!-->
                <div class="logo">
                    <h1>File Sharing Web Site</h1>
                    <h2>Messages</h2>
                </div><!--logo div ends!-->
            </div><!-- #header-->
            <div id="mainMenu">
                <?php $mainMenu = $request->getProperty('mainMenu') ?>
                <ul>
                    <?php foreach ($mainMenu as $menuItem): ?>
                        <li> <a href= "<?php echo $menuItem->url; ?>" >
                            <?php echo $menuItem->name; ?></a></li>
                    <?php endforeach; ?>
                        </ul>
                    </div><!--mainMenu div ends!-->
                    <div id="middle">
                        <div id="container">
                            <div id="content">
                                <h1>Welcome</h1>
                                <ul>
                            <?php $messages = $request->getProperty('messages'); ?>
                            <?php $count = 0; ?>
                            <?php foreach ($messages->message as $message): ?>
                                <li class="<?php
                                if ($count % 2 == 0)
                                    echo'meven';
                                else
                                    echo 'modd';$count = $count + 1;
                            ?>">
                                <h2> <?php echo $message->title; ?></h2>
                                <p>  <?php echo $message->content; ?></p>
                                <p>  <?php
                                    echo $message->user;
                                    echo '&nbsp;' . $message->time;
                                    echo '&nbsp;' . $message->date
                            ?></p>
                                    </li>
                        <?php endforeach; ?>
                        </ul>
                    </div><!-- #content-->
                </div><!-- #container-->
            </div><!-- #middle-->

            <div id="footer">
                <div id="copyright">&copy; Somoud Saqfelhait</div>
            </div><!-- #footer -->
        </div><!-- #wrapper -->

    </body>
</html>

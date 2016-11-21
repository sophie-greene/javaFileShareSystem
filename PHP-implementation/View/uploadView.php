<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Files</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/global.css" type="text/css" />
        <script type="text/javascript" src="scripts/jquery.js"></script>
        <script type="text/javascript">
            jQuery(document).ready( function() {
                jQuery(".panel").hide();
                //toggle the componenet with class msg_body
                jQuery(".head").click( function() {
                    jQuery(this).next(".panel").slideToggle(0);
                });
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">

                <div class="search">
                    <form action="index.php?cmd=search">
                        <fieldset>
                            <label for="search">Search</label>
                            <input id="search" name="search" type="text" />
                            <input id="submit" value="search" type="submit" class="submit_button"/>
                        </fieldset>
                    </form>
                </div><!--search div ends!-->

                <div class="login">
                    <a href="index.php?cmd=logout">logout</a>
                </div><!--login div ends!-->
                <div class="logo">
                    <h1>File Sharing Web Site</h1>
                    <h2>Files</h2>
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
                        <h1>Upload</h1>
                        <div class="upload">
                            <form method="post" enctype="multipart/form-data" action="index.php?cmd=uploadaction" >
                                <fieldset>
                                    <ul>

                                        <li><label for='file'>Select a file:</label>
                                            <input id="att" type="file" name="att" value=""/>
                                            <input type="hidden" name="MAX_FILE_SIZE" value="100000000" />

                                        </li>
                                        <li><label for='description'>Description:</label>
                                            <textarea rows="10" cols="10" name="description" ></textarea>
                                        </li>
                                        <li><label>Tags:</label>
                                            <input type="text" name="tag1"  id="tag1"/><br/>
                                            <input type="text" name="tag2"  id="tag2" /><br/>
                                            <input type="text" name="tag3"  id="tag3"/><br/>
                                            <input type="text" name="tag4"  id="tag4"/><br/>
                                        </li>
                                        <li>
                                            <label for="access">File Access Rights</label>
                                            <select name="access" id="access">
                                                <option value="777">public full access</option>
                                                <option value="770">group access</option>
                                                <option value="700" selected="selected">private(only me)</option>
                                            </select>
                                        </li>
                                        <li>
                                            <input type="hidden" name="process" value="true" />
                                            <input type='submit' value='Upload' class='submit_button' />

                                        </li>
                                    </ul>

                                </fieldset>
                            </form>
                        </div>
                    </div><!-- #content-->
                </div><!-- #container-->
            </div><!-- #middle-->

            <div id="footer">
                <div id="copyright">&copy; Somoud Saqfelhait</div>
            </div><!-- #footer -->
        </div><!-- #wrapper -->
    </body>
</html>

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
                                <h1>Files</h1>
                        <?php
                            $files = $request->getObject();
                            //print_r($files);
                        ?>

                        <?php foreach ($files as $file): ?>
                                <div class="head"><li><?php echo $file['fname']; ?></li></div>
                                <div class="panel">
                                    <ul>
                                        <li>
                                            <a href="<?php echo $file['fpath'].$file['fname'] ?>">Download File</a></li>
                                        <form action="index.php?cmd=deleteFile" method="post">
                                            <input type="hidden" name ="d" id="d" value=<?php echo $file["inode"];?> />
                                            <div class="head"><li ><?php $file['fname']; ?></li></div><input type="submit" name="submit" value="Delete" />
                                        </form>

                                        <li>size:<?php echo ($file['size'] /1024); ?>KB</li>
                                        <li>upload Time: <?php echo $file['tupload'] ?></li>
                                        <li>	Access:
                                    <?php
                                    if ($file['access'] == 700) {
                                        echo 'Private';
                                    } else if ($file['access'] == 770) {
                                        echo "Group";
                                    } else {
                                        echo "Public";
                                    }
                                    ?>
                                </li>
                            </ul>

                        </div>
<?php endforeach; ?>
                    </div><!-- #content-->
                </div><!-- #container-->
            </div><!-- #middle-->

            <div id="footer">
                <div id="copyright">&copy; Somoud Saqfelhait</div>
            </div><!-- #footer -->
        </div><!-- #wrapper -->
    </body>
</html>

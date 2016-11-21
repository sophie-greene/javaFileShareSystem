<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Error</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/global.css" type="text/css" />
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
                    <?php echo "TODO:loggedUser" ;?><a href="index.php?cmd=logout">logout</a>
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
                            <h1>Error</h1>
                            <p>An error has occurred </p>
                            <p><?php  echo ($request->getMessage());?></p>
			</div><!-- #content-->
		</div><!-- #container-->
	</div><!-- #middle-->

	<div id="footer">
            <div id="copyright">&copy; Somoud Saqfelhait</div>
        </div><!-- #footer -->
    </div><!-- #wrapper -->

    </body>
</html>

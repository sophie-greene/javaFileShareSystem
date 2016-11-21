<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Mental Health and Housing Forum</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/global.css" type="text/css" />
        <link rel="stylesheet" href="css/thickbox.css" type="text/css" />
        <script type="text/javascript" src="scripts/jquery.js"></script>
        <script type="text/javascript" src="scripts/thickbox.js"></script>
    </head>

    <body>
        <div id="wrapper">
            <div id="middle">
                <div id="container">
                    <div class="logoI">
                        <h1>File Sharing Web site</h1>
                        <h2>Touchstone</h2>
                        <p><?php  echo ($request->getMessage());?></p>
                    </div><!--logo div ends!-->
                    

                    <div class="loginI">
                        <form method='post' action='index.php?cmd=login' >
                            <fieldset>
                                <ul>
                                    <li>Please Enter you email and password </li>
                                    <li>
                                        If you don't have an account
                                        <a  class="thickbox" title="Registeration form"href="#TB_inline?height=200&width=400&inlineId=thick" >Register</a>
                                    </li>
                                    <li>
                                        <label for='name'>Email:</label>
                                        <input id='name' type='text' name='name'/>
                                    </li>
                                    <li>
                                        <label for='password'>Password:</label>
                                        <input id='password' type='password' name='password' />
                                    </li>
                                </ul>
                                <input type='submit' value='Login' class='submit_button' />
                            </fieldset>
                        </form>
                    </div>
                    <div id="thick"
                         <div class="registerI">
                            <form action='index.php?cmd=register' method="post">
                                <fieldset>
                                    <ul>
                                        <li>Please enter your details to register</li>
                                        <li>
                                            <label for='email'>Email:</label>
                                            <input id='email' type='text' name='email' />
                                        </li>
                                        <li>
                                            <label for='password'>Password:</label>
                                            <input id='password' type='password' name='password'/>

                                        </li>
                                        <li>
                                            <label for='cpassword'>Confirm Password:</label>
                                            <input id='cpassword' type='password' name='cpassword' />

                                        </li>
                                        <li>
                                            <label for='firstname'>First Name:</label>
                                            <input id='firstname' type='text' name='firstname'/>
                                        </li>
                                        <li>
                                            <label for='lastname'>Last Name:</label>
                                            <input id='lastname' type='text' name='lastname'/>
                                        </li>
                                        <input type='submit' value='Register' class='submit_button' />
                                    </ul>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
        </div><!-- #wrapper -->

    </body>
</html>

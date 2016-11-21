<?PHP
    header('content-type:text/css');

    // get the c parameter and ensure that it contains .css
 
    $c=$_GET['c'];
    if(preg_match('/\//',$c) or !preg_match('/.css/',$c))
    {
            die('only local CSS files allowed!');
            exit;
    }

    // load the content of the CSS file into the variable css, end if the
    // file wasn't found.
    $css=load($c);
    if($css=='')
    {
            die('File not Found, sorry!');
            exit;
    }
    // grab all constants and store them in the array constants
    preg_match_all("/\\$(\w+).*=.*\'(.*)\'/",$css,$constants);
    for($i=0;$i<sizeof($constants[1]);$i++)
    {

    // replace all occurrences of the contants with their values
            $css=preg_replace('/\\$'.$constants[1][$i].'/',$constants[2][$i],$css);
    }

    // delete all constant definitions
    $css=preg_replace("/\\#.*=.*?;\s+/s",'',$css);

    // print out the style sheet
    echo $css;

    function load($filelocation)
    {
            if (file_exists($filelocation))
            {
                    $newfile = fopen($filelocation,"r");
                    $file_content = fread($newfile, filesize($filelocation));
                    fclose($newfile);
                    return $file_content;
            }
    }
?>



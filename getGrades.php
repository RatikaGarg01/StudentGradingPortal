<?php
session_start();

include("include/tablegear.php");
include("include/grading.php");
$gardesheet=new Grading();

if($_SESSION['category']!="faculty")
        header('Location:index.php');

$options = array();
$options["database"] = array();
$options["pagination"] = array();

$options["database"]["name"]     = "studentgrading";
$options["database"]["username"] = "root";
$options["database"]["password"] = "";
$options["database"]["table"]    = "currently_courses";

$table = new TableGear($options);

if (isset($_POST['courses'])) 
  $_SESSION['courses_f']=$_POST['courses'];

$field=$_SESSION['courses_f'];
$fetch=$gardesheet->generateQuery($field);
if(!($row = mysql_fetch_array($fetch))){
    header('Refresh: 1;url=faculty.php');;
  //die("<script>alert('No students registerd for this course')</script>");
    die("no student registerd");
}


$table->fetchData("SELECT * FROM currently_courses WHERE course_id='$field'");
//echo $_POST['courses'];
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Grade Sheet </title>
  <script type="text/javascript" src="js/mootools-yui-compressed.js"></script>
  <script type="text/javascript" src="js/tablegear-mootools.js"></script>
  
  <link type="text/css" rel="stylesheet" href="css/_style.css" />
  </head>
<body>
  <h1>Course Name: <?php echo $field?></h1>
  <div>
    <?php $table->getTable() ?>
  </div>
<?php $table->getJavascript('mootools');
?>
<form name="get-total" method="post" >                 
  <input type="submit" name="gettotal-submit" value="Calculate Total Marks"/>
</form>
<?php

  if (!empty($_POST['gettotal-submit']))
  {
    $gardesheet->calculateTotalMarks($_SESSION['courses_f']);
    header('Location: ' . basename($_SERVER['PHP_SELF']));
  }
?>
<form name="get-grades" method="post" >
  <select name="grades" value="Grade List" placeholder="Grades">
    <option value='liberal' selected>Liberal Grading</option>
    <option value='medium' >Medium Grading</option>
    <option value='strict' >Strict Grading</option>
  </select>
<input type="submit" name="getgrades-submit" value="Calculate Grades" _/>
</form>
<form name="get-gradeschart" method="post">
  <input type="submit" name="getgrades-chart" value="Grades Chart"/>
</form>
  <?php

  if (!empty($_POST['getgrades-chart']))
  {
    //header('Location: charts/chartpie.php');
    echo"<script>NewWindow=window.open('charts/chartpie.php','newWin','width=510,height=280,left=0,top=0,toolbar=No,location=No,scrollbars=No,status=No,resizable=No,fullscreen=No');
    NewWindow.focus();</script>";
  }
?>
</body>
</html>

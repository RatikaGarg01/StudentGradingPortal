<!DOCTYPE html>
<?php
  session_start();

$myusername=$mypassword="";
  include("include/controller.php");
  $msg="";
  $control=new Controller();


  if($_SERVER["REQUEST_METHOD"] == "POST")
  {
    $myusername=$control->correctInput($_POST['login']);
    $mypassword=$control->correctInput($_POST['password']); 
    if (!(empty($myusername)||empty($mypassword)))
    {
      $msg=$control->login($myusername,$mypassword,$_POST["Category"]);
      $_SESSION['category'] = $_POST["Category"];
      $_SESSION['login']= $myusername;
      $_SESSION['password']= $mypassword;
    }
    else
      $msg="Invalid Username or Password";
  }
  elseif(isset($_SESSION['login']))
    $control->login( $_SESSION['login'],$_SESSION['password'],$_SESSION['category']);


?>
<head>
  <title>Student Grading Portal</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <table cellpadding="0" cellspacing="0" width="100%" align="center" >
    <tr>
            <td style="width: 7%">
            </td>
            <td style="width: 86%">
                <img alt="" src="images/Header.jpg" width="100%">
            </td>
            <td style="width: 7%">
            </td>
        </tr>
  </table>
  <section class="container" >
    <div class="login">
      <h1>Login to StudentGradingPortal</h1>
      <form method="post" action="<?php echo $_SERVER["PHP_SELF"];?>">
        <p>
          <select name="Category" value="" placeholder="Category">
            <option value="users">Admin</option>
            <option value="student">Student</option>
            <option value="faculty">Faculty</option>
            
          </select>
        </p>
		    <p>
          <input type="text" name="login" value="" placeholder="Username"></p>
        <p>
          <input type="password" name="password" value="" placeholder="Password"></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me">
            Remember me
          </label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Login"></p>
        <p style="color:red"><?php echo $msg;?>
      </form>
    </div>

    
  </section>
</body>
</html>
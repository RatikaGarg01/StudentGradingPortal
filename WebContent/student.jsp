<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty</title>
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

  <link  href="/automatedgradingsystem/css/normalize.css"  rel="stylesheet" type="text/css">

    <link href="/automatedgradingsystem/css/style.css" rel="stylesheet" type="text/css">

<style>


#student_box
{
	left:10%;
	position: absolute;
	top: 30%;
}
#login_As
{
 position: relative;
 top: 30%;
 left: 22%;
}

#lnm_Logo{
 position: absolute;
 top: 0%;
 left: 86%;
}
.form{
	position: absolute;
	top: 10%;
	left:50%;
}


</style>

</head>
<body bgcolor="#323a45">

<img src="/automatedgradingsystem/images/main2.JPG" width="900" height="180" alt="Home Page" usemap="#home_elements">
<img id="student_box" src="/automatedgradingsystem/images/student box.JPG" width ="500" height="430" >
<img id="lnm_Logo" src="/automatedgradingsystem/images/lnm.JPG" width ="175" height="100" usemap="#LnmElements">

<map name="home_elements">
  <area shape="rect" coords="0,115,150,170" href="/automatedgradingsystem/home.jsp">
  <area shape="rect" coords="150,115,325,170" href="/automatedgradingsystem/how_to_use.jsp">
  <area shape="rect" coords="325,115,500,170"  href="/automatedgradingsystem/team.jsp">
</map>

<map name="LnmElements">
  <area shape="rect" coords="00,400,150,550" alt="Logo_lnmiit" href="www.lnmiit.ac.in">
</map>

<script>
function divert_to_login()
{
  alert("Successful Signup! Login Now!! ")
}

</script>

<div class="form" id="form_student">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          
          
          <form action="check3" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                First Name<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="firstname" />
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req">*</span>
              </label>
              <input type="text"required autocomplete="off" name="lastname"/>
            </div>
          </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email"required autocomplete="off" name="email_id"/>
          </div>

          
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>

          <div class="field-wrap">
            <label>
              Branch<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="branch"/>
          </div>
          
          <button class="button button-block" onclick="divert_to_login()"/>Get Started</button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          <form action="check" method="post">
          
            <div class="field-wrap">
            <label>
             User Email <span class="req">*</span>
            </label>
            <input type="email"required autocomplete="off" name="email_id" />
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>
          
          <button class="button button-block"/>Log In</button>
          </form>
        </div>
        
      </div><!-- tab-content -->
      <script src="/automatedgradingsystem/js/jQuery.js"></script>

  		<script src="/automatedgradingsystem/js/index.js"></script>
      
</div> <!-- /form -->
</html>
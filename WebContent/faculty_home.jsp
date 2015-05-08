<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="/automatedgradingsystem/css/normalize.css">

<link rel="stylesheet" href="/automatedgradingsystem/css/style2.css">
<style>
#sheet_box
{
 position: absolute;
 top: 40%;
 left: 18%;
}

#lnm_Logo{
 position: absolute;
 top: 0%;
 left: 86%;
}
</style>
</head>
<body bgcolor="#323a45">

<script>
function divert_to_logout()
{
  alert("Successfully Logged Out! ")
}

</script>
<img src="/automatedgradingsystem/images/main2.JPG" width="900" height="180" alt="Home Page" usemap="#home_elements">
<img id="sheet_box" src="/automatedgradingsystem/images/sheet box.JPG" width ="850" height="400" usemap="#sheetboxElements">
<img id="lnm_Logo" src="/automatedgradingsystem/images/lnm.JPG" width ="175" height="100" usemap="#LnmElements">

<map name="home_elements">
  <area shape="rect" coords="0,115,150,170" href="/automatedgradingsystem/home.jsp">
  <area shape="rect" coords="150,115,325,170" href="/automatedgradingsystem/how_to_use.jsp">
  <area shape="rect" coords="325,115,500,170"  href="/automatedgradingsystem/team.jsp">
</map>

<map name="sheetboxElements">
  <area shape="rect" coords="120,50,350,270" alt="Logo_lnmiit" href="create_sheet.html">

  <area shape="rect" coords="520,50,770,270" alt="Logo_lnmiit" href="grade_sheet.html">
</map>

<map name="LnmElements">
  <area shape="rect" coords="00,400,150,550" alt="Logo_lnmiit" href="www.lnmiit.ac.in">
</map>

<div class="Logout" id="logout">

	<a href = "/automatedgradingsystem/faculty.jsp"><button class="button button-block" onclick="divert_to_logout()"/>Logout</button></a>
	<Logout action="/automatedgradingsystem/home.jsp">
</Logout>
</div>

</body>
</html>
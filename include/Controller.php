<?php
/**
 * Created by PhpStorm.
 * User: Anuvakah
 * Date: 4/1/14
 * Time: 1:27 AM
 */

class Controller {

	function Controller()
	{
		 $this->connection=mysql_connect("localhost","root","");
		if (mysqli_connect_errno())
  		{
  			echo "Failed to connect to MySQL: " . mysqli_connect_error();
  		}		
  		mysql_select_db("studentgrading",$this->connection);
	}

	/***  Add New Student Details ****/
	function addStudentDetails($options)
	{
		$query="INSERT INTO student (roll_no, password, name, fathers_name, mothers_name, d_o_b, nationality, gender, 
			caste, category, religion, email_id, alt_email_id, contact_no, local_add, fathers_number, landline_no, 
			degree, branch, year, semester, date_of_joining, batch, hostel, room_no, medical_history, blood_gp, 
			weight, height, bmi_index, current_condition, name_of_sport, level_in_sport, achievement) 
			VALUES ('$options[0]' ,'$options[1]' ,'$options[2]' ,'$options[3]' ,'$options[4]' ,'$options[5]' ,'$options[6]' ,'$options[7]' ,
				'$options[8]' ,'$options[9]' ,'$options[10]' ,'$options[11]' ,'$options[12]' ,'$options[13]' ,'$options[14]' ,'$options[15]' ,
				'$options[16]' ,'$options[17]' ,'$options[18]' ,'$options[19]' ,'$options[20]' ,'$options[21]' ,'$options[22]' ,'$options[23]' ,
				'$options[24]' ,'$options[25]' ,'$options[26]' ,'$options[27]', '$options[28]', '$options[29]', '$options[30]', '$options[31]',
				'$options[32]', '$options[33]' )";
		$result = mysql_query($query,$this->connection);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('Added Student to database')</script>";

	}


	/***  Add New Faculty Details ****/
	function addFacultyDetails($options)
	{
		$query="INSERT INTO faculty(faculty_id, password, name, room_no, phone_no, mobile_no, email_id) 
				VALUES ('$options[0]' ,'$options[1]' ,'$options[2]' ,'$options[3]' ,'$options[4]' ,'$options[5]' ,'$options[6]' )";
		$result = mysql_query($query,$this->connection);
		//echo $query;
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('Added Faculty to database')</script>";
		
	}


	/***  Update Quiz and Exam weightage of Course used by faculty ****/
	function updateQuizWeightage($options)
	{
		$query= "UPDATE courses SET wtgquiz1='$options[3]',wtgquiz2='$options[4]',wtgquiz3='$options[5]',wtgquiz4='$options[6]',
									wtgquiz5='$options[7]', wtgquiz6='$options[8]',wtgquiz7='$options[9]',wtgquiz8='$options[10]',
									wtgmid_term1='$options[11]', wtgmid_term2='$options[12]',wtgend_term='$options[13]',
									marksquiz1='$options[14]',marksquiz2='$options[15]', marksquiz3='$options[16]',marksquiz4='$options[17]',
									marksquiz5='$options[18]',marksquiz6='$options[19]', marksquiz7='$options[20]',marksquiz8='$options[21]',
									marksmid_term1='$options[22]',marksmid_term2='$options[23]', marksend_term='$options[24]' 
									WHERE course_id='$options[0]'";
		$result = mysql_query($query,$this->connection);
		//echo $query;
		if(!$result)
			echo "Database Error";
		else
			echo "<script>alert('Updated Course Marks Distribution to database')</script>";
	}
	function updateStudentDetails($options)	
	{

	}

	
	function updatefacultyDetails($options, $userid)
	{
		$query="UPDATE faculty SET name='$options[2]',room_no='$options[3]',phone_no='$options[5]',mobile_no='$options[4]',
								   email_id='$options[6]' WHERE username='$userid'";
		$result = mysql_query($query,$this->connection);
		//echo $query;
		if(!$result)
		{
			echo "Databse Error";
		}
		else
			echo "<script>alert('Updated Faculty to database')</script>";

	}

	
	function changePassword($password, $userid,$type)
	{
		$query="UPDATE $type SET password='$password' where username='$userid'";
		$result = mysql_query($query,$this->connection);
		if(!$result)
			echo "Databse Error";
		else
		{
			$_SESSION['password'] = $password;
			echo "<script>alert('Password Updated successfully')</script>";
		}
			
	}
	function allotCourse($faculty)
	{
	}
	function login(&$userid, &$password, &$type)
	{
		
		$auth=$this->authenticate($userid, $type,$password);
		if($auth==0)
			return "Wrong Username or password";
		
		switch ($type) {
			case 'users':
				header('Location:admin.php');
				break;
			case 'student':
				header('Location:student.php');
				break;
			case 'faculty':
				header('Location:faculty.php');
				break;
			default:
				echo"<script>alert('Error')</script>";
				break;
		}
	}
	function authenticate($userid, $type,$password)
	{
		$query="SELECT * FROM $type WHERE username='$userid'";
		$result = mysql_query($query);
		if(!($row = mysql_fetch_array($result)))
			return 0;
		if(!($userid==$row[0]&&$password==$row[1]))
			return 0;
		return 1;
	}

	function signout()
	{
		session_unset();
		session_destroy();
	}

	
	function correctInput(&$options)
	{
		$options=stripslashes($options);
		$options = mysql_real_escape_string($options);
		return $options;
	}

} 
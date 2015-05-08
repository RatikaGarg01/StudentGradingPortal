<?php
Class View
{
	function View()
	{
		 $this->connection=mysql_connect("localhost","root","");
		if (mysqli_connect_errno())
  		{
  			echo "Failed to connect to MySQL: " . mysqli_connect_error();
  		}		
  		mysql_select_db("studentgrading",$this->connection);
	}
	function smestercompleted($userid)
	{
		$query = "select distinct year,semester from acadmic_details where username='$userid'";
		$result = mysql_query($query,$this->connection);
		return $result;
	}
	function semesterMarksheet($user_id,$semester)
	{
		$query = "select acadmic_details.course_id,courses.course_name,acadmic_details.semester  from acadmic_details,courses  where 
		acadmic_details.course_id =courses.course_id and acadmic_details.username='$usder_id'  and acadmic_details.semester='$semester' ";
		$result = mysql_query($query,$this->connection);
		return $result;
	}
	function getCourseList($options)//Get course List of faculty
	{
		$query="select * from courses inner join isalloted where courses.course_id=isalloted.course_id and isalloted.username='$options'";
		$result = mysql_query($query,$this->connection);
		
		return $result;
		
	}
	function viewDetails($type, $userid)
	{
		$query = "SELECT * FROM $type WHERE username='$userid'";

		$result = mysql_query($query);
		if(!($row = mysql_fetch_array($result)))
			echo "<script>alert('Databse Error')</script>";
		else
			return $row; 	 
	}

	function viewCourseDetails($courseid)
	{
		$query = "SELECT * FROM courses WHERE course_id='$courseid'";

		$result = mysql_query($query);
		if(!($row = mysql_fetch_array($result)))
			echo "<script>alert('Databse Error')</script>";
		else
			return $row; 	
	}

	function viewCoursesAllocated_Student($rollid)
	{
		$query = "SELECT * FROM courses WHERE course_id in (select course_id from currently_courses where username='$rollid')";
		$result = mysql_query($query);
		return $result;
		
	}
	function viewCoursesAllocated_Faculty($facid)
	{
		//returns array
	}

}
?>
<?php
/**
 * Created by PhpStorm.
 * User: Anuvakah
 * Date: 4/1/14
 * Time: 1:26 AM
 */

class Grading {
    
    var $marks=array();
    var $con;
    //var $size;
    var $mid;
    var $high;
    
    function Grading()
    {
        //get all the marks for the student in array;function strict_grading()
        //$con=mysql_connect("127.0.0.1","root","","sudentgrading");
        $this->connection=mysql_connect("localhost","root","");
        if (mysqli_connect_errno())
        {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }       
        mysql_select_db("studentgrading",$this->connection);
        /*
        $size=0;
        $total=0;
        $high=0;
        $result = mysql_query("SELECT total FROM currently_courses");
        //$storeArray = Array();
        while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
            $marks[] =  $row['total'];
            $size++;
            if($high<$row['total'])
                $high=$row['total'];
            $total = $row['total'] + $total;
        }
        $mid=$total/$size;*/

    }
    function generateQuery($options)
    {
        $query="SELECT * FROM currently_courses where course_id='$options'";
        //echo $query;
        $result = mysql_query($query,$this->connection);
        return $result;
             
    }
    function getSemesterMarks($options)
    {
        $query="SELECT * FROM currently_courses where username='$options[0]' and course_id='$options[1]'";
        //echo $query;
        $result = mysql_query($query,$this->connection);
        if(!($row = mysql_fetch_array($result)))
            echo "<script>alert('Databse Error')</script>";
        else
            return $row;        
    }
    function getGardes($options)
    {
        $query="SELECT grade FROM currently_courses where course_id='$options'";
        $result = mysql_query($query,$this->connection);
        return $result;
    }
    function getSubjectMarks($options)
    {
        $query="SELECT * FROM courses where course_id='$options[1]'";
         $result = mysql_query($query,$this->connection);
        if(!($row = mysql_fetch_array($result)))
            echo "<script>alert('Databse Error')</script>";
        else
            return $row;   
    }
    function calculateTotalMarks($options) // Calculates the total marks for each Student
    {
        $query="SELECT * FROM courses where course_id='$options'";
        $result_courses = mysql_query($query,$this->connection);
        $result_currnt_c=$this->generateQuery($options);

        if(!($row_c=mysql_fetch_array($result_courses)))
            echo "<script>alert('Databse Error')</script>";

        
        while($row=mysql_fetch_array($result_currnt_c))
        {
            $marks=0;
            for($x=0;$x<=10;$x++)
            {
                if($row_c[$x+3]!=0)
                {
                    $marks+=($row[$x+2]/$row_c[$x+14])*$row_c[$x+3];
                    //echo $row[$x+2];
                    //echo $row[$x+2]."/".$row_c[$x+14]." * ".$row_c[$x+3];
                    //echo "=".$marks."<br>";
                }
                
            }
            $query="UPDATE currently_courses SET total_marks='$marks' WHERE username='$row[0]' and course_id='$row[1]'";
            //echo $query;
            if(!($result = mysql_query($query,$this->connection)))
                echo "Databse Error";
        }

    }

    /*Grading Decision*/
    /*****************************************************************************************************************/
    function set_strict_grading()
    {
        $gap=($this->high-$this->mid)/5;
        $grade=array();
        $i=0;
        foreach ($this->marks as $value)
        {
            if(abs($value-$this->mid)<=$gap)
                $grade[i]='C';
            elseif(abs($value-$this->mid)<=2*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='CD';
                else
                $grade[i]='BC';
            }
            elseif(abs($value-$this->mid)<=3*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='D';
                else
                $grade[i]='B';
            }
            elseif(abs($value-$this->mid)<=4*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='E';
                else
                $grade[i]='AB';
            }
            else
                $grade[i]='A';
            
        }
        submit($grade);

    }
    
    function medium_grading()
    {
        $gap=($this->high-$this->mid)/4;
        $grade=array();
        $i=0;
        foreach ($this->marks as $value)
        {
            if(abs($value-$this->mid)<=$gap)
                $grade[i]='BC';
            elseif(abs($value-$this->mid)<=2*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='C';
                else
                $grade[i]='B';
            }
            elseif(abs($value-$this->mid)<=3*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='CD';
                else
                $grade[i]='AB';
            }
            elseif(abs($value-$this->mid)<=4*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='D';
                else
                $grade[i]='A';
            }
            elseif(abs($value-$this->mid)<=5*$gap)
            {
                $grade[i]='E';
            }
            else
                $grade[i]='F';
            
        }
        submit($grade);

    }
    function easy_grading()
    {
        $gap=($this->high-$this->mid)/4;
        $grade=array();
        $i=0;
        foreach ($this->marks as $value)
        {
            if(abs($value-$this->mid)<=$gap)
                $grade[i]='B';
            elseif(abs($value-$this->mid)<=2*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='C';
                else
                $grade[i]='AB';
            }
            elseif(abs($value-$this->mid)<=3*$gap)
            {
                if($value-$this->mid<0)
                $grade[i]='CD';
                else
                $grade[i]='A';
            }
            elseif(abs($value-$this->mid)<=4*$gap)
            {    
                $grade[i]='D';
            }
            elseif(abs($value-$this->mid)<=5*$gap)
            {
                $grade[i]='E';
            }
            else
                $grade[i]='F';
            
        }
        submit($grade);

    }
    function submit($grade)
    {
        $array_string=mysql_escape_string(serialize($grade));
        mysql_query("insert into currently_courses (total) values($array_string)",$conn);

    }
    /*********************************************************************************************************************/
} 
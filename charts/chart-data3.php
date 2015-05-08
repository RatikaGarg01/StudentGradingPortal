<?php
 $con = mysqli_connect("127.0.0.1","root","","studentgrading");

 if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  $cid=$_SESSION['courses_f'];
$res = mysqli_query($con,"SELECT grade FROM currently_courses where course_id='$cid'");
$data = array('0','0','0','0','0','0','0','0');
//echo "hello";
$total = 0; 
while( $row = mysqli_fetch_array($res))
{
	if($row[0] == 'A')
	 $data[0]++;
	 else if($row[0] == 'AB')
	 $data[1]++;
	 else if($row[0] == 'B')
	 $data[2]++;
	 else if($row[0] == 'BC')
	 $data[3]++;
	 else if($row[0] == 'C')
	 $data[4]++;
	 else if($row[0] == 'CD')
	 $data[5]++;
	 else if($row[0] == 'D')
	 $data[6]++;
	 else if($row[0] == 'F')
	 $data[7]++;
}
$i=0;
while ($i<=7)
{
	$total = $total + $data[$i];
	$i++;
}
$i=0;
while ($i<=7)
{
	$data[$i] = round(($data[$i]/ $total)*100,2);
	$i++;	
}
include_once( 'ofc-library/open-flash-chart.php' );
$g = new graph();

$g->bg = '#E4F0DB';
$g->pie(100,'#E4F0DB','{display:none;}',false,1);
//
// pass in two arrays, one of data, the other data labels
//
$g->pie_values( $data, array('A','AB','B','BC','C','CD','D','F'), $links );
//
// Colours for each slice, in this case some of the colours
// will be re-used (3 colurs for 5 slices means the last two
// slices will have colours colour[0] and colour[1]):
//
$g->pie_slice_colours( array('#d01f3c','#356aa0','#C79810','#FF1493','#228B22','#4B0082','#00FF00','#FF4500') );
$g->set_tool_tip( 'Label: #x_label#<br>Value: #val#%&' );

$g->title( 'Grading Performance', '{font-size:18px; color: #d01f3c}' );

echo $g->render();
/*include_once( 'ofc-library/open-flash-chart.php' );
$g = new graph();
$g->title( 'quiz1', '{font-size: 12px;}' );
 
$g->set_data( $data );
$g->set_x_labels( array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec' ));
$g->set_y_max( 15 );
$g->y_label_steps( 3 );

// display the data
echo $g->render();
*/
?>

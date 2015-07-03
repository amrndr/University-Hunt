<?php
 
/*
 * Following code will list all the university
 */
 
// array for JSON response
$response = array();
 
// include db connect class
mysql_connect("localhost","root","") or die(mysql_error());

mysql_select_db("universitydb");
 
 $t_fees_min = $_GET['t_fees_min'];
	$t_fees_max = $_GET['t_fees_max'];
	$ielts_min = $_GET['ielts_min'];
	$ielts_max = $_GET['ielts_max'];
	$toefl_min = $_GET['toefl_min'];
	$toefl_max = $_GET['toefl_max'];
	$gpa_min = $_GET['gpa_min'];
	$gpa_max = $_GET['gpa_max'];
	$gre_min = $_GET['gre_min'];
	$gre_max = $_GET['gre_max'];
	$country = $_GET['country'];
	$course=$_GET['course'];
	$state=$_GET['state'];
  
	
    // get a university from universitys table
	
    $result = mysql_query("SELECT * FROM univtbl WHERE (tuition between $t_fees_min and $t_fees_max) and (ielts between $ielts_min and $ielts_max)
	and (toefl between $toefl_min and $toefl_max) and (gre between $gre_min and $gre_max) 
	and (gpa between $gpa_min and $gpa_max) and (country=$country) and (program like($course)) and(region like($state))") ;
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // university node
    $response["university"] = array();
 
    while ($row = mysql_fetch_array($result)) {

        // temp user array
		
        $product = array();
        $product["name"] = $row["name"];
            $product["image"] = $row["image"];
            //$product["univ_id"] = $row["univ_id"];
 
        // push single product into final response array
        array_push($response["university"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no university found
    $response["success"] = 0;
    $response["message"] = "No university found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
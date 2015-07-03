
<?php
 
/*
 * Following code will list all the university
 */
 
// array for JSON response
$response = array();
 
// include db connect class
mysql_connect("localhost","root","") or die(mysql_error());

mysql_select_db("universitydb");
 
    $name = $_GET['name'];
	
	
  
	
    // get a university from universitys table
	
    $result = mysql_query("SELECT * FROM univtbl WHERE (name=$name);") ;
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // university node
	
    $response["university"] = array();
 
    while ($row = mysql_fetch_array($result)) {

        // temp user array
		
        $product = array();
		$product["region"] = $row["region"];
		$product["tuition"] = $row["tuition"];
		$product["toefl"] = $row["toefl"];
        $product["name"] = $row["name"];
            $product["image"] = $row["image"];
             $product["gre"] = $row["gre"];
			  $product["ielts"] = $row["ielts"];
			   $product["gpa"] = $row["gpa"];
			    $product["country"] = $row["country"];
			 $product["program"] = $row["program"];
					 $product["deadline"] = $row["deadline"];
 	 $product["contact"] = $row["contact"];
	 	 $product["website"] = $row["website"];
		  
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
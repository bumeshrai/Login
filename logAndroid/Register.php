<?PHP
	$con = mysqli_connect("localhost","test","test","test");
	/* check connection */
	if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
	}

	$name = $_POST["name"];
	$age = $_POST["age"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	

	/* create a prepared statement */
	if ($statement = mysqli_prepare($con, "INSERT INTO android_user (name, age, username, password) VALUES (?, ?, ?, ?)")) {

		  /* bind parameters for markers */
		  mysqli_stmt_bind_param($statement,  "siss", $name, $age, $username, $password);

		  /* execute query */
		  mysqli_stmt_execute($statement);

			/*
		  printf("%s age is %s\n", $username, $age);
			*/

		  /* close statement */
		  mysqli_stmt_close($statement);
	}

	/* close connection */
	mysqli_close($con);

	$response = array();
    $response["success"] = true;  
    
    echo json_encode($response);



?>



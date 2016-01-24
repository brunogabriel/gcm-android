<?php
	
	// Credentials (see README.MD on top github)
	define('CREDENTIALS_KEY', 'CHANGE_HERE' );

	// Google URL GCM (01/23/2016)
	define('GCM_URL', 'https://gcm-http.googleapis.com/gcm/send');

	// 1 until 100 users, if you need more than 100, use function again with others ids
	$registrationIds = array("CHANGE HERE");

	// Array with keys (json) to be read by android app, put your message etc. here
	$androidPush = array(
    	'message'       => 'Hello user, test this project!',
    	'title'         => 'Using Native GCM',
    	'subtitle'      => 'Submessage'
	);

	// Json to be scalable using google gcm server
	$fields_gcm = array (
    	'registration_ids'  => $registrationIds,
    	'data'              => $androidPush
	);

	$headers = array(
    	'Authorization: key=' . CREDENTIALS_KEY,
    	'Content-Type: application/json'
	);

	// Prepare to send data
	$curlRequest = curl_init();

	curl_setopt( $curlRequest,CURLOPT_URL, GCM_URL);
	curl_setopt( $curlRequest,CURLOPT_POST, true );
	curl_setopt( $curlRequest,CURLOPT_HTTPHEADER, $headers );
	curl_setopt( $curlRequest,CURLOPT_RETURNTRANSFER, true );
	curl_setopt( $curlRequest,CURLOPT_SSL_VERIFYPEER, false );
	curl_setopt( $curlRequest,CURLOPT_POSTFIELDS, json_encode( $fields_gcm ) );

	$result = curl_exec($curlRequest);
	curl_close( $curlRequest );

	// plot result from google gcm server
	echo $result;


?>
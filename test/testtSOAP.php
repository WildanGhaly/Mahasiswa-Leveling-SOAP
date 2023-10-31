<?php
try {
    // Initialize SoapClient
    $client = new SoapClient('http://localhost:8081/ws/testing?wsdl');
    
    // The parameters for the SOAP call
    $params = array(
        'arg0' => 1  // Replace 1 with the actual ID you want to use
    );
    
    // Make the SOAP call
    $response = $client->getChallengeById($params);
    
    // Handle the response
    print_r($response);

    // Accessing the nested stdClass objects
    $description = $response->return->description;
    $id = $response->return->id;
    $name = $response->return->name;
    $threshold = $response->return->threshold;

    // Now, you can use these values as needed
    echo "<br><br>";
    echo "Description: " . $description . "<br>";
    echo "ID: " . $id . "<br>";
    echo "Name: " . $name . "<br>";
    echo "Threshold: " . $threshold . "<br>";

    
} catch (SoapFault $fault) {
    // Handle exceptions
    echo "SOAP Fault: (fault code: {$fault->faultcode}, fault string: {$fault->faultstring})";
}
?>

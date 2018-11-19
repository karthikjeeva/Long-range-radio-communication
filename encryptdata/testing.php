<?php

include "connect.php";

$query="select src from encrypted_data";

$rs=$dbhandle->query($query);

$key = hex2bin('2b7e151628aed2a6abf7158809cf4f3c');
$iv = hex2bin('f0f1f2f3f4f5f6f7f8f9fafbfcfdfeff');
 
while ($row = $rs->fetch_array()) {
  $data['src'] = $row;
 
}
 foreach($data['src'] as $str)
{
	
	if($str==3)
	var_dump($str);
 
   else
	   echo 'rerieved as string not number';
		  
}	  

   /* $input = openssl_encrypt('kar', 'AES-128-CTR',$key, OPENSSL_RAW_DATA, $iv);
	  var_dump($input);
	  
	  $hex1=bin2hex($input);
	  var_dump($hex1);
 
		  $output = openssl_decrypt($input, 'AES-128-CTR', $key,OPENSSL_RAW_DATA, $iv);
		  var_dump($output);  */
		  
		  
?>

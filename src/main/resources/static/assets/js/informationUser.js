/**
 * 
 */

function updateInformation(){

	var checkBoolean = true;
	
	var nameCustomer = document.getElementById("nameCustomer").value;
	if(nameCustomer === ''){
		document.getElementById("error-name-customer").style.display = "block";
		return checkBoolean = false;
	}else{
		document.getElementById("error-name-customer").style.display = "none";
	}
	
	return checkBoolean;
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>Register</title>
   <link rel="stylesheet" href="stylesheet.css" type="text/css" /> 
   <link rel="shortcut icon" href="http://www.iconarchive.com/download/i99695/sonya/swarm/Fast-Food.ico">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script></head>
  
  <style>
  	#register_block{
  		margin-top : 60px;
  	}
  	#boy_logo{
  		margin-top : 10px;
  	}
  	
  	/* Popup container - can be anything you want */
.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* The actual popup */
.popup .popuptext {
  visibility: hidden;
  width: 160px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

/* Toggle this class - hide and show the popup */
.popup .show {
  visibility: visible;
  -webkit-animation: fadeIn 1s;
  animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
@-webkit-keyframes fadeIn {
  from {opacity: 0;} 
  to {opacity: 1;}
}

@keyframes fadeIn {
  from {opacity: 0;}
  to {opacity:1 ;}
}
  	
  </style>
<body>
	<div class="container-fluid">
	<form action="./Success" method="post">
	 <center>
		<h3 style="font-weight:bold" width="20%" for="title">------ Welcome To New User Registration ------</h3>
		<div class="card" style="width:400px" id="register_block">
    		<center><img class="card-img-top" id="boy_logo" src="https://i.ibb.co/J2s3Qbq/boy.png" alt="Card image" style="width:30%;"></center>
	    	<div class="card-body">
	    		<div class="form-group">
		    		<label for="name">Name</label>
		    		<input type="text" pattern="[A-Za-z]+" class="form-control" id="name" name="name" required>
		  		</div>
				<div class="form-group">
		    		<label for="email">Email</label>
		    		<input type="email" class="form-control" id="email" name="email" required>
		  		</div>
		  		<div class="form-group">
		    		<label for="password">Password</label>
		    		<input type="password" pattern=".{0}|.{5,10}"  required title="5 Length is minimum 10 Length is maximum" class="form-control" id="password" name="password" required>
		  		</div>
		  		<div align="center">
		  			<font size=4px>
		  				<input id="checkBox" type="checkbox" required> 
		  				I agree to the <div class="popup" onclick="myFunction()"><b>Terms and Conditions</b>
		  			</font>	
		  				<span class="popuptext" id="myPopup">Your Private data will be maintained by our team and you can't raise your voice against us</span>
		  			</div>
		  		</div>
				<br>
				<input type="submit" class="btn btn-danger btn-block" value="Register" disabled="disabled"/>
				<input type="submit" class="btn btn-danger btn-block" value="Cancel" onclick="window.location='./index';" />
			</div>
		</div>
		</center>
			<script type="text/javascript">
				// When the user clicks on div, open the popup
				function myFunction() {
					var popup = document.getElementById("myPopup");
					popup.classList.toggle("show");
				}
			</script>
			<script  src="js/submitEnable.js"></script>
	</form>
</div>
</body>
</html>


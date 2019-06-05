<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page session = "false" %>
	
<html>
<%	HttpSession session = request.getSession(false);
	if(session.getAttribute("name")!=null){
		response.sendRedirect("./home");
}else{ 
%>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="shortcut icon"
	href="http://www.iconarchive.com/download/i99695/sonya/swarm/Fast-Food.ico">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<style>
#register_block {
	margin-top: 10px;
}

#boy_logo {
	margin-top: 10px;
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
@
-webkit-keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>
<body
	background="https://images.assetsdelivery.com/compings_v2/krekdm/krekdm1611/krekdm161100029.jpg">
	<div class="container-fluid">
		<form autocomplete="off" action="./Success" method="post">
			<center>
				<h2 style="font-weight: bold" width="20%" for="title">Sign Up</h2>
				<p>It's Free and always will be.</p>
				<div class="card" style="width: 400px" id="register_block">
					<center>
						<img class="card-img-top" id="boy_logo"
							src="https://i.ibb.co/J2s3Qbq/boy.png" alt="Card image"
							style="width: 20%;">
					</center>
					<div class="card-body">
						<div class="form-group">
							<label for="name">Name</label> <input type="text"
								placeholder="Full Name" class="form-control" id="name"
								name="name" maxlength="20" required
								pattern="^[a-zA-Z]+( [a-zA-Z]+)*$"
								title="Only max 20 digits of Alphabetic Character With Single Space is allowed between Characters"
								onkeyup="getCaps()"
								onkeypress="return (event.charCode > 64 && event.charCode < 91) || (event.charCode > 96 && event.charCode < 123) || event.charCode == 32">

						</div>
						<div class="form-group">
							<label for="email">Email</label> <input maxlength="20"
								type="email" placeholder="e.g. xyz@domain.com"
								class="form-control" id="email" name="email" required
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
								title="Please Provide Vaild Mail Id"
								onkeypress="return event.charCode != 32">
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								placeholder="e.g. Uber@123"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,10}" required
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 but less than 10 characters"
								class="form-control" id="password" name="password">
						</div>
						<div class="form-group">
							<label for="password">Confirm Password</label> <input
								type="password" placeholder="e.g. Uber@123"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,10}" required
								title="Password and Confirm Password must match"
								class="form-control" id="confPwd" name="confPwd"
								onkeyup="checkPwd()">
						</div>
						<div align="center">
							 <input id="checkBox" type="checkbox"required">
								I agree to the
								<div class="popup"
									onclick="document.getElementById('id01').style.display='block'">
									<font size=3px><b>Terms and Conditions</b></font>
						</div>
					</div>
					<div id="id01" class="w3-modal">
						<div class="w3-modal-content w3-card-4">
							<header class="w3-container w3-teal">
								<span
									onclick="document.getElementById('id01').style.display='none'"
									class="w3-button w3-display-topright">&times;</span>
								<h2>Foody Policies</h2>
							</header>
							<div class="w3-container">
								<p>Your Private data will be maintained by our
								team and you can't raise your voice against us</p>
							</div>
							<footer class="w3-container w3-teal">
								<p>End of T&C</p>
							</footer>
						</div>
					</div>
					<br>
					<br /> <input type="submit" class="btn btn-danger btn-block"
						value="Register" disabled="disabled" /> <input type="reset"
						class="btn btn-danger btn-block" value="Reset" /> <input
						type="button" class="btn btn-danger btn-block" value="Cancel"
						onclick="warnPopUp()" />
				</div>
				<div>
					<a href="./login">Already Has The Account with us? <b><u>Login
								Here</u></b></a>
				</div>
	</div>
	</center>
	<script type="text/javascript">
	
		// Get the modal
		 var modal = document.getElementById('id01');

		 // When the user clicks anywhere outside of the modal, close it
		 window.onclick = function(event) {
		   if (event.target == modal) {
		     modal.style.display = "none";
		   }
		 }
		 
		function getCaps() {
			var x = document.getElementById("name");
			x.value = x.value.toUpperCase();
		}

		function warnPopUp() {
			if (confirm("Your data will be lost. Once you press OK !!!")) {
				window.location = './index';
			} else {
			}
		}

		function checkPwd() {
			var pwd = document.getElementById("password");
			var cfPwd = document.getElementById("confPwd");
			return (pwd == cfPwd);
		}

		window.onbeforeunload = function() {
			var confirmationMessage = "\o/";
			(e || window.event).returnValue = confirmationMessage; //Gecko + IE
			return confirmationMessage; //Webkit, Safari, Chrome
		};
	</script>
	<script src="js/submitEnable.js"></script>
	</form>
	</div>
</body>
<% }%>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="shortcut icon" href="http://www.iconarchive.com/download/i99695/sonya/swarm/Fast-Food.ico">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<style>
#register_block {
	margin-top: 20px;
}

#boy_logo {
	margin-top: 10px;
}
</style>
<body  background="https://images.assetsdelivery.com/compings_v2/krekdm/krekdm1611/krekdm161100029.jpg">
	<div class="container-fluid">
	<script type="text/javascript">
		 	 function checkform() {
				var f = document.forms["loginForm"].elements;
				var count=0;
				for (var i = 0; i < f.length; i++) {
					if (f[i].value.length > 0)
						count++;
				}
				if(count==f.length)
					document.getElementById('login').disabled = false;
				else{
					document.getElementById('login').disabled = true;
					count=0;
				}
			}   
		</script>
		<form autocomplete="off" action="./console" method="post" name="loginForm">
			<center>
				<h2 style="font-weight: bold" width="20%" for="title"><font>LOGIN</font></h2>
				<div class="card" style="width: 360px" id="register_block">
					<center>
						<img class="card-img-top" id="boy_logo"
							src="https://i.ibb.co/RQyz9Vv/man.png" alt="Card image"
							style="width: 30%;">
					</center>
					<div class="card-body">
						<div class="form-group">
							<label for="email">Email</label> 
							<input type="email"
								class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Please Provide Vaild Mail Id" 
								id="email" name="email" onkeypress= "return event.charCode != 32"
								placeholder="e.g: myFoody@domain.com" onKeyup="checkform()" required>
						</div>
						<div class="form-group">
							<label for="password">Password</label> 
							<input type="password"
								class="form-control" 
								id="password" name="password"
								placeholder="e.g: Foody@123"
								onKeyup="checkform()" required>
						</div>
						<input id="login" type="submit" name="form[Submit]"
							class="btn btn-success btn-block" value="GO !"
							disabled="disabled"" />
						</div>
						<div>
							<p>Don't have Account with us yet?</p><a href="./register"><b>Register Here</b></a>
						</div>
				</div>	
			</center>
		</form>
	</div>
</body>
</html>
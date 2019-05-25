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
	margin-top: 60px;
}

#boy_logo {
	margin-top: 10px;
}
</style>
<body>
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
		<form action="./console" method="post" name="loginForm">
			<center>
				<div class="card" style="width: 400px" id="register_block">
					<center>
						<img class="card-img-top" id="boy_logo"
							src="https://i.ibb.co/RQyz9Vv/man.png" alt="Card image"
							style="width: 40%;">
					</center>
					<div class="card-body">
						<div class="form-group">
							<label for="email">Email</label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="Ex: King123" onKeyup="checkform()" required>
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" id="password" name="password"
								 onKeyup="checkform()" required>
						</div>
						<input id="login" type="submit" name="form[Submit]"
							class="btn btn-success btn-block" value="Login"
							disabled="disabled"" />
					</div>
				</div>
			</center>
		</form>
	</div>
</body>
</html>
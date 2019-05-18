<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">
		
		<style>
			body{
				font-family: 'Nunito', sans-serif;
			}
			
			#navigationbar{
				margin-top : 40px;
				margin-bottom : 40px;
			}
			
			
			#content{
			
				margin-top : 70px;
				margin-bottom : 70px;	
			}
			
			.footer {
				position: fixed;
				left: 0;
				bottom: 0;
				width: 100%;
				background-color: black;
				color: white;
  				text-align: center;
  				height : 120px;
			}
			
			#footer-data{
				margin-top : 30px;
			}
			
			#cart_logo,#cart_value{
				padding-right : 5px;
			}
			
		</style>
</head>
<body>
		
		<div class="container-fluid">
			<div class="container-fluid" id="navigationbar">
				<nav class="navbar navbar-expand-sm">
					  <a class="navbar-brand" href="index.html">
						<svg xmlns="http://www.w3.org/2000/svg" viewBox="57 57 369 61" height="24px" width="146px" data-reactid="17"><g data-reactid="18"><path fill="#262626" d="M228.06,81.56c-5.47,0-9.35,4.28-9.35,10.85v24.1h-8.35V74.09h8.25v5.18a11.19,11.19,0,0,1,9.94-5.48h3v7.77ZM204.59,95.3c0-12.65-9-22.11-21.18-22.11A21.83,21.83,0,0,0,161.73,95.3c0,12.64,9.75,22.2,22.47,22.2a22.17,22.17,0,0,0,18.3-9.06L196.44,104a14.78,14.78,0,0,1-12.24,6.17,14.22,14.22,0,0,1-14-12.14h34.41Zm-34.21-3.89c1.49-6.47,6.66-10.85,12.93-10.85s11.43,4.38,12.83,10.85Zm-35.1-18.22a21.12,21.12,0,0,0-15,6.27V58.25h-8.35v58.26h8.25v-5.38a21.45,21.45,0,0,0,15.12,6.37,22.16,22.16,0,1,0,0-44.31Zm-.6,36.85A14.69,14.69,0,1,1,149.3,95.4,14.58,14.58,0,0,1,134.68,110Zm-53.5-.4c8.06,0,14.32-6.18,14.32-15.44V58.25h8.65v58.26H95.6V111a21.24,21.24,0,0,1-15.41,6.47c-12.43,0-22-9.06-22-22.8V58.25H67v36C67,103.56,73,109.64,81.18,109.64Z" data-reactid="19"></path><path fill="#5fb709" d="M252.32,58.25h40.87v10H263.36V82.45h29v9.66h-29v14.44h29.83v10H252.32ZM406.06,117.6c12.53,0,19.59-6,19.59-14.24,0-5.87-4.18-10.25-12.93-12.15l-9.25-1.89c-5.37-1-7.06-2-7.06-4,0-2.59,2.59-4.18,7.36-4.18,5.17,0,9,1.39,10,6.17h10.84c-.59-9-7.06-14.94-20.18-14.94-11.34,0-19.3,4.68-19.3,13.75,0,6.27,4.38,10.35,13.83,12.34l10.34,2.39c4.08.8,5.17,1.9,5.17,3.59,0,2.69-3.08,4.38-8.06,4.38-6.26,0-9.84-1.39-11.23-6.17H384.28C385.87,111.63,392.53,117.6,406.06,117.6Zm-24.93-1.09H369.4c-7.36,0-11.44-4.58-11.44-10.36V83.25h-8.25V73.49H358V61.24H368.9V73.49h12.23v9.76H368.9v20.11c0,2.29,1.59,3.39,4.08,3.39h8.15Zm-47-43v3.88a21.16,21.16,0,0,0-13.73-5,22.61,22.61,0,1,0,0,45.21,21.1,21.1,0,0,0,13.73-5v3.89H345v-43Zm-12.83,34.65a13.15,13.15,0,1,1,13-13.14A13,13,0,0,1,321.28,108.14Z" data-reactid="20"></path></g></svg>
					  </a>
					  <ul class="navbar-nav ml-auto">
						<li class="nav-item">
						  <a class="nav-link text-danger" href="./login">Login</a>
						</li>
						<li class="nav-item">
						  <a class="nav-link text-danger" href="./register">Register</a>
						</li>
						<li class="nav-item" id="cart_logo">
							<a href="./cart.jsp"><img src="https://i.ibb.co/wQdbbhw/cart.png" alt="cart" border="0"></a>					
						</li>
						<li class="nav-item" id="cart_value">
							<h6></h6>							
						</li>
					  </ul>
				</nav>
			</div>
			<div class="container-fluid" id="content">
				<div class="row">
					<div class="col">
						<div class="card menu" style="width:200px">
							<img class="card-img-top" src="https://i.ibb.co/xzpd5sw/pz1.jpg" alt="Menu 1" style="width:100%; height:150px;">
								<div class="card-body">
									<center>
										<h5 class="card-title">Margherita</h5>
										<p class="card-text"></p>
									<center>
									<button class="btn btn-block btn-primary add">Add</button>
								</div>
						</div>
					</div>
					<div class="col">
						<div class="card menu" style="width:200px">
							<img class="card-img-top" src="https://i.ibb.co/Hg9cbx5/kfc2.jpg" alt="Rest 1" style="width:100%; height:150px;">
								<div class="card-body">
									<center>
										<h5 class="card-title">Egg Rice Bowl</h5>
										<p class="card-text"></p>
									<center>
									<button class="btn btn-block btn-primary add">Add</button>
									
								</div>
						</div>
					</div>
					<div class="col">
						<div class="card menu" style="width:200px">
							<img class="card-img-top" src="https://i.ibb.co/7NcFsN6/kfc3.png" alt="Rest 1" style="width:100%; height:150px;">
								<div class="card-body">
									<center>
										<h5 class="card-title">Chicken Bucket</h5>
										<p class="card-text"></p>
									<center>
									<button class="btn btn-block btn-primary add">Add</button>
								</div>
						</div>
					</div>
					<div class="col">
						<div class="card menu" style="width:200px">
							<img class="card-img-top" src="https://i.ibb.co/2YMndSY/kfc4.jpg" alt="Rest 1" style="width:100%; height:150px;">
								<div class="card-body">
									<center>
										<h5 class="card-title">Paneer Pakoda</h5>
										<p class="card-text"></p>
									<center>
									<button class="btn btn-block btn-primary add">Add</button>
								</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card menu" style="width:200px">
							<img class="card-img-top" src="https://i.ibb.co/sHhDWN1/kfc5.jpg" alt="Rest 1" style="width:100%; height:150px;">
								<div class="card-body">
									<center>
										<h5 class="card-title">KFC combo</h5>
										<p class="card-text"></p>
									<center>
									<button class="btn btn-block btn-primary add">Add</button>
								</div>
						</div>
					</div>
					
				</div>
			</div>
			
			<div class="container-fluid footer">
				<div class="container-fluid" id="footer-data">
					<h5>Powered By @Uber Technologies</h5>
				</div>
			</div>
			
		</div>

		<script>
			$(".add").click(
				
				function(){
					var cart_val = $("#cart_value").text().trim();
					console.log("Value Length :"+cart_val.length);
					if(cart_val.length === 0){
						console.log("1st Flow");
						cart_val = 1;
					}else{
						console.log("2nd Flow");
						cart_val = parseInt(cart_val) + 1;
					}
					console.log("New Value "+cart_val);
					$("#cart_value").text(cart_val);
					
				}
			);
		</script>
	

</body>
</html>
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
		
</head>
<body>


		<div class="container-fluid">
			<div class="jumbotron text-center" style="background-color : #1565C0; color: white;">
  				<h2>Biryani Zone</h2> 
			</div>
			<table class="table table-bordered" style="text-align: center;">
			    <thead>
			      <tr>
			        <th>Item Name</th>
			        <th>Quantity</th>
			        <th>Price</th>
			      </tr>
			    </thead>
			    <tbody>
			      <tr>
			        <td>Biryani</td>
			        <td>2</td>
			        <td>Rs. 180.00</td>
			      </tr>
			      <tr>
			        <td>Dosa</td>
			        <td>1</td>
			        <td>Rs. 60.00</td>
			      </tr>
			      <tr>
			        <td colspan="2">Total Amount : </td>
			        <td>Rs. 240.00</td>
			      </tr>
			    </tbody>
			</table>
			<div class="container-fluid">
				<center><button id="checkout" style="width:50%" class="btn btn-block btn-danger">Proceed To Checkout</button></center>
			</div>
		</div>
		
		<script>
			$("#checkout").click(
				function(){
					
					window.alert("Successfully Ordered");
					window.location ="./index.jsp";
					
				}		
			);
		</script>

</body>
</html>
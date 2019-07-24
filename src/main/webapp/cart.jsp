<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session = "false" %>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Cart</title>
<link rel="shortcut icon"
	href="http://www.iconarchive.com/download/i99695/sonya/swarm/Fast-Food.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito"
	rel="stylesheet">

</head>

<body>
	<div class="container-fluid">
		<div class="jumbotron text-center"
			style="background-color: #1565C0; color: white;">
			<h2>Foody Cart</h2>
		</div>
	
	<form action="./checkOut" method="post">
		<table class="table table-bordered" id="summary"
			style="text-align: center;">
			<thead>
				<tr>
					<th>Item Name</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td colspan="2">Total Amount :</td>
					<td id="net"></td>
				</tr>
			</tbody>
		</table>
		<div class="container-fluid">
			<center>
		<input type="hidden" id="myCart" name="myCart" value="" />
					<script type="text/javascript">
						 var elem = document.getElementById("myCart");
						 var value = localStorage.getItem('cart_items').toString();
						 elem.value = value;
					</script>
		<input type="submit" value="Proceed To Checkout" style="width: 50%"
					class="btn btn-block btn-danger"/>	
		<input type="button" value="Clear Cart" style="width: 50%"
					class="btn btn-block btn-danger" onclick="emptyStash()"/>
			</center>
		</div>
	 </form>
</div>

	<script>
			
		function emptyStash(){
			localStorage.clear();
			location.reload();
		}
		
			window.onload = function fillData(){
				var items = JSON.parse(localStorage.getItem('cart_items'));
				var table = document.getElementById("summary");
				var sum=0;
				var currencyCode = null;
				for(var i=0; i<items.length;i++){
					var newRow = table.insertRow(i+1);
					for(var j=0 ; j < 3 ; j++){ //Length of the variable
						var cell = newRow.insertCell(j);
						if(j==0)
							cell.innerHTML = items[i]["item_name"];
 						if(j==1)
							cell.innerHTML = items[i]["item_count"];
						if(j==2)
							{
								cell.innerHTML = items[i]["item_price"]; 
								var amt = parseFloat((parseFloat(items[i]["item_price"].substr(3, ))*parseFloat(items[i]["item_count"])).toFixed(2)); 
								// 3-Digit Currency Code ignore
								currencyCode = items[i]["item_price"].substr(0,3);
								sum = sum + amt;
							}
					}
				}
				document.getElementById("net").innerText = currencyCode + " " + sum.toFixed(2);
			};
		</script>
</body>

</html>
<%@page import="com.aws.domain.FOODY_ORDER_DETAILS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="com.aws.domain.*"%>
<%@ page import="com.aws.dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page session="false"%>

<html>
<%
	HttpSession ses = request.getSession(false);
	if (ses.getAttribute("name") != null) {
%>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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


<style>
body {
	font-family: 'Nunito', sans-serif;
}

#navigationbar {
	margin-top: 40px;
	margin-bottom: 40px;
}

#content {
	margin-top: 70px;
	margin-bottom: 70px;
	display: flex;
	justify-content: center;
}

.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: black;
	color: white;
	text-align: center;
	height: 90px;
}

#footer-data {
	margin-top: 30px;
}

.dot {
	margin: 15px;
	height: 10px;
	width: 10px;
	background-color: #B22222;
	border-radius: 50%;
	display: inline-block;
}

/* Style the tab */
.tab {
	float: left;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
	width: 25%;
	height: 500px;
}

/* Style the buttons inside the tab */
.tab button {
	display: block;
	background-color: inherit;
	color: black;
	padding: 22px 16px;
	width: 100%;
	border: none;
	outline: none;
	text-align: left;
	cursor: pointer;
	transition: 0.3s;
	font-size: 13px;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	float: left;
	padding: 0px 12px;
	border: 1px solid #ccc;
	width: 75%;
	border-left: none;
	height: 500px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}

th {
	text-align: center;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<div class="container-fluid" id="navigationbar">
			<nav class="navbar navbar-expand-sm">
				<a class="navbar-brand" href="./home"> <svg
						xmlns="http://www.w3.org/2000/svg" viewBox="57 57 369 61"
						height="24px" width="146px" data-reactid="17">
						<g data-reactid="18">
						<path fill="#262626"
							d="M228.06,81.56c-5.47,0-9.35,4.28-9.35,10.85v24.1h-8.35V74.09h8.25v5.18a11.19,11.19,0,0,1,9.94-5.48h3v7.77ZM204.59,95.3c0-12.65-9-22.11-21.18-22.11A21.83,21.83,0,0,0,161.73,95.3c0,12.64,9.75,22.2,22.47,22.2a22.17,22.17,0,0,0,18.3-9.06L196.44,104a14.78,14.78,0,0,1-12.24,6.17,14.22,14.22,0,0,1-14-12.14h34.41Zm-34.21-3.89c1.49-6.47,6.66-10.85,12.93-10.85s11.43,4.38,12.83,10.85Zm-35.1-18.22a21.12,21.12,0,0,0-15,6.27V58.25h-8.35v58.26h8.25v-5.38a21.45,21.45,0,0,0,15.12,6.37,22.16,22.16,0,1,0,0-44.31Zm-.6,36.85A14.69,14.69,0,1,1,149.3,95.4,14.58,14.58,0,0,1,134.68,110Zm-53.5-.4c8.06,0,14.32-6.18,14.32-15.44V58.25h8.65v58.26H95.6V111a21.24,21.24,0,0,1-15.41,6.47c-12.43,0-22-9.06-22-22.8V58.25H67v36C67,103.56,73,109.64,81.18,109.64Z"
							data-reactid="19"></path>
						<path fill="#5fb709"
							d="M252.32,58.25h40.87v10H263.36V82.45h29v9.66h-29v14.44h29.83v10H252.32ZM406.06,117.6c12.53,0,19.59-6,19.59-14.24,0-5.87-4.18-10.25-12.93-12.15l-9.25-1.89c-5.37-1-7.06-2-7.06-4,0-2.59,2.59-4.18,7.36-4.18,5.17,0,9,1.39,10,6.17h10.84c-.59-9-7.06-14.94-20.18-14.94-11.34,0-19.3,4.68-19.3,13.75,0,6.27,4.38,10.35,13.83,12.34l10.34,2.39c4.08.8,5.17,1.9,5.17,3.59,0,2.69-3.08,4.38-8.06,4.38-6.26,0-9.84-1.39-11.23-6.17H384.28C385.87,111.63,392.53,117.6,406.06,117.6Zm-24.93-1.09H369.4c-7.36,0-11.44-4.58-11.44-10.36V83.25h-8.25V73.49H358V61.24H368.9V73.49h12.23v9.76H368.9v20.11c0,2.29,1.59,3.39,4.08,3.39h8.15Zm-47-43v3.88a21.16,21.16,0,0,0-13.73-5,22.61,22.61,0,1,0,0,45.21,21.1,21.1,0,0,0,13.73-5v3.89H345v-43Zm-12.83,34.65a13.15,13.15,0,1,1,13-13.14A13,13,0,0,1,321.28,108.14Z"
							data-reactid="20"></path></g></svg>
				</a>
				<ul class="navbar-nav ml-auto">
					<li class="dot" style="margin-right: 5px; margin-left: 5px;"></li>
					<li class="nav-item"><a class="nav-link text-danger"
						href="./logout">Logout</a></li>
				</ul>
			</nav>
		</div>

		<%
			FOODY_USERS user = UsersDAO.getInstance().getUserFromEmail((String) ses.getAttribute("email"));
		%>

		<div class="tab">
			<button class="tablinks" onclick="openTab(event, 'Me')"
				id="defaultOpen">About Me</button>
			<button class="tablinks" onclick="openTab(event, 'Pwd')">Manage
				Password</button>
			<button class="tablinks" onclick="openTab(event, 'Add')">Manage
				Address</button>
			<button class="tablinks" onclick="openTab(event, 'Order')">My
				Orders</button>
		</div>

		<div id="Me" class="tabcontent">
			&nbsp; &nbsp; &nbsp;
			<h3>
				Name ::
				<%=ses.getAttribute("name")%></h3>
			<p>
				Email ::
				<%=ses.getAttribute("email")%></p>
			<p>
				<%
					DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
						String text = df.format(user.getDoj());
				%>
				Member Since ::
				<%=text%></p>

			<%
				String add = user.getAddress();
					if (add != null) {
			%>
			<p>
				Address ::
				<%=add%>
			</p>
			<%
				} else {
			%>
			<p>Address :: No Address Can be Found</p>
			<%
				}
			%>

		</div>

		<div id="Pwd" class="tabcontent">
			&nbsp; &nbsp;
			<h5>If your password encrypts your history then its a PASTWORD</h5>
			<form action="./updatePwd" method="post">
				<br>
				<table>
					<tbody>
						<tr>
							<td>Old Password :</td>
							<td><input type="password" name="oldPwd" autocomplete="off" id="oldPwd"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,10}" required
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 but less than 10 characters" 
								onkeyup="checkDiffer()" required><br>
							</td>
						</tr>
						<tr>
							<td>New Password :</td>
							<td><input type="password" name="password" id="password"
								autocomplete="off"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,10}" required
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 but less than 10 characters" 
								onkeyup="checkDiffer()" required><br></td>
						</tr>
						<tr>
							<td>Retype Password :</td>
							<td><input type="password" name="confPwd" id="confPwd" autocomplete="off"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,10}"
								title="Password and Confirm Password must match" 
								onkeypress="enable()" required><br></td>
						</tr>
					</tbody>
				</table>
				<br> <input type="submit" value="Change Password" id="submit" onClick="confSubmit(this.form)"> 
				<input type="reset" value="Clear">
			</form>
		</div>

		<div id="Add" class="tabcontent">
			&nbsp; &nbsp; &nbsp;
			<h5>Seems Wrong ..? Don't Worry We Can Make It Right !!</h5>
			<br>
			<form action="./updateAdd" method="post">
				<table>
					<tbody>
						<tr>
							<td>Current Address :</td>
							<%
								if (add != null) {
							%>
							<td><label><%=add%></label></td>

							<%
								} else {
							%>
							<td><label> No Address Can be Found</label></td>
							<%
								}
							%>

						</tr>

						<tr>
							<td>New Address :</td>
							<td><textarea type="text" rows="4" cols="50" name="address"
									maxlength="100"></textarea></td>
						</tr>
					</tbody>
				</table>
				<br> <input type="submit" value="Change Address"
					onClick="confSubmit(this.form);"> <input type="reset"
					value="Clear">
			</form>
		</div>

		<div id="Order" class="tabcontent">
			&nbsp; &nbsp; &nbsp;
			<h3>Review Order History</h3>
			<br>
			<%
				FOODY_USER_ORDERS_DAO orders;
					FOODY_ORDER_DETAILS_DAO orderDetails = null;
					ArrayList<FOODY_USER_ORDERS> currentUserOrders = null;

					orders = FOODY_USER_ORDERS_DAO.getInstance();
					orderDetails = FOODY_ORDER_DETAILS_DAO.getInstance();
					currentUserOrders = orders.getAllOrdersForUser(user);
					Map<String, List<FOODY_ORDER_DETAILS>> data = new HashMap<String, List<FOODY_ORDER_DETAILS>>();
					for (FOODY_USER_ORDERS temp : currentUserOrders) {
						data.put(temp.getOrder_id(), orderDetails.getOrderDetails(temp));
					}
			%>

			<table style="width: 100%">
				<tbody>
					<tr>
						<th>View Order</th>
						<th>Order ID</th>
						<th>Order Date & Time</th>
						<th>Total Amount (INR)</th>
					</tr>

					<!-- DB Fetched the Information -->
					<%
						if (currentUserOrders.size() >= 1) {
								for (FOODY_USER_ORDERS temp : currentUserOrders) {
					%>
					<tr>
						<td>
							<button type="button" onclick="showHide(this)">Details</button>
							<table style="display: none">
								<tbody>
									<tr>
										<th>Sl No</th>
										<th>Item Name</th>
										<th>Quantity</th>
										<th>Unit Price</th>
									</tr>
									<%
										List<FOODY_ORDER_DETAILS> details = data.get(temp.getOrder_id());
													//For Serial No
													int counter = 1;
													for (FOODY_ORDER_DETAILS obj : details) {
									%>
									<tr>
										<td><%=counter%></td>
										<td><%=obj.getOrder_item()%></td>
										<td><%=obj.getQuantity()%></td>
										<td><%=obj.getItem_unit_price()%></td>
									</tr>
									<%
										counter++;
													}
									%>
								</tbody>
							</table>
						</td>
						<td><%=temp.getOrder_id()%></td>
						<td><%=temp.getDate()%><br><%=temp.getTime()%></td>
						<td><%=temp.getSumAmt()%></td>
					</tr>
					<%
						}
							} else {
					%>
					<tr>
						<td colspan="4" align="center">No Orders Can Be Found ...</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>

	</div>


	<div class="container-fluid footer">
		<div class="container-fluid" id="footer-data">
			<h5>Powered By @Foody Technologies</h5>
		</div>
	</div>

	<script>
		function confSubmit(form) {
			if (confirm("Are you sure you want to update the data?")) {
				form.submit();
			}
		}

		function showHide(obj) {
			console.log("Inside the ONCLICK");
			var tbody = obj.parentNode.getElementsByTagName("table")[0];
			var old = tbody.style.display;
			tbody.style.display = (old == "none" ? "" : "none");
		}

		//OPEN SELECTABLE WINDOW
		function openTab(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}

		// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();

		var password = document.getElementById("password"), confirm_password = document
				.getElementById("confPwd")
		oldPwd = document.getElementById("oldPwd");

		function checkDiffer() {
			if (password.value === oldPwd.value) {
				alert("Old and New Password Can't be same");
				document.getElementById("submit").disabled = true;
			} else {
				document.getElementById("submit").disabled = false;
			}
		}
		// PASSWORD VALIDATION
		function validatePassword() {
			if (password.value != confirm_password.value) {
				alert("Password and Confirm Password must match");
				document.getElementById("submit").disabled = true;
				return false;
			} else {
				document.getElementById("submit").disabled = false;
				return true;
			}
		}
		function enable() {
			document.getElementById("submit").disabled = false;
		}

		function confSubmit(form) {
			var x = validatePassword();
			if (x) {
				if (confirm("Are you sure you want to update the data?")) {
					form.submit();
				}
			}
		}

		//POP-UP WHEN FIELD VALUE IS PROVIDED
		window.onbeforeunload = function() {
			var confirmationMessage = "\o/";
			(e || window.event).returnValue = confirmationMessage; //Gecko + IE
			return confirmationMessage; //Webkit, Safari, Chrome
		};
	</script>
</body>
<%
	} else {
		response.sendRedirect("./login");
	}
%>
</html>

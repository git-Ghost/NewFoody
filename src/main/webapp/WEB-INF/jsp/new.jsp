<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>New</title>
<link rel="shortcut icon" href="http://www.iconarchive.com/download/i99695/sonya/swarm/Fast-Food.ico">
<meta http-equiv="Refresh" content="3;url=register">
<style>
body {
  background-color: white;
  text-align: center;
  color: black;
  font-family: Arial, Helvetica, sans-serif;
}

.loader {
position:fixed;
        position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 200px;
    height: 100px;
    margin: auto;
    
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

</style>
</head>
<body>
<% response.setContentType("text/html");
			HttpSession ses = request.getSession(false);
			if(ses.getAttribute("name")==null){ %>
	
<h1>Seems like you are the first one to visit Foody.</h1>
<p>Please Wait while we Redirect You to Register page...</p>
<div class="loader"></div>
<% }else
			response.sendRedirect("./home");
%>
</body>
</html>

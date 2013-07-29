<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<title>Backend Home Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="/backend-web/static/assets/js/jquery-1.8.2.js"></script>
		<script src="/backend-web/static/assets/js/bootstrap.js"></script>
		<link href="/backend-web/static/assets/css/bootstrap.css" rel="stylesheet" />
	</head>
	<body>
		<div class="navigation">
			<div class="navbar">
				<div class="navbar-inner">
			        <div class="container">
			        	<div class="nav-collapse">
            				<ul class="nav">
			        			<li><a >Home</a></li>
			        		</ul>
			        	</div>
			        </div>
			    </div>
			</div>
		</div>
		<h1>Welcome to the Backend Page</h1>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">${user.firstName }</div>
				<div class="span6">${user.lastName }</div>
			</div>
			<c:forEach items="${messageList}" var="currentMessage">
			<div class="row-fluid">
				<div class="span6">${currentMessage.message }</div>
				<div class="span6">${currentMessage.userName }</div>
			</div>
			</c:forEach>
		</div>
	</body>
</html>

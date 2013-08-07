<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
	<head>
		<title>Backend Home Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="/backend-web/wro/vendor.js"></script>
		<link href="/backend-web/wro/vendor.css" rel="stylesheet" />
	</head>
	<body>
		<%@include file="/WEB-INF/jsp/navigation.jspf" %>
		<h1>Post A Message</h1>
		<div class="container-fluid">
			<div id="userContainer">
				<form:form action="/backend-web/main/createMessage">
					<form:textarea path="messageText"/>
					<form:hidden path="userName"/>
					<input type="submit" class="btn btn-default"></input>
				</form:form>
			</div>
			<div id="messagesContainer">
				<div class="row-fluid">
					<div class="span12">
						<h2>Your Messages</h2>
						<div class="messages">
							<c:forEach items="${messageList}" var="currentMessage">
							<div class="row-fluid">
								<div class="span6">${currentMessage.messageText }</div>
								<div class="span6">${currentMessage.userName }</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

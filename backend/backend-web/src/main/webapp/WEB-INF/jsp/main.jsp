<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<title>Backend Home Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="/backend-web/wro/vendor.js"></script>
		<link href="/backend-web/wro/vendor.css" rel="stylesheet" />
	</head>
	<body>
		<%@include file="/WEB-INF/jsp/navigation.jspf" %>
		<h1>Welcome to the Backend Page</h1>
		<div class="container-fluid">
			<div id="userContainer">
				<div class="row-fluid">
					<div class="span6">${user.firstName }</div>
					<div class="span6">${user.lastName }</div>
				</div>
			</div>
			<div id="booksContainer">
				
						<c:forEach items="${bookResults.items}" var ="volItem">
						<div class="row-fluid">
							<div class="span12">
							<a href="${volItem.volumeInfo.previewLink }">${volItem.volumeInfo.title }</a>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span6">
								<img alt="" src="${volItem.volumeInfo.imageLinks.thumbnail }">
							</div>
							<div class="span6">
								<img alt="" src="${volItem.volumeInfo.imageLinks.smallThumbnail }">
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
							${volItem.volumeInfo.description }
							</div>
						</div>
						</c:forEach>
						
					
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

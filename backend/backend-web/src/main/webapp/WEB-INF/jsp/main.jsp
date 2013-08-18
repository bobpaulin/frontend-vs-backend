<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
	<head>
		<title>Backend Home Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="/backend-web/wro/vendor.css" rel="stylesheet" />
	</head>
	<body>
		<%@include file="/WEB-INF/jsp/navigation.jspf" %>
		<h1>Welcome to the Backend Page</h1>
		<div class="container-fluid">
			<div id="preferenceContainer">
				<div class="row-fluid">
					<div class="span12">
						<div>Your Preferences</div>
						<ul>
							<c:forEach items="${bookPreferences }" var="currentPreference">
								<li>${currentPreference.keyword}</li>
							</c:forEach>
						</ul>
						<div id="newPreferenceContainer">
							<div>Add A Preference</div>
							<form:form action="/backend-web/main/createBookPreference">
								<form:input path="keyword"/>
								<form:hidden path="userName"/>
								<input type="submit" class="btn btn-default" value="Add Preference"></input>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<div id="booksContainer">
				
						<c:forEach items="${bookResults}" var ="volItem">
						<div class="row-fluid">
							<div class="span12">
							<a href="${volItem.volumeInfo.previewLink }">${volItem.volumeInfo.title }</a>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
								<img alt="" src="${volItem.volumeInfo.imageLinks.thumbnail }">
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
							${volItem.volumeInfo.description }
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
							<a href="main/review/${volItem.id }">See Reviews</a>
							</div>
						</div>
						</c:forEach>
			</div>
		</div>
		<script src="/backend-web/wro/vendor.js"></script>
	</body>
</html>

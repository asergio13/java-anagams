<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" ng-app="anagram">
<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
	<script type="text/javascript" src="../js/src/anagram.js"></script>
</head>
<body>

	<div class="container">

		<div class="starter-template">
			<h1>Anagram with SpringBoot</h1>
		</div>

	</div>
	<form method="post">
		<input type="radio" name="actionSelected" value="1"> Get Anagrams<br>
		<input type="radio" name="actionSelected" value="2"> Add word to dictionary<br>
		<input type="radio" name="actionSelected" value="3"> Delete word from dictionary<br>
		<input type="text" id="word" size="25" maxlength="25"/>
		<button type="button" onclick="alert('action called....')">Submit Word</button>
	</form>
	
	<div>
		<div ng-controller="Hello">
			<p>The content is {{AnagramResult.anagrams}}</p>
		</div>
	</div>
	
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>

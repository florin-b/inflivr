<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">


<link rel="stylesheet" type="text/css" href="css/OneMarkerStyle.css">
<link rel="stylesheet" type="text/css" href="css/MarkerLabelStyle.css">


<script
	src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBhGZckc6WAio9WiiLstQTTpVtAvQ7kIEc'></script>
<script type="text/javascript" src='scripts/markerwithlabel.js'></script>

<script type="text/javascript" src="scripts/traseuSoferi.js"></script>

<script type="text/javascript" src="scripts/mapsTraseu.js"></script>

<style>
div.map_div {
	width: 1000px;
	height: 700px;
	visibility: hidden;
}
</style>

</head>
<body>



	<section id="body" class="width clear"> <section id="content"
		class="column-right"> <br>

	<div id="contentdiv" align="center">


		
		<h1><div id="labelTraseu"></div></h1>
		<br>
		<div id="hartaTraseu" class='map_div'></div>



	</div>



	<div id="loadingScreen"></div>
	<div id="nrmasina" style="visibility: hidden">${sessionScope.nrmasina}</div>
	<div id="interval" style="visibility: hidden">${sessionScope.interval}</div>
	<input type="hidden" id="dateTraseu" value=${sessionScope.dateTraseu}>
	<div id="opriri" style="visibility: hidden">${sessionScope.opriri}</div>
</body>
</html>
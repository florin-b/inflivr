<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informare livrare</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<script type="text/javascript" src="scripts/status.common.js"></script>

</head>
<body>


	<div data-role="page" id="status" data-theme="d" data-url="">


		<div data-role="content" id="loadContent">

			<div class="ui-corner-all custom-corners">



				<div class="ui-grid-a ui-responsive">

					<div class="ui-block-a" id='clientPanel'></div>
					<br>
					<div class="ui-block-a" id='estimarePanel'></div>


				</div>

				<br>


				<div class="ui-body ui-body-a" id='mapPanel'>

					<div class="ui-grid-a ui-responsive">

						<br>

						<div class="ui-grid-a ui-responsive">
							<div id="map_canvas_status" style="height: 400px">

								<script
									src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBhGZckc6WAio9WiiLstQTTpVtAvQ7kIEc'></script>
								<script type="text/javascript" src="scripts/status.maps.js"></script>
							</div>

						</div>

						<br>

						<div class="ui-grid-a ui-responsive" style='visibility: hidden'>
							<a href="#" class="ui-btn ui-corner-all" id="afisMap"
								onclick="afiseazaHarta();">Actualizeaza</a>
						</div>



					</div>


					<div class="ui-grid-a ui-responsive" id='labelArticole'></div>


					<div id='listArticole'></div>
					<br>

				</div>

				<div id='infoPanel'>Pentru aceasta livrare nu exista
					informatii.</div>

				<br>




			</div>

		</div>
	</div>




	<div id="coordMasina" style="visibility: hidden">${sessionScope.coordMasina}</div>
	<div id="coordClient" style="visibility: hidden">${sessionScope.coordClient}</div>
	<div id="numeClient" style="visibility: hidden">${sessionScope.numeClient}</div>
	<div id="articole" style="visibility: hidden">${sessionScope.articole}</div>
	<div id="estimare" style="visibility: hidden">${sessionScope.estimare}</div>


</body>
</html>
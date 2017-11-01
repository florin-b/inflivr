function afiseazaHarta() {

	var coordMasina = $('#coordMasina').text();

	var coordClient = $('#coordClient').text();

	var pozMasina = new google.maps.LatLng(coordMasina.split(',')[0],
			coordMasina.split(',')[1]);

	var pozClient = new google.maps.LatLng(coordClient.split(',')[0],
			coordClient.split(',')[1]);

	if (coordMasina.split(',')[0] == 0) {
		$('#mapPanel').hide();
		$('#infoPanel').show();
	} else {
		$('#mapPanel').show();
		$('#infoPanel').hide();
	}

	var minZoomLevel = 12;

	var map = new google.maps.Map(document.getElementById('map_canvas_status'),
			{
				zoom : minZoomLevel,
				center : pozMasina,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				gestureHandling : 'cooperative'
			});

	var markers = [];

	var masinaMarker = new google.maps.Marker({
		position : pozMasina,
		icon : 'images/truck_icon.png',
	});

	var clientMarker = new google.maps.Marker({
		position : pozClient,
		icon : 'images/customer.png',
	});

	markers.push(masinaMarker);
	markers.push(clientMarker);

	setMapMarkers(null, markers);

	if (coordClient.split(',')[0] > 0 && coordMasina.split(',')[0] > 0)
		setMapMarkers(map, markers);
	else
		$('#map_canvas_status').hide();

}

function setMapMarkers(map, markers) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

function afiseazaArticole(listArticole) {

	var content = '<table border="0" style="width:100%;" cellpadding="5" data-role="table"  data-mode="columntoogle" class="ui-responsive table-stroke">';

	content += '<tr><th align="left">Denumire</th><th align="right">Cantitate</th><th align="left">Um</th></tr>';

	for (var i = 0; i < listArticole.length; i++) {

		content += '<tr><td>' + listArticole[i].nume + '</td><td align="right">'
				+ listArticole[i].cantitate + '</td><td align="left">' + listArticole[i].um
				+ '</td></tr>';

	}

	content += '</table>';

	$('#labelArticole').html('Comanda contine urmatoarele repere:');
	$('#listArticole').html(content);

}

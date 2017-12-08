$(document).on('pagecreate', '#status', function() {

	afiseazaHarta();
	afisClient();
	var listArticole = JSON.parse($('#articole').text());

	$('#labelArticole').html('');
	$('#listArticole').html('');

	if (listArticole.length > 0)
		afiseazaArticole(listArticole);

});

function afisClient() {
	var numeClient = $('#numeClient').text();

	var estimareSosire = $('#estimare').text();

	if (numeClient.trim().length > 0)
		$('#clientPanel').html(' ' + numeClient);
	else
		$('#clientPanel').html(' ');

	if (estimareSosire.trim().length > 0)
		$('#estimarePanel').html(
				'Livrarea se va face in aproximativ ' + numeClient);
	else
		$('#estimarePanel').html(' ');

}

function createObj() {

	var myObj = new Object();

	myObj.nume = 'Nume';
	myObj.valoare = '123';
	myObj.data = 'data1';

	return myObj;

}

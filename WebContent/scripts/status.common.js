$(document).on('pagecreate', '#status', function() {

	afiseazaHarta();
	afisClient();
	var listArticole = JSON.parse($('#articole').text());

	$('#labelArticole').html('');
	$('#listArticole').html('');

	if (listArticole.length > 0)
		afiseazaArticole(listArticole);

});

function testObjects() {

}

function afisClient() {
	var numeClient = $('#numeClient').text();

	if (numeClient.trim().length > 0)
		$('#clientPanel').html('Livrare client ' + numeClient);
	else
		$('#clientPanel').html(' ');

}

function createObj() {

	var myObj = new Object();

	myObj.nume = 'Nume';
	myObj.valoare = '123';
	myObj.data = 'data1';

	return myObj;

}

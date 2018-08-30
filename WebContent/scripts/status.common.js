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
				'Livrarea se va face in aproximativ ' + estimareSosire);
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


function loadjsfile() {

	var googleSrc = 'https://maps.googleapis.com/maps/api/js?key='
			+ getMapKey();

	var fileref = document.createElement('script');
	fileref.setAttribute("type", "text/javascript");
	fileref.setAttribute("src", googleSrc);

	document.getElementsByTagName("head")[0].appendChild(fileref)

}


function getMapKey() {

	// developer8.arabesque@gmail.com/1DeveLo@er2
	
	var key1 = "AIzaSyCxk_2WZSl-biwYYWvMA5aHHPoddCN1HEs";
	var key2 = "AIzaSyA1rDlpHO5W4i_7hs6rWjVOq0A0mkgBCE8";
	var key3 = "AIzaSyD-TLdQxa5dnjpPnznEoNj-K6cO4hMa4fM";
	var key4 = "AIzaSyBzcf2x1g-AmW-IxB3znLnb8dag8yB_exw";
	var key5 = "AIzaSyD6b99wdwt4jCckZoijw-4QUEjfgsOYuIc";
	var key6 = "AIzaSyASWJ_vN2xzimbzShHa3aygUTIKAczR5X8";
	var key7 = "AIzaSyBaT9RJ8eWNEP0bAqybJUo9cfXgnlR9JxM";

	var rnd = Math.floor((Math.random() * 7) + 1);

	var key;

	switch (rnd) {
	case 2:
		key = key2;
		break;
	case 3:
		key = key3;
		break;
	case 4:
		key = key4;
		break;
	case 5:
		key = key5;
		break;
	case 6:
		key = key6;
		break;
	case 7:
		key = key7;
		break;
	case 1:
	default:
		key = key1;
		break;

	}

	return key;

}




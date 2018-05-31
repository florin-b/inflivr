$(document)
		.ready(

				function() {

					var traseu = $('#dateTraseu').val();
					var opriri = $('#opriri').text();

					if (traseu.length == 0) {
						$('#labelTraseu').text(
								'Nu exista date pentru masina '
										+ $('#nrmasina').text()
										+ ' in perioada '
										+ $('#interval').text());
					} else {
						$('#labelTraseu').text(
								'Traseu masina ' + $('#nrmasina').text()
										+ ' in perioada '
										+ $('#interval').text());

						document.getElementById('hartaTraseu').style.visibility = "visible";
						getHartaTraseuInterval(traseu, opriri);
					}

				});

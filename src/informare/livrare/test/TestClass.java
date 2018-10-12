package informare.livrare.test;

import java.util.List;

import com.google.maps.model.LatLng;

import informare.livrare.beans.Articol;
import informare.livrare.beans.Client;
import informare.livrare.model.OperatiiBorderou;
import informare.livrare.model.OperatiiCamion;
import informare.livrare.model.OperatiiClient;
import informare.livrare.model.OperatiiCoordonate;

public class TestClass {

	public static void main(String[] args) {

		String params = "0001903761-9010149334";

		String nrBorderou = params.split("-")[0];
		String codAdresa = params.split("-")[1];

		//LatLng coordMasina = new OperatiiCoordonate().getCoordonateMasina(nrBorderou);
		
		LatLng coordMasina = new OperatiiCoordonate().getCoordonateMasina(nrBorderou, codAdresa);

		Client client;
		List<Articol> articole;

		client = new OperatiiCoordonate().getCoordonateCodAdresa(nrBorderou, codAdresa);
		
		articole = new OperatiiBorderou().getArticoleComanda(nrBorderou, codAdresa);
		
		/*
		if (codAdresa.length() == 10) {
			//client = new OperatiiCoordonate().getCoordonateCodAdresa(nrBorderou, codAdresa);
			articole = new OperatiiBorderou().getArticoleComanda(nrBorderou, codAdresa);
		} else {
			//client = new OperatiiCoordonate().getCoordonateComanda(codAdresa);
			articole = new OperatiiBorderou().getArticoleComandaGED(nrBorderou, codAdresa);
		}
		*/


		String estimareLivrare = new OperatiiClient().getTimpSosireClient_Beta(nrBorderou, codAdresa);
		
		System.out.println("Estimare livrare = " + estimareLivrare);
		
		System.out.println("Articole = " + articole);
		
		System.out.println("Client = " + client);
		

	}

}

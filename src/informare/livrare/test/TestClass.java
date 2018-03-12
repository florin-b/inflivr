package informare.livrare.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.maps.model.LatLng;

import informare.livrare.beans.Articol;
import informare.livrare.beans.Client;
import informare.livrare.database.DBManager;
import informare.livrare.model.OperatiiBorderou;
import informare.livrare.model.OperatiiClient;
import informare.livrare.model.OperatiiCoordonate;

public class TestClass {

	public static void main(String[] args) {

		// Client client = new
		// OperatiiCoordonate().getCoordonateAdresa("0001776208", "4110005312");

		// System.out.println(client);

		
		
		
		//0001798066-4110101808.
		
		
		  
		  String nrBorderou = "0001798066"; String codClient = "4110101808";
		  
		  LatLng coordMasina = new
		  OperatiiCoordonate().getCoordonateMasina(nrBorderou, codClient);
		  
		  Client client = new
		  OperatiiCoordonate().getCoordonateAdresa(nrBorderou, codClient);
		  
		  List<Articol> articole = new
		  OperatiiBorderou().getArticoleComanda(nrBorderou, codClient);
		  
		  String estimareLivrare = new
		  OperatiiClient().getTimpSosireClient(nrBorderou, codClient);
		  
		  
		  System.out.println("estimare" + estimareLivrare);
		  
		 

	}

}

package informare.livrare.test;

import com.google.maps.model.LatLng;

import informare.livrare.beans.Client;
import informare.livrare.model.OperatiiBorderou;
import informare.livrare.model.OperatiiCoordonate;

public class TestClass {

	public static void main(String[] args)
	{
		
//		System.out.println(new OperatiiCoordonate().getCoordonateMasina("0001721563"));
		
		//System.out.println(new OperatiiCoordonate().getCoordonateAdresa("0001721563", "4110030746"));
		
		Client client = new OperatiiCoordonate().getCoordonateAdresa("0001742175","4119000367");
		
		System.out.println(client);
		
		
		//LatLng coordMasina = new OperatiiCoordonate().getCoordonateMasina("0001742355");
		//System.out.println(coordMasina);
		
		//System.out.println(new OperatiiBorderou().getArticoleComanda("0001741852","4110099509"));
		
	}
	
}

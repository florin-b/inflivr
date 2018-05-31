package informare.livrare.test;

import informare.livrare.model.OperatiiCamion;

public class TestClass {

	public static void main(String[] args) {

		String dateTraseu = new OperatiiCamion().getTraseuInterval("GL07DRD", "29-05-2018 04:00", "30-05-2018 14:00");

		System.out.println(dateTraseu.split("@")[0]);
		System.out.println(dateTraseu.split("@")[1]);

	}

}

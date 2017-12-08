package informare.livrare.utils;

public class Constants {

	public static final String GOOGLE_MAPS_API_KEY = "AIzaSyA-h9799tCt5YDGAnZIKxqP-RmzPNEEo64";

	public static double getTimpStationareH(String tipMasina) {

		switch (tipMasina) {
		case "DAF_CF85":
			return (double) 30 / 60;
		case "DAF_FAR85":
			return (double) 35 / 60;
		case "DAF_LF55":
			return (double) 20 / 60;
		case "IVECO_35C":
		case "IVECO_50C":
		case "IVECO_65C":
		default:
			return (double) 15 / 60;

		}

	}

}

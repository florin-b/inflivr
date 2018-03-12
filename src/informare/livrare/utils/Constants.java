package informare.livrare.utils;

public class Constants {

	public static final String GOOGLE_MAPS_API_KEY = "AIzaSyA-h9799tCt5YDGAnZIKxqP-RmzPNEEo64";

	// developer5.arabesque@gmail.com/1DeveLo@er2
	// My Project
	public static final String GOOGLE_MAPS_API_KEY_01 = "AIzaSyBL5M27T3D4jm5Af3P74uh9C9PTxcx-504";

	// My Project1
	public static final String GOOGLE_MAPS_API_KEY_02 = "AIzaSyAQgScBJJRf_26MwprFMZoAspugfijDRh0";

	// My Project2
	public static final String GOOGLE_MAPS_API_KEY_03 = "AIzaSyDwQnqAhaBuHAGfZZAUHPf749-QbjQS8OI";

	// My Project3
	public static final String GOOGLE_MAPS_API_KEY_04 = "AIzaSyAs5TVP_kThhEKe_CSfs5v0jPtH81FQT30";

	// My Project4
	public static final String GOOGLE_MAPS_API_KEY_05 = "AIzaSyA3U9eTasEOQpmRLH9jMMxp5s3Ij7_cets";

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

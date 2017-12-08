package informare.livrare.utils;

public class UtilsAdrese {

	public static String getNumeJudet(String codJudet) {
		String retVal = "Nedefinit";

		if (codJudet.equals("01"))
			retVal = "ALBA";

		else if (codJudet.equals("02"))
			retVal = "ARAD";

		else if (codJudet.equals("03"))
			retVal = "ARGES";

		else if (codJudet.equals("04"))
			retVal = "BACAU";

		else if (codJudet.equals("05"))
			retVal = "BIHOR";

		else if (codJudet.equals("06"))
			retVal = "BISTRITA-NASAUD";

		else if (codJudet.equals("07"))
			retVal = "BOTOSANI";

		else if (codJudet.equals("09"))
			retVal = "BRAILA";

		else if (codJudet.equals("08"))
			retVal = "BRASOV";

		else if (codJudet.equals("40"))
			retVal = "BUCURESTI";

		else if (codJudet.equals("10"))
			retVal = "BUZAU";

		else if (codJudet.equals("51"))
			retVal = "CALARASI";

		else if (codJudet.equals("11"))
			retVal = "CARAS-SEVERIN";

		else if (codJudet.equals("12"))
			retVal = "CLUJ";

		else if (codJudet.equals("13"))
			retVal = "CONSTANTA";

		else if (codJudet.equals("14"))
			retVal = "COVASNA";

		else if (codJudet.equals("15"))
			retVal = "DAMBOVITA";

		else if (codJudet.equals("16"))
			retVal = "DOLJ";

		else if (codJudet.equals("17"))
			retVal = "GALATI";

		else if (codJudet.equals("52"))
			retVal = "GIURGIU";

		else if (codJudet.equals("18"))
			retVal = "GORJ";

		else if (codJudet.equals("19"))
			retVal = "HARGHITA";

		else if (codJudet.equals("20"))
			retVal = "HUNEDOARA";

		else if (codJudet.equals("21"))
			retVal = "IALOMITA";

		else if (codJudet.equals("22"))
			retVal = "IASI";

		else if (codJudet.equals("23"))
			retVal = "ILFOV";

		else if (codJudet.equals("24"))
			retVal = "MARAMURES";

		else if (codJudet.equals("25"))
			retVal = "MEHEDINTI";

		else if (codJudet.equals("26"))
			retVal = "MURES";

		else if (codJudet.equals("27"))
			retVal = "NEAMT";

		else if (codJudet.equals("28"))
			retVal = "OLT";

		else if (codJudet.equals("29"))
			retVal = "PRAHOVA";

		else if (codJudet.equals("31"))
			retVal = "SALAJ";

		else if (codJudet.equals("30"))
			retVal = "SATU-MARE";

		else if (codJudet.equals("32"))
			retVal = "SIBIU";

		else if (codJudet.equals("33"))
			retVal = "SUCEAVA";

		else if (codJudet.equals("34"))
			retVal = "TELEORMAN";

		else if (codJudet.equals("35"))
			retVal = "TIMIS";

		else if (codJudet.equals("36"))
			retVal = "TULCEA";

		else if (codJudet.equals("38"))
			retVal = "VALCEA";

		else if (codJudet.equals("37"))
			retVal = "VASLUI";

		else if (codJudet.equals("39"))
			retVal = "VRANCEA";

		return retVal;
	}

}

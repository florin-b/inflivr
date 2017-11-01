package informare.livrare.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
	public static String getStackTrace(Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}

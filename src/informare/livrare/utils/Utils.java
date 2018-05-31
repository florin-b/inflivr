package informare.livrare.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

	public static String getStackTrace(Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

	public static Date getDate(String stringDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MMM.yy HH:mm:ss", new Locale("en"));
		Date date = new Date();

		try {
			date = dateFormat.parse(stringDate);
		} catch (ParseException e) {

		}

		return date;
	}

	public static String dateDiff(Date dateStart, Date dateStop) {

		StringBuilder result = new StringBuilder();

		if (dateStart == null || dateStop == null)
			return result.toString();

		try {

			long diff = dateStop.getTime() - dateStart.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			if (diffDays > 0) {
				result.append(diffDays);
				result.append(" zile ");
			}

			if (diffHours > 0) {
				result.append(diffHours);
				result.append(" ore ");
			}

			if (diffMinutes > 0) {
				result.append(diffMinutes);

				result.append(" min");

			} else {
				if (diffMinutes != 0) {
					diffMinutes = 60 - Math.abs(diffMinutes);
					result.append(diffMinutes);

					result.append(" min");

				}
			}

		} catch (Exception e) {

		}

		return result.toString();

	}
}

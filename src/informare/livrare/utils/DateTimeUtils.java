package informare.livrare.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	public static String addDaysToDate(int nrDays) {

		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, nrDays);

		return df.format(cal.getTime());
	}

	public static String getStringTime(double time) {
		String strTime = "";

		double fracMin = time - (int) time;

		int minute = 0;

		if (fracMin < 1)
			minute = (int) (fracMin * 60);

		/*
		if (minute > 0 && minute <= 15)
			minute = 15;
		else if (minute > 15 && minute <= 30)
			minute = 30;
		else if (minute > 30 && minute <= 45)
			minute = 45;
		else if (minute > 45 && minute < 60)
			minute = 60;
*/
		if (minute == 60) {
			time++;
			minute = 0;
		}

		if ((int) time > 0) {

			String ora = "";

			if ((int) time == 1)
				ora = " ora";
			else if ((int) time > 1)
				ora = " ore";

			strTime = String.valueOf((int) time) + ora;

		}

		if (minute > 0) {
			if (!strTime.isEmpty())
				strTime += " si " + minute + " minute";
			else
				strTime += minute + " minute";
		}

		return strTime;
	}

}

package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import informare.livrare.beans.Oprire;
import informare.livrare.beans.PozitieGps;
import informare.livrare.database.DBManager;
import informare.livrare.utils.Utils;

public class OperatiiCamion {

	public String getTraseuInterval(String nrMasina, String dataStart, String dataStop) {

		String results = "", strTraseu = "";

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(getTraseuInterval());) {

			stmt.setString(1, nrMasina);
			stmt.setString(2, dataStart);
			stmt.setString(3, dataStop);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();

			int kmStart = 0, kmStop = 0, speed = 0, avgSpeed = 0, distanta = 0, maxSpeed = 0;

			List<Oprire> listOpriri = new ArrayList<Oprire>();
			Oprire oprire = null;
			Date dataStartOprire = null, dataStopOprire = null, ultimaInreg = null;

			int i = 0;
			int instantSpeed = 0;

			while (rs.next()) {

				if (i == 0)
					kmStart = rs.getInt("mileage");

				kmStop = rs.getInt("mileage");

				instantSpeed = rs.getInt("speed");

				speed += instantSpeed;

				if (instantSpeed > maxSpeed)
					maxSpeed = rs.getInt("speed");

				if (0 == instantSpeed) {
					if (oprire == null) {
						oprire = new Oprire();
						oprire.setPozitieGps(new PozitieGps(null, rs.getDouble("latitude"), rs.getDouble("longitude")));
						dataStartOprire = Utils.getDate(rs.getString("record_time"));
						oprire.setData(rs.getString("record_time"));

					}

				} else {
					if (dataStartOprire != null) {
						dataStopOprire = Utils.getDate(rs.getString("record_time"));
						oprire.setDurata(Utils.dateDiff(dataStartOprire, dataStopOprire));

						if (!oprire.getDurata().equals("1 min") && !oprire.getDurata().equals("2 min") && !oprire.getDurata().equals("3 min") && !oprire.getDurata().equals("4 min"))
							listOpriri.add(oprire);
						oprire = null;
						dataStartOprire = null;
					}
				}

				ultimaInreg = Utils.getDate(rs.getString("record_time"));

				strTraseu += "#" + String.valueOf(rs.getDouble("latitude")) + ","
						+ String.valueOf(rs.getDouble("longitude"));

				i++;

			}

			if (dataStartOprire != null) {
				oprire.setDurata(Utils.dateDiff(dataStartOprire, ultimaInreg));
				listOpriri.add(oprire);
			}

			distanta = kmStop - kmStart;

			if (i > 0)
				avgSpeed = speed / i;

			String opriri = formatOpririTraseu(listOpriri);

			results = strTraseu + "@" + opriri;

		} catch (Exception ex) {

		}

		return results;
	}

	private static String getTraseuInterval() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(
				" select x.longitude, x.latitude, x.mileage, x.speed, to_char(x.record_time,'dd.Mon.yyyy hh24:mi:ss','NLS_DATE_LANGUAGE = AMERICAN') record_time from ");
		sqlString.append(" (select longitude, latitude, mileage, speed, record_time from gps_date where device_id = ");
		sqlString.append(" (select id from gps_masini where nr_masina =?) and record_time ");
		sqlString.append(
				" between to_date(?,'dd-mm-yyyy HH24:mi') and to_date(?,'dd-mm-yyyy HH24:mi')  order by record_time) x ");

		return sqlString.toString();

	}

	private static String formatOpririTraseu(List<Oprire> listOpriri) {

		StringBuilder str = new StringBuilder();

		for (Oprire oprire : listOpriri) {
			str.append(oprire.getData());
			str.append("-");
			str.append(oprire.getDurata());
			str.append("-");
			str.append(oprire.getPozitieGps().getLatitudine());
			str.append("-");
			str.append(oprire.getPozitieGps().getLongitudine());
			str.append("!");
		}

		return str.toString();
	}
}

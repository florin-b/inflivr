package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import informare.livrare.beans.CoordonateGps;
import informare.livrare.beans.DateMasina;
import informare.livrare.beans.StareMasina;
import informare.livrare.database.DBManager;
import informare.livrare.queries.SqlQueries;
import informare.livrare.utils.DateTimeUtils;

public class OperatiiMasina {

	private static final Logger logger = LogManager.getLogger(OperatiiMasina.class);

	public double getVitezaMedie(String tipMasina, String codFiliala) {

		double vm = 0;

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getVitezaMedieMF())) {

			stmt.setString(1, DateTimeUtils.addDaysToDate(-7));
			stmt.setString(2, tipMasina);
			stmt.setString(3, codFiliala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				vm = rs.getDouble(1);
			}

		} catch (SQLException ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));
		}

		return vm;
	}

	public DateMasina getDateMasinaBord(String codBorderou) {

		DateMasina dateMasina = new DateMasina();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getDateMasinaBord())) {

			stmt.setString(1, codBorderou);
			stmt.setString(2, DateTimeUtils.addDaysToDate(-7));

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				dateMasina.setVitezaMedie(rs.getDouble(1));
				dateMasina.setTipMasina(rs.getString(2));
			}

		} catch (SQLException ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));
		}

		return dateMasina;
	}

	public StareMasina getStareMasina(String codBorderou) {

		StareMasina stareMasina = new StareMasina();
		CoordonateGps pozitie = null;

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement prep = conn.prepareStatement(SqlQueries.getPozitieMasina());) {

			prep.setString(1, codBorderou);
			prep.executeQuery();

			ResultSet rs = prep.getResultSet();

			if (rs.next()) {

				stareMasina.setKilometraj(rs.getInt("mileage"));

				pozitie = new CoordonateGps();
				pozitie.setLatitude(rs.getDouble("latitude"));
				pozitie.setLongitude(rs.getDouble("longitude"));
				stareMasina.setCoordonateGps(pozitie);
				stareMasina.setViteza(rs.getInt("speed"));

			}

		} catch (SQLException ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));
		}

		return stareMasina;
	}

}

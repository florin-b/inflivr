package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.model.LatLng;

import informare.livrare.beans.Client;
import informare.livrare.database.DBManager;
import informare.livrare.queries.SqlQueries;

public class OperatiiCoordonate {

	private static final Logger logger = LogManager.getLogger(OperatiiCoordonate.class);

	public LatLng getCoordonateMasina(String codBorderou, String codClient) {

		LatLng coords = new LatLng(0, 0);

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCoordMasinaAdresa());) {

			stmt.setString(1, codBorderou);
			stmt.setString(2, codBorderou);
			stmt.setString(3, codClient);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));

			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);

		}

		return coords;

	}

	public LatLng getCoordonateMasina(String codBorderou) {

		LatLng coords = new LatLng(0, 0);

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCoordMasinaBord());) {

			stmt.setString(1, codBorderou);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));

			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);

		}

		return coords;

	}

	public Client getCoordonateAdresa(String codBorderou, String codClient) {

		Client client = new Client();
		LatLng coords = new LatLng(0, 0);
		client.setCoords(coords);

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCoordAdresa());) {

			stmt.setString(1, codBorderou);
			stmt.setString(2, codClient);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));
				client = new Client();
				client.setCoords(coords);
				client.setNume(rs.getString("nume"));

			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);

		}

		return client;

	}

	public Client getCoordonateCodAdresa(String codBorderou, String codAdresa) {

		Client client = new Client();
		LatLng coords = new LatLng(0, 0);
		client.setCoords(coords);

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCoordCodAdresa_Beta());) {

			stmt.setString(1, codBorderou);
			stmt.setString(2, codAdresa);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));
				client = new Client();
				client.setCoords(coords);

				if (rs.getString("nume_client").trim().isEmpty() || rs.getString("nume_client").equals("-1"))
					client.setNume(rs.getString("nume"));
				else
					client.setNume(rs.getString("nume_client"));

			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);

		}

		return client;

	}

	public Client getCoordonateComanda(String idComanda) {
		Client client = new Client();
		LatLng coords = new LatLng(0, 0);
		client.setCoords(coords);

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getCoordComanda());) {

			stmt.setString(1, idComanda);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));
				client = new Client();
				client.setCoords(coords);
				client.setNume(rs.getString("nume_client"));

			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);

		}

		return client;
	}

}

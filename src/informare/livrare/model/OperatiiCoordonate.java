package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import informare.livrare.beans.AddressLite;
import informare.livrare.beans.Client;
import informare.livrare.beans.GoogleContext;
import informare.livrare.database.DBManager;
import informare.livrare.queries.SqlQueries;
import informare.livrare.utils.Utils;
import informare.livrare.utils.UtilsAdrese;

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

			int i = 0;

			while (rs.next()) {
				coords = new LatLng(rs.getDouble("latitude"), rs.getDouble("longitude"));
				client = new Client();
				client.setCoords(coords);

				if (rs.getString("nume_client").trim().isEmpty() || rs.getString("nume_client").equals("-1"))
					client.setNume(rs.getString("nume"));
				else
					client.setNume(rs.getString("nume_client"));

				i++;

			}

			if (i == 0) {
				client = getCoordonateAdresaGoogle(getAdresa(codAdresa));
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
	

	public Client getCoordonateAdresaGoogle(AddressLite adresa) {
		Client client = new Client();

		client.setNume(adresa.getNumeClient());

		String strAddress = "Romania, " + adresa.getJudet() + ", " + adresa.getLocalitate() + ", " + adresa.getStrada();

		GeoApiContext context = GoogleContext.getContextKey();

		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, strAddress).await();

			if (results.length > 0) {
				LatLng coords = new LatLng(results[0].geometry.location.lat, results[0].geometry.location.lng);
				client.setCoords(coords);
			}

		} catch (OverQueryLimitException q) {
			logger.error("getCoordonateAdresaGoogle -> " + q.toString());

		} catch (Exception e) {
			logger.error("getCoordonateAdresaGoogle -> " + Utils.getStackTrace(e));
		}

		return client;
	}

	public AddressLite getAdresa(String idAdresa) {

		AddressLite address = new AddressLite();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getAdresa());) {

			stmt.setString(1, idAdresa);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				address.setJudet(UtilsAdrese.getNumeJudet(rs.getString(1)));
				address.setLocalitate(rs.getString(2));
				address.setStrada(rs.getString(3));
				address.setNumeClient(rs.getString(4));
			}

			rs.close();

		} catch (SQLException ex) {
			logger.error(ex);
		}

		return address;

	}

}

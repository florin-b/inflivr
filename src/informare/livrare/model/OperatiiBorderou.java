package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.model.DirectionsRoute;

import informare.livrare.beans.Address;
import informare.livrare.beans.Articol;
import informare.livrare.beans.BeanClient;
import informare.livrare.beans.StareMasina;
import informare.livrare.database.DBManager;
import informare.livrare.queries.SqlQueries;
import informare.livrare.utils.DateTimeUtils;
import informare.livrare.utils.MapsUtils;

public class OperatiiBorderou {

	private static final Logger logger = LogManager.getLogger(OperatiiBorderou.class);

	public List<Articol> getArticoleComanda_old(String nrBorderou, String codClient) {
		List<Articol> listArticole = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getArticoleComanda())) {

			stmt.setString(1, nrBorderou);
			stmt.setString(2, codClient);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Articol articol = new Articol();

				articol.setNume(rs.getString("nume"));
				articol.setCantitate(rs.getString("kwmeng"));
				articol.setUm(rs.getString("vrkme"));
				listArticole.add(articol);

			}

		} catch (Exception ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));

		}

		return listArticole;
	}

	public Set<BeanClient> getClientiBorderouFromDB(String codBorderou) {

		Set<BeanClient> listClienti = new LinkedHashSet<>();
		BeanClient client = null;

		try (Connection con = new DBManager().getProdDataSource().getConnection();
				PreparedStatement prep = con.prepareStatement(SqlQueries.getClientiBorderouFromDB());) {
			prep.setString(1, codBorderou);
			prep.executeQuery();
			ResultSet rs = prep.getResultSet();

			while (rs.next()) {
				client = new BeanClient();
				client.setCodClient(rs.getString("codclient"));
				client.setCodAdresa(rs.getString("codadresa"));
				client.setDistClPrecedent(rs.getInt("distclant"));
				client.setInitKm(rs.getInt("initkm"));
				listClienti.add(client);
			}

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return listClienti;
	}

	public Set<BeanClient> getClientiBorderouOnline(String codBorderou, StareMasina stareMasina) {

		Set<BeanClient> listClienti = new LinkedHashSet<>();
		BeanClient client = null;
		Address address = null;

		try (Connection con = new DBManager().getProdDataSource().getConnection();
				PreparedStatement prep = con.prepareStatement(SqlQueries.getClientiBorderou());) {

			prep.setString(1, codBorderou);
			prep.executeQuery();
			ResultSet rs = prep.getResultSet();

			while (rs.next()) {
				client = new BeanClient();
				client.setCodClient(rs.getString("cod_client"));
				client.setNumeClient(rs.getString("nume"));

				address = new Address();
				address.setIdAdress(rs.getString("adresa_client"));
				address.setCity(rs.getString("city1"));
				address.setStreet(rs.getString("street"));
				address.setStrNumber(rs.getString("house_num1"));
				address.setSector(rs.getString("region"));
				client.setAdresa(address);

				if (!rs.getString("latitudine").equals("0"))
					client.setCoordGps(rs.getString("latitudine") + "," + rs.getString("longitudine"));
				else
					client.setCoordGps(con, rs, address);

				client.setCodAdresa(rs.getString("adresa_client"));
				client.setInitKm(stareMasina.getKilometraj());

				if (Double.valueOf(client.getCoordGps().split(",")[0]) > 0)
					listClienti.add(client);

			}

			if (!listClienti.isEmpty())
				calculeazaTraseuBorderou(stareMasina, listClienti, codBorderou);

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return listClienti;
	}

	private void calculeazaTraseuBorderou(StareMasina stareMasina, Set<BeanClient> listClienti, String codBorderou) {
		DirectionsRoute[] bordRoute = null;
		try {
			bordRoute = MapsUtils.traseuBorderou(stareMasina, listClienti);
		} catch (Exception e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		int i = 0;
		for (BeanClient client : listClienti) {
			client.setDistClPrecedent((int) bordRoute[0].legs[i].distance.inMeters / 1000);
			i++;
		}

		saveEtapeBorderou(listClienti, stareMasina, codBorderou);

	}

	private void saveEtapeBorderou(Set<BeanClient> listClienti, StareMasina stareMasina, String codBorderou) {

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.saveEtapeBorderou())) {

			int pos = 1;
			for (BeanClient client : listClienti) {
				stmt.setString(1, codBorderou);
				stmt.setInt(2, pos);
				stmt.setString(3, client.getCodClient());
				stmt.setString(4, client.getCodAdresa());
				stmt.setDouble(5, client.getDistClPrecedent());
				stmt.setDouble(6, stareMasina.getKilometraj());

				stmt.execute();

				pos++;
			}

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

	}

	public int getPozitieLivrare(String nrBorderou, String codClient) {
		int pozitieLivrare = 0;

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getPozitieLivrare())) {

			stmt.setString(1, nrBorderou);
			stmt.setString(2, codClient);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				pozitieLivrare = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return pozitieLivrare;
	}

	public int getPozitieLivrare_Beta(String nrBorderou, String codAdresa) {
		int pozitieLivrare = 0;

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getPozitieLivrare_Beta())) {

			stmt.setString(1, nrBorderou);
			stmt.setString(2, codAdresa);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				pozitieLivrare = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return pozitieLivrare;
	}	
	
	
	public static void logEstimare(String codClient, String codBorderou, String estimare) {
		try (Connection conn = new DBManager().getTestDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.addEstimare())) {

			stmt.setString(1, codClient);
			stmt.setString(2, codBorderou);
			stmt.setString(3, estimare);

			stmt.execute();

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

	}

	public List<Articol> getArticoleComandaGED(String nrBorderou, String idComanda) {
		List<Articol> listArticole = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getArticoleComandaGED())) {

			stmt.setString(1, nrBorderou);
			stmt.setString(2, idComanda);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Articol articol = new Articol();

				articol.setNume(rs.getString("nume"));
				articol.setCantitate(rs.getString("kwmeng"));
				articol.setUm(rs.getString("vrkme"));
				listArticole.add(articol);

			}

		} catch (Exception ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));

		}

		return listArticole;
	}	
	
	
	
	public List<Articol> getArticoleComanda(String nrBorderou, String codAdresa) {
		List<Articol> listArticole = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getArtComanda())) {

			stmt.setString(1, nrBorderou);
			stmt.setString(2, codAdresa);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Articol articol = new Articol();

				articol.setNume(rs.getString("nume"));
				articol.setCantitate(rs.getString("cantitate"));
				articol.setUm(rs.getString("um"));
				listArticole.add(articol);

			}

		} catch (Exception ex) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(ex));

		}

		return listArticole;
	}		
	
	
}

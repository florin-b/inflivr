package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import informare.livrare.beans.BeanClient;
import informare.livrare.beans.BeanCoordonateClient;
import informare.livrare.beans.CoordonateGps;
import informare.livrare.beans.DateMasina;
import informare.livrare.beans.StareMasina;
import informare.livrare.queries.SqlQueries;
import informare.livrare.utils.Constants;
import informare.livrare.utils.DateTimeUtils;
import informare.livrare.utils.MapsUtils;

public class OperatiiClient {

	private static final Logger logger = LogManager.getLogger(OperatiiClient.class);

	public List<BeanCoordonateClient> getCoordonateClienti(Set<BeanClient> listClienti) throws Exception {

		List<BeanCoordonateClient> listCoordonate = new ArrayList<>();

		for (BeanClient client : listClienti) {

			BeanCoordonateClient coordonata = new BeanCoordonateClient();
			coordonata.setCodClient(client.getCodClient());
			coordonata.setCoordonate(MapsUtils.geocodeAddress(client.getAdresa()));
			listCoordonate.add(coordonata);

		}

		return listCoordonate;
	}

	public void saveCoordonateAdresa(Connection conn, String idAddress, CoordonateGps coords) {

		try (PreparedStatement prep = conn.prepareStatement(SqlQueries.addCoordAdresa());) {
			prep.setString(1, idAddress);
			prep.setString(2, String.valueOf(coords.getLatitude()));
			prep.setString(3, String.valueOf(coords.getLongitude()));

			prep.execute();
		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

	}

	public void adaugaCoordonateClient(Connection conn, String codAdresa, String codClient, CoordonateGps coords) {
		try (PreparedStatement prep = conn.prepareStatement(SqlQueries.addCoordAdresaClient());) {

			prep.setString(1, codClient);
			prep.setString(2, codAdresa);
			prep.setString(3, String.valueOf(coords.getLatitude()));
			prep.setString(4, String.valueOf(coords.getLongitude()));

			prep.execute();
		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}
	}

	public boolean adresaExista(Connection conn, String idAddress) {

		boolean exista = false;

		try (PreparedStatement prep = conn.prepareStatement(SqlQueries.verificaAdresa(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			prep.setString(1, idAddress);
			prep.execute();

			prep.executeQuery();

			ResultSet rs = prep.getResultSet();

			rs.last();

			if (rs.getRow() > 0)
				exista = true;

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return exista;

	}

	public boolean adresaClientExista(Connection conn, String codAdresa, String codClient) {

		boolean exista = false;

		try (PreparedStatement prep = conn.prepareStatement(SqlQueries.verificaAdresaClient(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			prep.setString(1, codClient);
			prep.setString(2, codAdresa);
			prep.execute();

			prep.executeQuery();

			ResultSet rs = prep.getResultSet();

			rs.last();

			if (rs.getRow() > 0)
				exista = true;

		} catch (SQLException e) {
			logger.error(informare.livrare.utils.Utils.getStackTrace(e));
		}

		return exista;

	}

	public String getTimpSosireClient(String nrBorderou, String codClient) {
		String strTimp = "";
		double timpSosire = 0;

		OperatiiMasina opMasina = new OperatiiMasina();
		DateMasina dateMasina = opMasina.getDateMasinaBord(nrBorderou);

		if (dateMasina.getTipMasina() == null)
			return strTimp;

		StareMasina stareMasina = opMasina.getStareMasina(nrBorderou);

		if (stareMasina != null && stareMasina.getKilometraj() > 0) {

			Set<BeanClient> listClienti = new OperatiiBorderou().getClientiBorderouFromDB(nrBorderou);

			if (listClienti.isEmpty())
				listClienti = new OperatiiBorderou().getClientiBorderouOnline(nrBorderou, stareMasina);

			if (!listClienti.isEmpty()) {

				timpSosire = new OperatiiClient().calculeazaTimpSosireClient(listClienti, stareMasina, dateMasina,
						nrBorderou, codClient);

				strTimp = DateTimeUtils.getStringTime(timpSosire);

			}

		}

		if (timpSosire > 0 && timpSosire <= 6)
			return strTimp;
		else
			return "";

	}

	private double calculeazaTimpSosireClient(Set<BeanClient> listClienti, StareMasina stareMasina,
			DateMasina dateMasina, String nrBorderou, String codClient) {

		double timpSosireH = 0;

		int nrOpriri = new OperatiiBorderou().getPozitieLivrare(nrBorderou, codClient) - 1;

		if (nrOpriri <= 0)
			return 0;

		double distantaTotal = 0;
		double distParcursa = 0;

		for (BeanClient client : listClienti) {

			if (distantaTotal == 0 && distParcursa == 0) {
				distParcursa = stareMasina.getKilometraj() - client.getInitKm();
			}

			distantaTotal += client.getDistClPrecedent() - distParcursa;

			if (distantaTotal > 0 && distParcursa != 0)
				distParcursa = 0;
			else if (distantaTotal < 0) {
				distParcursa = Math.abs(distantaTotal);
				distantaTotal = 0;

			}

			if (distantaTotal > 0) {

				timpSosireH = (distantaTotal) / dateMasina.getVitezaMedie();

				timpSosireH += nrOpriri * Constants.getTimpStationareH(dateMasina.getTipMasina());

				if (client.getCodClient().equals(codClient))
					return timpSosireH;

			}

		}

		return 0;

	}

	public BeanClient getCoordonateAdresa() {
		return null;
	}

	public String getTimpSosireClient_Beta(String nrBorderou, String codAdresa) {
		String strTimp = "";
		double timpSosire = 0;

		OperatiiMasina opMasina = new OperatiiMasina();
		DateMasina dateMasina = opMasina.getDateMasinaBord(nrBorderou);

		if (dateMasina.getTipMasina() == null)
			return strTimp;

		StareMasina stareMasina = opMasina.getStareMasina(nrBorderou);

		if (stareMasina != null && stareMasina.getKilometraj() > 0) {

			Set<BeanClient> listClienti = new OperatiiBorderou().getClientiBorderouFromDB(nrBorderou);

			if (listClienti.isEmpty())
				listClienti = new OperatiiBorderou().getClientiBorderouOnline(nrBorderou, stareMasina);

			if (!listClienti.isEmpty()) {

				timpSosire = new OperatiiClient().calculeazaTimpSosireClient_Beta(listClienti, stareMasina, dateMasina,
						nrBorderou, codAdresa);

				strTimp = DateTimeUtils.getStringTime(timpSosire);

			}

		}

		if (timpSosire > 0 && timpSosire <= 6)
			return strTimp;
		else
			return "";

	}

	private double calculeazaTimpSosireClient_Beta(Set<BeanClient> listClienti, StareMasina stareMasina,
			DateMasina dateMasina, String nrBorderou, String codAdresa) {

		double timpSosireH = 0;

		int nrOpriri = new OperatiiBorderou().getPozitieLivrare_Beta(nrBorderou, codAdresa) - 1;

		if (nrOpriri <= 0)
			return 0;

		double distantaTotal = 0;
		double distParcursa = 0;

		for (BeanClient client : listClienti) {

			if (distantaTotal == 0 && distParcursa == 0) {
				distParcursa = stareMasina.getKilometraj() - client.getInitKm();
			}

			distantaTotal += client.getDistClPrecedent() - distParcursa;

			if (distantaTotal > 0 && distParcursa != 0)
				distParcursa = 0;
			else if (distantaTotal < 0) {
				distParcursa = Math.abs(distantaTotal);
				distantaTotal = 0;

			}

			if (distantaTotal > 0) {

				timpSosireH = (distantaTotal) / dateMasina.getVitezaMedie();

				timpSosireH += nrOpriri * Constants.getTimpStationareH(dateMasina.getTipMasina());

				if (client.getCodAdresa().equals(codAdresa))
					return timpSosireH;

			}

		}

		return 0;

	}

}

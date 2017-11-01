package informare.livrare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.Utils;

import informare.livrare.beans.Articol;
import informare.livrare.database.DBManager;
import informare.livrare.queries.SqlQueries;

public class OperatiiBorderou {

	private static final Logger logger = LogManager.getLogger(OperatiiBorderou.class);

	public List<Articol> getArticoleComanda(String nrBorderou, String codClient) {
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

}

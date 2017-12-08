package informare.livrare.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.maps.model.LatLng;

import informare.livrare.beans.Articol;
import informare.livrare.beans.Client;
import informare.livrare.model.OperatiiBorderou;
import informare.livrare.model.OperatiiClient;
import informare.livrare.model.OperatiiCoordonate;

@WebServlet("/stat")
public class StatusLivrare extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatusLivrare() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String params = request.getParameter("p");

		String nrBorderou = params.split("-")[0];
		String codClient = params.split("-")[1];

		LatLng coordMasina = new OperatiiCoordonate().getCoordonateMasina(nrBorderou, codClient);

		Client client = new OperatiiCoordonate().getCoordonateAdresa(nrBorderou, codClient);

		List<Articol> articole = new OperatiiBorderou().getArticoleComanda(nrBorderou, codClient);

		String estimareLivrare = new OperatiiClient().getTimpSosireClient(nrBorderou, codClient);

		request.getSession().setAttribute("coordMasina", coordMasina.toString());
		request.getSession().setAttribute("coordClient", client.getCoords().toString());
		request.getSession().setAttribute("numeClient", client.getNume());
		request.getSession().setAttribute("articole", new Gson().toJson(articole));
		request.getSession().setAttribute("estimare", estimareLivrare);

		request.getRequestDispatcher("/status.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private List<Articol> getArticole() {
		List<Articol> listArticole = new ArrayList<>();

		Articol articol = new Articol();
		articol.setNume("DULAP JOS89X68X39 1P/ARMOIRE");
		articol.setCantitate("3");
		articol.setUm("BUC");
		listArticole.add(articol);

		articol = new Articol();
		articol.setNume("RIGOLA SPORT N125 FANTA CURBATA");
		articol.setCantitate("5");
		articol.setUm("BUC");
		listArticole.add(articol);

		articol = new Articol();
		articol.setNume("ABS STEJAR TOBACCO 43X2");
		articol.setCantitate("15");
		articol.setUm("M2");
		listArticole.add(articol);

		articol = new Articol();
		articol.setNume("TAMPLARIE PVC APOLO EXCLUSIV");
		articol.setCantitate("2");
		articol.setUm("PUN");
		listArticole.add(articol);

		return listArticole;

	}

}

package informare.livrare.servlets;

import java.io.IOException;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String params = request.getParameter("p");

		String nrBorderou = params.split("-")[0];
		String codAdresa = params.split("-")[1];

		LatLng coordMasina = new OperatiiCoordonate().getCoordonateMasina(nrBorderou, codAdresa);

		Client client;
		List<Articol> articole;

		articole = new OperatiiBorderou().getArticoleComanda(nrBorderou, codAdresa);
		client = new OperatiiCoordonate().getCoordonateCodAdresa(nrBorderou, codAdresa);

		String estimareLivrare = new OperatiiClient().getTimpSosireClient_Beta(nrBorderou, codAdresa);

		request.getSession().setAttribute("coordMasina", coordMasina.toString());
		request.getSession().setAttribute("coordClient", client.getCoords().toString());
		request.getSession().setAttribute("numeClient", client.getNume());
		request.getSession().setAttribute("articole", new Gson().toJson(articole));
		request.getSession().setAttribute("estimare", estimareLivrare);

		if (estimareLivrare.isEmpty())
			estimareLivrare = " ";

		request.getRequestDispatcher("/status.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

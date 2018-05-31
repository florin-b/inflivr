package informare.livrare.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import informare.livrare.model.OperatiiCamion;

@WebServlet("/traseu")
public class Traseu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Traseu() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String params = request.getParameter("p");

		String nrMasina = params.split("/")[0];
		String interval1 = params.split("/")[1];
		String interval2 = params.split("/")[2];

		String dateTraseu = new OperatiiCamion().getTraseuInterval(nrMasina, interval1, interval2);

		request.getSession().setAttribute("nrmasina", nrMasina);
		request.getSession().setAttribute("interval",
				interval1.replace('-', '.') + " - " + interval2.replace('-', '.'));

		String coordTraseu;
		String opriri;
		if (dateTraseu.length() > 1) {
			coordTraseu = dateTraseu.split("@")[0];
			opriri = dateTraseu.split("@")[1];
		} else {
			coordTraseu = "";
			opriri = "";

		}

		request.getSession().setAttribute("dateTraseu", coordTraseu);
		request.getSession().setAttribute("opriri", opriri);

		request.getRequestDispatcher("/traseu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

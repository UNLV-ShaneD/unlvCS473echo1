package edu.unlv.cs673.echoteam.droid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DroidServlet
 */
public class DroidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DroidServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		// Get login data
		String username = "" + request.getParameter("username");
		String password = "" + request.getParameter("password");
		DataLogin login = new DataLogin(username, password);

		// Verify
		if (!login.isAuthenticated()) {
			writer.print("bad");
			return;
		}

		// Get request type
		String stringAction = "" + request.getParameter("action");
		Action action;
		try {
			action = Action.valueOf(stringAction);	
		} catch (IllegalArgumentException exception) {
			writer.print("invalid action");
			return;
		}

		ApplicationServices services = new ApplicationServices(login);

		switch (action) {
		case verify:
			writer.print("good");
			return;
		default:
			services.act(action);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

package com.focusdays2014.inventory_core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EANSearch
 */
public class EANSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EANSearch() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String eanTitle = "none";
			if (request.getParameter("q") != null) {
				eanTitle = this.getEANTitle(request.getParameter("q"));
			}
			response.setContentType("text/plain");
			response.setStatus(200);
			response.getWriter().write(eanTitle);
			response.flushBuffer();
		} catch (IOException e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private String getEANTitle(String content) throws IOException {
		EANTitlePlugin plugin = this.newPlugin();
		plugin.setEAN(content);
		return plugin.getEANTitle();
	}

	private EANCodecheckInfoPlugin newPlugin() {
		return new EANCodecheckInfoPlugin();
	}

}

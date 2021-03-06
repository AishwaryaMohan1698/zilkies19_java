package io.ztech.jkingsley.hrmanagement.servlets;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.jkingsley.hrmanagement.services.EmployeeLogin;

/**
 * Servlet implementation class Gateway
 */
@WebServlet(description = "Servlet to handle registration and login", urlPatterns = { "/gateway" })
public class Gateway extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Gateway() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("\nAccess Denied");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("emp-id"));
		
		BigInteger empId = BigInteger.valueOf(Long.parseLong(request.getParameter("emp-id")));
	    String password = request.getParameter("password");
	    
		EmployeeLogin employeeLogin = new EmployeeLogin();
		boolean verifiedUser = employeeLogin.isCorrect(empId, password);
		if (verifiedUser == false) {
			request.setAttribute("alertMessage", "Incorrect username or password. Failed to sign in!");
	    	request.setAttribute("visibility", "visibility: visible;");
	    	request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("user", verifiedUser);
			request.getRequestDispatcher("/dashboard").forward(request, response);
		}
	}

}

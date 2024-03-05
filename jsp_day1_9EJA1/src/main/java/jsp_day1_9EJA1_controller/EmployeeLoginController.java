package jsp_day1_9EJA1_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_day1_9EJA1_dao.EmployeeDao;
import jsp_day1_9EJA1_dto.Employee;

@WebServlet("/login")
public class EmployeeLoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
	//	long phone = Long.parseLong(req.getParameter("phone"));
		String password = req.getParameter("password");

		EmployeeDao dao = new EmployeeDao();

		 Employee employee = dao.getEmployeeByEmail(email);
	//	Employee employee = dao.getEmployeeByPhone(phone);
		if (employee != null) {
			if (password.equals(employee.getPassword())) {
				List<Employee> employees = dao.getAllEmployees();
				req.setAttribute("employees", employees);
				RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			req.setAttribute("message", "invalid credentials");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);  
		}
	}

}

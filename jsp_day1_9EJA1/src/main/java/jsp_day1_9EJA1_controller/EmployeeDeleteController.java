package jsp_day1_9EJA1_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_day1_9EJA1_dao.EmployeeDao;
import jsp_day1_9EJA1_dto.Employee;

@WebServlet("/delete")
public class EmployeeDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		EmployeeDao dao = new EmployeeDao();
		boolean result = dao.deleteEmployee(id);

		if (result) {
			List<Employee> employees = dao.getAllEmployees();
			req.setAttribute("employees", employees);
			RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}
	}

}

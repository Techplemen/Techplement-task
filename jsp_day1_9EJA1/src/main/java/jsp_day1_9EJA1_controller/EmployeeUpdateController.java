package jsp_day1_9EJA1_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp_day1_9EJA1_dao.EmployeeDao;
import jsp_day1_9EJA1_dto.Employee;

@WebServlet("/update")
public class EmployeeUpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		EmployeeDao dao = new EmployeeDao();
		Employee employee = dao.getEmployeeById(id);

		if (employee != null) {
			
				req.setAttribute("employee", employee);
				RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
				dispatcher.forward(req, resp);
			} else {
				req.setAttribute("message", "Login first to update");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.forward(req, resp);
			}
		}
	}



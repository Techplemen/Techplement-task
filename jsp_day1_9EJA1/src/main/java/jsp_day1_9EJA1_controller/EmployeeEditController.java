package jsp_day1_9EJA1_controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_day1_9EJA1_dao.EmployeeDao;
import jsp_day1_9EJA1_dto.Employee;

@WebServlet("/edit")
public class EmployeeEditController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setAddress(address);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setPassword(password);

		EmployeeDao dao = new EmployeeDao();
		Employee employee2 = dao.updateEmployee(employee);

		if (employee2 != null) {
			List<Employee> employees = dao.getAllEmployees();
			req.setAttribute("employees", employees);
			RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}
	}

}

package com.employee.controller;

import com.employe.model.Employee;
import com.employee.dao.EmployeeDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private EmployeeDAO dao = new EmployeeDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if (action == null)
			action = "list";

		try {
			switch (action) {
			case "new":
				showNewForm(req, resp);
				break;
			case "insert":
				insertEmployee(req, resp);
				break; // note: insert normally POST, but keeping here for completeness
			case "edit":
				showEditForm(req, resp);
				break;
			case "update":
				updateEmployee(req, resp);
				break;
			case "delete":
				deleteEmployee(req, resp);
				break;
			case "view":
				viewEmployee(req, resp);
				break;
			default:
				listEmployees(req, resp);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		System.out.println("action - " + action);
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // so forms with method="post" also work
    }

	private void listEmployees(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		List<Employee> list = dao.getAllEmployees();
		req.setAttribute("employeeList", list);
		req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("employee", new Employee());
		req.setAttribute("formAction", "insert");
		req.getRequestDispatcher("/jsp/form.jsp").forward(req, resp);
	}

	private void insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String department = req.getParameter("department");
		double salary= Double.parseDouble(req.getParameter("salary"));

		Employee newEmployee = new Employee();
		newEmployee.setName(name);
		newEmployee.setEmail(email);
		newEmployee.setDepartment(department);
		newEmployee.setSalary(salary);

		dao.insertEmployee(newEmployee);
		resp.sendRedirect("employees?action=list");
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Employee existingEmployee = dao.getEmployeeById(id);

		req.setAttribute("employee", existingEmployee);
		req.setAttribute("formAction", "update");
		req.getRequestDispatcher("/jsp/form.jsp").forward(req, resp);
	}

	private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String department = req.getParameter("department");
		double salary= Double.parseDouble(req.getParameter("salary"));
		Employee emp = new Employee();
		emp.setId(id);
		emp.setDepartment(department);
		emp.setEmail(email);
		emp.setName(name);
		emp.setSalary(salary);	
		dao.updateEmployee(emp);
		resp.sendRedirect("employees?action=list");
	}

	private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		dao.deleteEmployee(id);
		resp.sendRedirect("employees?action=list");
	}

	private void viewEmployee(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Employee emp = dao.getEmployeeById(id);

		req.setAttribute("employee", emp);
		req.getRequestDispatcher("/jsp/view.jsp").forward(req, resp);
	}

}

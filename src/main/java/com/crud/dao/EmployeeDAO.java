package com.crud.dao;

import java.sql.*;
import java.util.*;
import com.employe.model.Employee;
import com.employee.util.DBUtil;

public class EmployeeDAO {
	
	// CREATE
	public void insertEmployee(Employee emp) throws SQLException {
		String sql = "INSERT INTO employee (name, email, department, salary) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getEmail());
			ps.setString(3, emp.getDepartment());
			ps.setDouble(4, emp.getSalary());
			ps.executeUpdate();
		}
	}

	// READ (all)
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT id, name, email, department, salary FROM employee ORDER BY id DESC";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("department"), rs.getDouble("salary"));
				list.add(e);
			}
		}
		return list;
	}

	// READ (by id)
	public Employee getEmployeeById(int id) throws SQLException {
		String sql = "SELECT id, name, email, department, salary FROM employee WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
							rs.getString("department"), rs.getDouble("salary"));
				}
			}
		}
		return null;
	}

	// UPDATE
	public boolean updateEmployee(Employee emp) throws SQLException {
		String sql = "UPDATE employee SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getEmail());
			ps.setString(3, emp.getDepartment());
			ps.setDouble(4, emp.getSalary());
			ps.setInt(5, emp.getId());
			int rows = ps.executeUpdate();
			return rows > 0;
		}
	}

	// DELETE
	public boolean deleteEmployee(int id) throws SQLException {
		String sql = "DELETE FROM employee WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			return rows > 0;
		}
	}
}

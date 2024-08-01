package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection con;

	public DepartmentDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO department(Name) VALUES (?)");
			ps.setString(1, obj.getName());
			ps.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM department WHERE Id = ?");
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM department WHERE Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				Department dep = instantiateDepartment(rs);
				return dep;
			} else 
				return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Department> findAll() {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Department> list = new ArrayList<Department>();
			try {
				ps = con.prepareStatement("SELECT * FROM department");
				rs = ps.executeQuery();
				while (rs.next()) { 
					Department dep = instantiateDepartment(rs);
					list.add(dep);
				} 
				return list;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeResultSet(rs);
				DB.closeStatement(ps);
			}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
}

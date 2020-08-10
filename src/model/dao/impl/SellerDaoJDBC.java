package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery(); //executa a query e armazena a linha com as informações do ID no ResultSet
			
			if(rs.next()) { //confere se o Id existe
				Department dep = instantiateDdepartment(rs); //cria o objeto do tipo Department e adiciona os parametros
				Seller obj = instantiateDdepartment(rs, dep); //cria o objeto do tipo Seller
								
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateDdepartment(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setSalalry(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		
		return obj;
	}

	private Department instantiateDdepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId")); 
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery(); //executa a query e armazena a linha com as informações do ID no ResultSet
			
			Map<Integer, Department> map = new HashMap<>();
			List<Seller> list = new ArrayList<>();
			
			while(rs.next()) { //confere se o Id existe
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDdepartment(rs); //cria o objeto do tipo Department e adiciona os parametros
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateDdepartment(rs, dep); //cria o objeto do tipo Seller
								
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery(); //executa a query e armazena a linha com as informações do ID no ResultSet
			
			Map<Integer, Department> map = new HashMap<>();
			List<Seller> list = new ArrayList<>();
			
			while(rs.next()) { //confere se o Id existe
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDdepartment(rs); //cria o objeto do tipo Department e adiciona os parametros
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateDdepartment(rs, dep); //cria o objeto do tipo Seller
								
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}

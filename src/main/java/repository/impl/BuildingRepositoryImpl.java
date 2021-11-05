package repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import Entity.BuildingEntity;
import constant.SystemConstant;
import repository.BuildingRepository;

public class BuildingRepositoryImpl implements BuildingRepository {

	private final String DB_URL = "jdbc:mysql://localhost:3306/javasql092021";
	private final String USER = "root";
	private final String PASS = "123456";

	@Override
	public List<BuildingEntity> getBuilding() {
//		int id
		Connection conn = null;
		// Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildings = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");
				// stmt = coon.createStatement();
				StringBuilder sql = new StringBuilder("select * from building where 1 = 1");
				stmt = conn.prepareStatement(sql.toString());
				rs = stmt.executeQuery();
				while (rs.next()) {
					BuildingEntity buildingBean = new BuildingEntity();
					buildingBean.setId(rs.getInt("id"));
					buildingBean.setName(rs.getString("name"));
					buildingBean.setStreet(rs.getString("street"));
					buildingBean.setBuldingType(rs.getString("buildingType"));
					buildings.add(buildingBean);
				}
				return buildings;
			}
		} catch (ClassNotFoundException | SQLException | ArithmeticException e) {
			e.printStackTrace();
//			return new ArrayList<>();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}
	@Override
	public List<BuildingEntity> buildingSearch(String name, String street) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildings = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");
				StringBuilder sql = new StringBuilder("select buildingType from building");
				if (SystemConstant.TANGTRET != null) {
					sql.append(" and name = ?");
				}

				if (SystemConstant.TANGTRET_NGUYENCAN_NOITHAT_CODE != null) {
					sql.append(" and street = ?");
				}
						
				stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, name);
				stmt.setString(2, street);
				rs = stmt.executeQuery();
				while (rs.next()) {
					BuildingEntity buildingBean = new BuildingEntity();
					buildingBean.setId(rs.getInt("id"));
					buildingBean.setName(rs.getString("name"));
					buildingBean.setStreet(rs.getString("street"));
					buildingBean.setBuldingType(rs.getString("buildingType"));
					buildings.add(buildingBean);
				}
				return buildings;
			}
		} catch (ClassNotFoundException | SQLException | ArithmeticException e) {
			e.printStackTrace();
//			return new ArrayList<>();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}


	@Override
	public List<BuildingEntity> getBuildingByStaff() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void save(BuildingEntity entity) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void save(BuildingEntity entity) {
//		Connection conn = null;
//		// Statement stmt = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		List<BuildingEntity> buildings = new ArrayList<>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			conn.setAutoCommit(false);
//			if (conn != null) {
//				System.out.println("Connection database");
//				// stmt = coon.createStatement();
//				StringBuilder sql = new StringBuilder(
//						"insert into building(id, name, street, buildingType) value(?,?,?,?)");
//				stmt = conn.prepareStatement(sql.toString());
//				stmt.setInt(1, entity.getId());
//				stmt.setString(2, entity.getName());
//				stmt.setString(3, entity.getStreet());
//				stmt.setString(4, entity.getBuldingType());
//				stmt.executeUpdate();
//				conn.commit();
////				StringBuilder sql1 = new StringBuilder("insert into building(name, street) value(?,?)");
////				stmt = conn.prepareStatement(sql1.toString());
////				stmt.setString(1, "test4");
////				stmt.setString(2, "abc4");
////				stmt.executeUpdate();
////				conn.commit();
//			}
//		} catch (ClassNotFoundException | SQLException | ArithmeticException e) {
//			e.printStackTrace();
////			return new ArrayList<>();
//		} finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (rs != null) {
//					rs.close();
//				}
//				conn.rollback();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

}

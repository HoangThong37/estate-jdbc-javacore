package com.laptrinhjava.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.xml.ws.AsyncHandler;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.repository.BuildingRepository;
import com.laptrinhjava.utils.DataUtils;
import com.laptrinhjava.utils.StringUtils;

public class BuildingRepositoryImpl implements BuildingRepository {

	private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private final String USER = "root";
	private final String PASS = "123456";

	@Override
	public List<BuildingEntity> getBuilding(BuildingRequestDto dto) {
		Connection conn = null;
		;
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildingSearch = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");

				StringBuilder sql = new StringBuilder(
						"SELECT b.name,b.floorarea,b.street,b.ward,ds.code,b.rentprice,b.brokeragefee,b.servicefee,b.districtid \r\n"
								+ "FROM building as b\r\n" + "LEFT JOIN rentarea as ra on b.id = ra.buildingid\r\n"
								+ "LEFT JOIN buildingrenttype as brt on b.id = brt.buildingid\r\n"
								+ "LEFT JOIN district as ds on b.districtid = ds.id \r\n"
								+ "LEFT JOIN assignmentbuilding as asbd on b.id = asbd.buildingid\r\n"
								+ "INNER JOIN renttype as rt on rt.id = brt.renttypeid\r\n" + "WHERE 1 = 1 \r\n"
								+ "group by b.name");

				if (!StringUtils.isNullOrEmpty(dto.getName())) {
					sql.append(" and b.name = ?");
				}
				if (!StringUtils.isNullOrEmpty(dto.getStreet())) {
					sql.append(" and b.street = ? ");
				}
				if (!StringUtils.isNullOrEmpty(dto.getWard())) {
					sql.append(" and b.ward = ? ");
				}
				if (dto.getFloorArea() != null) {
					sql.append(" and b.floorarea = ? ");
				}
				if (dto.getRentPriceFrom() != null) {
					sql.append(" and b.rentPriceFrom = ? ");
				}
				if (dto.getRentPriceTo() != null) {
					sql.append(" and b.rentPriceTo = ? ");
				}

				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql.toString());
				while (rs.next()) {
					BuildingEntity building = new BuildingEntity();
					building.setName(rs.getString("b.name"));
					building.setStreet(rs.getString("b.street"));
					building.setWard(rs.getString("b.ward"));
					building.setDistrictid(rs.getInt("b.districtid"));
					building.setRentprice(rs.getInt("b.rentprice"));
					building.setBrokeragefee(rs.getInt("b.brokeragefee")); // môi giới
					building.setServicefee(rs.getInt("b.servicefee"));
					building.setFloorarea(rs.getInt("b.floorarea"));
//					building.setDirection(rs.getString("b.direction"));
//					building.setFloorarea(rs.getInt("b.floorarea"));
					buildingSearch.add(building);
				}
				return buildingSearch;
			}
		} catch (SQLException | ArithmeticException e) {
			e.printStackTrace();
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
	public List<BuildingEntity> getBuildingSearch(BuildingRequestDto dto) {
		Connection conn = null;
		PreparedStatement stmt = null; // xử lý
		ResultSet rs = null; // kết quả
		List<BuildingEntity> buildings = new ArrayList<>();
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");
				StringBuilder sql = new StringBuilder(
						"SELECT b.name,b.floorarea,b.street,b.ward,ds.code,b.rentprice,b.brokeragefee,b.servicefee \r\n"
								+ "FROM building as b\r\n" + "LEFT JOIN rentarea as ra on b.id = ra.buildingid\r\n"
								+ "LEFT JOIN buildingrenttype as brt on b.id = brt.buildingid\r\n"
								+ "LEFT JOIN district as ds on b.districtid = ds.id \r\n"
								+ "LEFT JOIN assignmentbuilding as asbd on b.id = asbd.buildingid\r\n"
								+ "INNER JOIN renttype as rt on rt.id = brt.renttypeid\r\n" + "WHERE 1 = 1 \r\n"
								+ "group by b.name and rt.code like '%tang-tret%'\r\n"
								+ "and bd.name like '%Nam Giao%'\r\n" + "and bd.floorarea = 500\r\n"
								+ "and ds.code = 'Q1'\r\n" + "and bd.ward like '%phường%' \r\n"
								+ "and bd.street like '%Phan%' \r\n" + "and bd.numberofbasement = 2\r\n"
								+ "and bd.direction is null\r\n" + "and bd.level is null\r\n" + "and ra.value >= 0\r\n"
								+ "and ra.value <= 200\r\n" + "and bd.rentprice >= 10\r\n"
								+ "and bd.rentprice <= 20\r\n" + "and asbd.staffid = 2\r\n" + "group by bd.name");

				if (!StringUtils.isNullOrEmpty(dto.getName())) {
					sql.append(" and b.name = ?");
				}
//				if(!StringUtils.isNullOrEmpty(dto.getStaffId())) {
//					sql.append(" and b.staffId = ? ");
//				}
				if (!StringUtils.isNullOrEmpty(dto.getWard())) {
					sql.append(" and b.ward = ? ");
				}
				if (!StringUtils.isNullOrEmpty(dto.getStreet())) {
					sql.append(" and b.street = ? ");
				}
				if (!StringUtils.isNullOrEmpty(dto.getDirection())) {
					sql.append(" and b.direction = ? ");
				}
				if (!StringUtils.isNullOrEmpty(dto.getLevel())) {
					sql.append(" and b.level  = ? ");
				}
				if (!StringUtils.isNullOrEmpty(dto.getDistrictCode())) {
					sql.append(" and b.districtid  = ? ");
				}
				if (dto.getFloorArea() != null) {
					sql.append(" and b.floorarea = ? ");
				}
				if (dto.getNumberOfBasements() != null) {
					sql.append(" and b.numberofbasement = ? ");
				}

				if (dto.getAreaFrom() != null) {
					sql.append(" and b.areaFrom = ? ");
				}
				if (dto.getAreaTo() != null) {
					sql.append(" and b.areaTo = ? ");
				}
				if (dto.getRentPriceFrom() != null) {
					sql.append(" and b.rentPriceFrom = ? ");
				}
				if (dto.getRentPriceTo() != null) {
					sql.append(" and b.rentPriceTo = ? ");
				}
			
				stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, dto.getName());
				stmt.setString(2, dto.getWard());
				stmt.setString(3, dto.getStreet());
				stmt.setString(4, dto.getDirection());
				stmt.setString(5, dto.getLevel());
				stmt.setString(6, dto.getDistrictCode());
				stmt.setString(7, dto.getFloorArea());
				stmt.setString(8, dto.getNumberOfBasements());
				stmt.setString(9, dto.getAreaFrom());
				stmt.setString(10, dto.getAreaTo());
				stmt.setString(11, dto.getRentPriceFrom());
				stmt.setString(12, dto.getRentPriceTo());
				rs = stmt.executeQuery();
				
				while (rs.next()) { // hứng kết quả
					BuildingEntity buildingBean = new BuildingEntity();
					buildingBean.setName(rs.getString("name"));
					buildingBean.setWard(rs.getString("ward")); 
					buildingBean.setStreet(rs.getString("street"));
					buildingBean.setDirection(rs.getString("direction"));
					buildingBean.setLevel(rs.getString("level"));
					buildingBean.setDistrictid(rs.getInt("districtid"));
					buildingBean.setFloorarea(rs.getInt("floorarea"));
					buildingBean.setNumberofbasement(rs.getInt("numberofbasement"));
//					buildingBean.setAreaFrom(rs.getInt("areaFrom"));
//					buildingBean.setArea
//					buildingBean.setRent(rs.getInt("rentPriceFrom"));
//					buildingBean.setNumberofbasement(rs.getInt("rentPriceTo"));
					buildings.add(buildingBean);
				}
				return buildings;
			}
		} catch (SQLException | ArithmeticException e) {
			e.printStackTrace();
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
}

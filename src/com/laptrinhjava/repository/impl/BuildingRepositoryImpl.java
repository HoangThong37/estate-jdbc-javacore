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
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.repository.BuildingRepository;
import com.laptrinhjava.utils.DataUtils;
import com.laptrinhjava.utils.MapUtils;
import com.laptrinhjava.utils.StringUtils;
import com.laptrinhjava.utils.Validation;

public class BuildingRepositoryImpl implements BuildingRepository {

	private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private final String USER = "root";
	private final String PASS = "123456";

	public static String buildingTypeCombine(String[] buildingType) { // ket hop cac loai toa nha
		List<String> result = new ArrayList<>();
		for (String item : buildingType) {
			String string = " rt.code like '%" + item + "%'";
			result.add(string);
		}
		return String.join(" or ", result);
	}

	@Override
	public List<BuildingEntity> buildingSearch(Map<String, Object> params ,List<String> types) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildingSearch = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");
				StringBuilder sqlBefore = new StringBuilder("SELECT bd.name,bd.floorarea,bd.street,bd.ward,ds.code,"
						+ "bd.rentprice,bd.brokeragefee,bd.servicefee\r\n"
						+ "FROM building as bd INNER JOIN district as ds on bd.districtid = ds.id");
				StringBuilder sqlAfter = new StringBuilder("WHERE 1 = 1\n");
				String sqlFinal = querySQL(params, types, sqlBefore, sqlAfter).toString();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlFinal);
				while (rs.next()) {				
					BuildingEntity building = new BuildingEntity();
                    building.setDistrictid(rs.getInt("b.districtid"));
                    building.setName(rs.getString("b.name"));
                    building.setStreet(rs.getString("b.street"));
                    building.setWard(rs.getString("b.ward"));
                    building.setRentprice(rs.getInt("b.rentprice"));
                    building.setBrokeragefee(rs.getInt("b.brokeragefee"));
                    building.setFloorarea(rs.getInt("b.floorarea"));
                    building.setServicefee(rs.getInt("b.servicefee"));
                    buildingSearch.add(building);
				}
			}
			return buildingSearch;

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

	private Object querySQL(Map<String, Object> params, List<String> types, StringBuilder sqlBefore,
			StringBuilder sqlAfter) {
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "districtid"))) {
			sqlAfter.append("and ds.code like '%" + MapUtils.getValue(params, "districtid") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "name"))) {
			sqlAfter.append("and bd.name like '%" + MapUtils.getValue(params, "name") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "floorarea"))) {
			sqlAfter.append("and bd.floorarea like '%" + MapUtils.getValue(params, "floorarea") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "street"))) {
			sqlAfter.append("and bd.street like '%" + MapUtils.getValue(params, "street") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "ward"))) {
			sqlAfter.append("and bd.ward like '%" + MapUtils.getValue(params, "ward") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "direction"))) {
			sqlAfter.append("and bd.direction like '%" + MapUtils.getValue(params, "direction") + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "level"))) {
			sqlAfter.append("and bd.level like '%" + MapUtils.getValue(params, "level") + "%'\n");
		}
		
		// areafrom - areato
		if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "areafrom")) 
				|| !StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "areato"))) {
			
			sqlBefore.append("INNER JOIN rentarea as ra on bd.id = ra.buildingid\r\n");
			if (MapUtils.getValue(params, "areafrom") != null ) {
				sqlAfter.append("and ra.value >= " + MapUtils.getValue(params, "areafrom") + "\n");
			}
			if (MapUtils.getValue(params, "areato") != null ) {
				sqlAfter.append("and ra.value <= " + MapUtils.getValue(params, "areato") + "\n");
			}
		}
		// numberofbasement()
		 if ((MapUtils.getValue(params, "numberofbasement") != null)) {
			sqlAfter.append("and bd.numberofbasement = " + Integer.parseInt(MapUtils.getValue(params, "numberofbasement").toString() + "\n"));
		}
		 
		// RentPriceFrom() and RentPriceTo()
	
	        if (MapUtils.getValue(params, "rentPriceFrom") != null) {
	        	sqlAfter.append("and bd.rentprice <= " + Integer.parseInt(MapUtils.getValue(params, "rentPriceFrom").toString() + "\n"));
	        }
	        if (MapUtils.getValue(params, "rentPriceTo") != null) {
	        	sqlAfter.append("and bd.rentprice <= " + Integer.parseInt(MapUtils.getValue(params, "rentPriceTo").toString() + "\n"));
	        }
		//  StaffId
		if (MapUtils.getValue(params, "getStaffId") != null) {
			sqlBefore.append("INNER JOIN assignmentbuilding as asbd on bd.id = asbd.buildingid\n");
			sqlAfter.append("and asbd.staffId =  " + MapUtils.getValue(params, "getStaffId") + "\n");
		}
		// BuildingTypes()
		 if (types != null && !types.isEmpty()) {
	            sqlAfter.append(" and (");
	            int i = 0;
	            for (String item : types) {
	            	types.set(i, " rt.code ='" + item + "'");
	                i++;
	            }
	            String sqlType = String.join(" or ", types);

	            sqlAfter.append(sqlType + ")");
	           
	        }
	        return sqlAfter;
	}}
//		if (dto.getBuildingTypes().length > 0) {
//			sqlBefore.append(" INNER JOIN buildingrenttype as brt on bd.id = brt.buildingid \r\n"
//					+ " INNER JOIN renttype as rt on rt.id = brt.renttypeid");
//			sqlAfter.append("and" + buildingTypeCombine(dto.getBuildingTypes()) + "\n");
//		}
//		sqlAfter.append("group by bd.id");
//		return sqlBefore.append(sqlAfter);
//	}
	

//	private StringBuilder querySQL(, StringBuilder sqlBefore, StringBuilder sqlAfter) {
//		
//}

//System.out.println("Câu query : " + sqlFinal);
//System.out.println("Data trả về : ");
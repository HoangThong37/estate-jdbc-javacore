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
import com.laptrinhjava.utils.ValidateUtils;

public class BuildingRepositoryImpl implements BuildingRepository {

	private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private final String USER = "root";
	private final String PASS = "123456";

//	public static String buildingTypeCombine(String[] buildingType) { // ket hop cac loai toa nha
//		List<String> result = new ArrayList<>();
//		for (String item : buildingType) {
//			String string = " rt.code like '%" + item + "%'";
//			result.add(string);
//		}
//		return String.join(" or ", result);
//	}

	@Override
	public List<BuildingEntity> buildingSearch(Map<String, Object> params, List<String> types) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildingSearch = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println(" Kết nối thành công ");
				StringBuilder sql = new StringBuilder("SELECT bd.name,bd.floorarea,bd.street,bd.ward,bd.districtid,"
						+ "bd.rentprice,bd.brokeragefee,bd.servicefee\r\n"
						+ "FROM building as bd INNER JOIN district as ds on bd.districtid = ds.id");
				StringBuilder joinSql = new StringBuilder();
				StringBuilder whereSql = new StringBuilder();
				buildingSqlWithJoin(params, types, whereSql, joinSql);
				buildingSqlWithOutJoin(params, whereSql);
				sql.append(joinSql).append(" where 1=1 ").append(whereSql).append(" GROUP BY bd.id");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql.toString());
				
				while (rs.next()) {   // hứng kết quả
					BuildingEntity building = new BuildingEntity();
					building.setDistrictid(rs.getInt("bd.districtid"));
					building.setName(rs.getString("bd.name"));
					building.setStreet(rs.getString("bd.street"));
					building.setWard(rs.getString("bd.ward"));
					building.setRentprice(rs.getInt("bd.rentprice"));
					building.setBrokeragefee(rs.getInt("bd.brokeragefee"));
					building.setFloorarea(rs.getInt("bd.floorarea"));
					building.setServicefee(rs.getInt("bd.servicefee"));
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

	private void buildingSqlWithOutJoin(Map<String, Object> params, StringBuilder whereSql) {
		if (ValidateUtils.isValid(params.get("name"))) {
			whereSql.append("and bd.name like '%").append(params.get("name")).append("%'");
		}
		if (ValidateUtils.isValid(params.get("street"))) {
			whereSql.append("and bd.street like '%").append(params.get("street")).append("%'");
		}
		if (ValidateUtils.isValid(params.get("ward"))) {
			whereSql.append("and bd.ward like '%").append(params.get("ward")).append("%'");
		}
		if (ValidateUtils.isValid(params.get("level"))) {
			whereSql.append(" and bd.level like '%").append(params.get("level")).append("%'");
		}
		if (ValidateUtils.isValid(params.get("direction"))) {
			whereSql.append(" and bd.direction like '%").append(params.get("direction")).append("%'");
		}
		// floorarea
		if (ValidateUtils.isValid(params.get("floorArea"))) {
			whereSql.append(" and bd.floorarea =").append(params.get("floorArea"));
		}
		// rentprice
		if (ValidateUtils.isValid(params.get("rentPriceFrom"))) {
			whereSql.append(" and bd.rentprice >= ").append(params.get("rentPriceFrom"));
		}
		if (ValidateUtils.isValid(params.get("rentPriceTo"))) {
			whereSql.append(" and bd.rentprice <= ").append(params.get("rentPriceTo"));
		}

		// numberofbasement
		if (ValidateUtils.isValid(params.get("numberofbasement"))) {
			whereSql.append("and bd.numberofbasement = ").append(params.get("numberofbasement"));
		}

	}

	private void buildingSqlWithJoin(Map<String, Object> params, List<String> types, StringBuilder whereSql,
			StringBuilder joinSql) {
		if (ValidateUtils.isValid(params.get("AreaRentFrom")) || ValidateUtils.isValid(params.get("AreaRentTo"))) {
			joinSql.append(" inner join rentarea as ra on ra.id = bd.id");
			if (ValidateUtils.isValid(params.get("AreaRentFrom"))) {
				whereSql.append(" ra.value >=").append(params.get("AreaRentFrom"));
			}
			if (ValidateUtils.isValid(params.get("AreaRentTo"))) {
				whereSql.append(" ra.value <=").append(params.get("AreaRentTo"));
			}
		}

		if (ValidateUtils.isValid(params.get("AreaRentFrom")) || ValidateUtils.isValid(params.get("AreaRentTo"))) {
			joinSql.append(" inner join rentarea as ra on ra.id = bd.id");
			if (ValidateUtils.isValid(params.get("AreaRentFrom"))) {
				whereSql.append(" ra.value >=").append(params.get("AreaRentFrom"));
			}
			if (ValidateUtils.isValid(params.get("AreaRentTo"))) {
				whereSql.append(" ra.value <=").append(params.get("AreaRentTo"));
			}
		}
		if (ValidateUtils.isValid(params.get("staffId"))) {
			joinSql.append(" inner join assignmentbuilding as ab on ab.buildingid = bd.id inner join user as u on ab.staffid = u.id ");
			whereSql.append(" and staffid = ").append(params.get("staffId"));
		}
		
		// BuildingTypes()
		if (types != null && !types.isEmpty()) {
			joinSql.append(" inner join buildingrenttype as brt on brt.buildingid = bd.id\n "
					+ " inner join renttype as rt on brt.renttypeid = rt.id");
			List<String> buildingtypes = new ArrayList<>();
			for (String item : types) {
				buildingtypes.add(" rt.code like '%" + item + "%'");
			}
			whereSql.append(" and( ").append(String.join(" OR ", buildingtypes)).append(")");
		}
//		return whereSql;
	}
}

/*
 * private Object querySQL(Map<String, Object> params, List<String> types,
 * StringBuilder sqlBefore, StringBuilder sqlAfter) { if
 * (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params,
 * "districtid"))) { sqlAfter.append("and ds.code like '%" +
 * MapUtils.getValue(params, "districtid") + "%'\n"); } if
 * (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "name"))) {
 * sqlAfter.append("and bd.name like '%" + MapUtils.getValue(params, "name") +
 * "%'\n"); } if (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params,
 * "floorarea"))) { sqlAfter.append("and bd.floorarea like '%" +
 * MapUtils.getValue(params, "floorarea") + "%'\n"); } if
 * (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "street"))) {
 * sqlAfter.append("and bd.street like '%" + MapUtils.getValue(params, "street")
 * + "%'\n"); } if (!StringUtils.isNullOrEmpty((String)
 * MapUtils.getValue(params, "ward"))) { sqlAfter.append("and bd.ward like '%" +
 * MapUtils.getValue(params, "ward") + "%'\n"); } if
 * (!StringUtils.isNullOrEmpty((String) MapUtils.getValue(params, "direction")))
 * { sqlAfter.append("and bd.direction like '%" + MapUtils.getValue(params,
 * "direction") + "%'\n"); } if (!StringUtils.isNullOrEmpty((String)
 * MapUtils.getValue(params, "level"))) { sqlAfter.append("and bd.level like '%"
 * + MapUtils.getValue(params, "level") + "%'\n"); }
 * 
 * // areafrom - areato if (!StringUtils.isNullOrEmpty((String)
 * MapUtils.getValue(params, "areafrom")) || !StringUtils.isNullOrEmpty((String)
 * MapUtils.getValue(params, "areato"))) {
 * 
 * sqlBefore.append("INNER JOIN rentarea as ra on bd.id = ra.buildingid\r\n");
 * if (MapUtils.getValue(params, "areafrom") != null ) {
 * sqlAfter.append("and ra.value >= " + MapUtils.getValue(params, "areafrom") +
 * "\n"); } if (MapUtils.getValue(params, "areato") != null ) {
 * sqlAfter.append("and ra.value <= " + MapUtils.getValue(params, "areato") +
 * "\n"); } } // numberofbasement() if ((MapUtils.getValue(params,
 * "numberofbasement") != null)) { sqlAfter.append("and bd.numberofbasement = "
 * + Integer.parseInt(MapUtils.getValue(params, "numberofbasement").toString()+
 * "\n" )); }
 * 
 * // RentPriceFrom() and RentPriceTo()
 * //////////////////////////////////////////////
 * 
 * if (MapUtils.getValue(params, "rentPriceFrom") != null) {
 * sqlAfter.append("and bd.rentprice <= " +
 * Integer.parseInt(MapUtils.getValue(params, "rentPriceFrom").toString() +
 * "\n")); } if (MapUtils.getValue(params, "rentPriceTo") != null) {
 * sqlAfter.append("and bd.rentprice <= " +
 * Integer.parseInt(MapUtils.getValue(params, "rentPriceTo").toString() +
 * "\n")); } // StaffId if (MapUtils.getValue(params, "getStaffId") != null) {
 * sqlBefore.
 * append("INNER JOIN assignmentbuilding as asbd on bd.id = asbd.buildingid\n");
 * sqlAfter.append("and asbd.staffId =  " + MapUtils.getValue(params,
 * "getStaffId") + "\n"); } // BuildingTypes() if (types != null &&
 * !types.isEmpty()) { sqlAfter.append(" and ("); int i = 0; for (String item :
 * types) { types.set(i, " rt.code ='" + item + "'"); i++; } String sqlType =
 * String.join(" or ", types);
 * 
 * sqlAfter.append(sqlType + ")");
 * 
 * } return sqlAfter; }}
 */
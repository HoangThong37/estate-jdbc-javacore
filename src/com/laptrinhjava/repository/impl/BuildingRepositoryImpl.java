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
	public List<BuildingResponse> buildingSearch(BuildingRequestDto dto) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingResponse> buildingSearch = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Connection database");
				StringBuilder sqlBefore = new StringBuilder("SELECT bd.name,bd.floorarea,bd.street,bd.ward,ds.code,"
						+ "bd.rentprice,bd.brokeragefee,bd.servicefee\r\n"
						+ "FROM building as bd INNER JOIN district as ds on bd.districtid = ds.id");
				StringBuilder sqlAfter = new StringBuilder("WHERE 1 = 1\n");
				String sqlFinal = querySQL(dto, sqlBefore, sqlAfter).toString();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlFinal);
				while (rs.next()) {
					BuildingResponse buildingResponse = new BuildingResponse();
					buildingResponse.setName(rs.getString("name"));

					Map<String, String> districtType = DataUtils.getTypeDistrict();
					String districtName = districtType.get(rs.getString("code"));

					buildingResponse
							.setAddress(rs.getString("street") + "," + rs.getString("ward") + "," + districtType);
					buildingResponse.setFloorarea(rs.getString("floorarea"));
					buildingResponse.setRentprice(rs.getString("rentprice"));
					buildingSearch.add(buildingResponse);
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

	private StringBuilder querySQL(BuildingRequestDto dto, StringBuilder sqlBefore, StringBuilder sqlAfter) {
		if (!StringUtils.isNullOrEmpty(dto.getDistrictCode())) {
			sqlAfter.append("and ds.code like '%" + dto.getDistrictCode() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getName())) {
			sqlAfter.append("and bd.name like '%" + dto.getName() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getFloorArea())) {
			sqlAfter.append("and bd.floorarea like '%" + dto.getFloorArea() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getFloorArea())) {
			sqlAfter.append("and bd.street like '%" + dto.getStreet() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getWard())) {
			sqlAfter.append("and bd.ward like '%" + dto.getWard() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getWard())) {
			sqlAfter.append("and bd.ward like '%" + dto.getWard() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getDirection())) {
			sqlAfter.append("and bd.direction like '%" + dto.getDirection() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getLevel())) {
			sqlAfter.append("and bd.level like '%" + dto.getLevel() + "%'\n");
		}
		if (!StringUtils.isNullOrEmpty(dto.getAreaFrom()) || !StringUtils.isNullOrEmpty(dto.getAreaTo())) {
			sqlBefore.append("INNER JOIN rentarea as ra on bd.id = ra.buildingid\r\n");
			if (Validation.stringIsNumeric(dto.getAreaFrom())) {
				sqlAfter.append("and ra.value >= " + dto.getAreaFrom() + "\n");
			}
			if (Validation.stringIsNumeric(dto.getAreaTo())) {
				sqlAfter.append("and ra.value <= " + dto.getAreaTo() + "\n");
			}
		}
		if (Validation.stringIsNumeric(dto.getNumberOfBasements())) {
			sqlAfter.append("and bd.numberofbasement = " + dto.getNumberOfBasements() + "\n");
		}
		if (Validation.stringIsNumeric(dto.getRentPriceFrom())) {
			sqlAfter.append("and bd.rentprice >= " + dto.getAreaFrom() + "\n");
		}
		if (Validation.stringIsNumeric(dto.getRentPriceTo())) {
			sqlAfter.append("and bd.rentprice <= " + dto.getRentPriceTo() + "\n");
		}
		if (Validation.stringIsNumeric(dto.getStaffId())) {
			sqlBefore.append("INNER JOIN assignmentbuilding as asbd on bd.id = asbd.buildingid\n");
			sqlAfter.append("and asbd.staffId =  " + dto.getStaffId() + "\n");
		}
		if (dto.getBuildingTypes().length > 0) {
			sqlBefore.append(" INNER JOIN buildingrenttype as brt on bd.id = brt.buildingid \r\n"
					+ " INNER JOIN renttype as rt on rt.id = brt.renttypeid");
			sqlAfter.append("and" + buildingTypeCombine(dto.getBuildingTypes()) + "\n");
		}
		sqlAfter.append("group by bd.id");
		return sqlBefore.append(sqlAfter);
	}
}

//System.out.println("Câu query : " + sqlFinal);
//System.out.println("Data trả về : ");
package com.laptrinhjava.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjava.entity.DistrictEntity;
import com.laptrinhjava.repository.DistrictRepository;

public class DistrictRepositoryImpl implements DistrictRepository {
	private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private final String USER = "root";
	private final String PASS = "123456";

	@Override
	public DistrictEntity findByCode(Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DistrictEntity district = new DistrictEntity();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				System.out.println("Kết nối thành công");
				StringBuilder sql = new StringBuilder("select id, name from district where 1 = 1");
				if (id != null) {
					sql.append(" and id = ? ");
					stmt = conn.prepareStatement(sql.toString());
					stmt.setInt(1, id);
					rs= stmt.executeQuery();
					while (rs.next()) {
						district.setName(rs.getString("name")); // set gtri name cho building
					}
				}
				return district;
			}
		} catch (Exception e) {
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
		return new DistrictEntity();
	}
}

package com.laptrinhjava.repository.impl;

import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.entity.UserEntity;
import com.laptrinhjava.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    private final String USER = "root";
    private final String PASS = "123456";

    @Override
    public List<UserEntity> findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println(" Kết nối thành công ");
                StringBuilder sql = new StringBuilder("select u.id,b.name, u.fullname from building as b" +
                        " inner join assignmentbuilding as ab on ab.buildingid = b.id\n" +
                        " inner join user as u on u.id = ab.staffid where 1=1 ");
                if (id != null) {
                    sql.append(" and b.id = ? ");
                    stmt = conn.prepareStatement(sql.toString());
                    stmt.setLong(1, id);
                    rs = stmt.executeQuery();
                    while (rs.next()) {   // hứng kết quả
                        UserEntity userEntity = new UserEntity();
                        userEntity.setId(rs.getLong("u.id"));
                        userEntity.setFullname(rs.getString("u.fullname"));
                        userEntityList.add(userEntity);
                    }
                }
                return userEntityList;

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


    // findAll
    @Override
    public List<UserEntity> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println(" Kết nối thành công ");
                StringBuilder sql = new StringBuilder(" select * from user \n");
                stmt = conn.prepareStatement(sql.toString());
                rs = stmt.executeQuery();
                while (rs.next()) {   // hứng kết quả
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(rs.getLong("id"));
                    userEntity.setFullname(rs.getString("fullname"));
                    userEntityList.add(userEntity);
                }
                return userEntityList;
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



//    @Override
//    public List<UserEntity> GiaoToaNha(Long[] id) {
//        return null;
//    }
}

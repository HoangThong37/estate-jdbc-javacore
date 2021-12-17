package com.laptrinhjava.repository.impl;

import com.laptrinhjava.repository.AssigmentBuildingRepo;

import java.sql.*;
import java.util.List;

public class AssigmentBuildingRepoImpl implements AssigmentBuildingRepo {
    private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    private final String USER = "root";
    private final String PASS = "123456";

    @Override
    public void ASS(Long buildingid, List<Long> newstaff, List<Long> oldstaff) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (!newstaff.isEmpty()) {
                StringBuilder sqlAddNew = new StringBuilder();
                conn.setAutoCommit(false); // tắt chế độ auto-commit

                sqlAddNew.append("\n INSERT INTO assignmentbuilding(staffid, buildingid)").append(" values ");
                for (int i = 0; i < newstaff.size(); i++) {
                    sqlAddNew.append("(" + newstaff.get(i) + ", " + buildingid + ")");
                    if (i < newstaff.size() - 1) {
                        sqlAddNew.append(",");
                    }
                }
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlAddNew.toString());
                conn.commit();
            }
// XÓA
            if (!oldstaff.isEmpty()) {
                StringBuilder sqlRemove = new StringBuilder();
                conn.setAutoCommit(false); // tắt chế độ auto-commit

                sqlRemove.append("\n delete from assignmentbuilding where buildingid = ").append(buildingid)
                        .append(" and staffid IN(");
                for (int i = 0; i < oldstaff.size(); i++) {
                    sqlRemove.append(oldstaff.get(i));
                    if (i < oldstaff.size() - 1) {
                        sqlRemove.append(",");
                    } else {
                        sqlRemove.append(")");
                    }
                }
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlRemove.toString());
                conn.commit();
            }
        } catch (SQLException e) {
            e.getMessage();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

        }

    }
}

package com.higgsup.intern.w02.testcases.management_student;

import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public Boolean deteleStudent(int id){
        Boolean isDelete = false;
        try (
                Connection conn = DBUtil.getConnection();
        ){
            String delete = "DELETE FROM `student` WHERE id = "+id;
            Statement stmt = conn.createStatement();
            stmt.execute(delete);
            Read read = new Read();
            read.readInfoStudent();
            isDelete = true;

        }catch(SQLException ex){
            System.err.println(ex);
            isDelete = false;
        }
        return isDelete;
    }

    public static void main(String[] args) {
        Delete delete = new Delete();
        delete.deteleStudent(3012);
    }
}

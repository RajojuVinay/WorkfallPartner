package example.database;

import example.testbase.TestBase;


import java.sql.*;

public class DataBase extends TestBase {
    public String getTestDetails() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println(" Driver loaded ");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/localworkfall", "postgres", "test");
        System.out.println(" Connected to MySQL DB ");
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("Select * from app_user where email = 'vinayclient31@getnada.com'");
        String userEmail = null;
        while (rs.next()) {
            String firstname = rs.getString("email");
            userEmail = firstname;
            System.out.println("selected firstname is " + firstname);
        }
//        smt.executeUpdate("Update app_user set is_email_verified = 'false' where email = 'vinaypartner18@getnada.com'");
//        smt.executeUpdate("Update verf_user set is_verified = 'true',app_user_id = '3543' where app_user_id = 3243");
        return userEmail;
    }

}

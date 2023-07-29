package example;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTesting {

    public DbTesting(){
        super();
    }
        @Test
        public void testDB ( ) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver" );
            System.out.println ( " Driver loaded " ) ;
            Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/localworkfall" ,"postgres" ,"test" ) ;
            System.out.println ( " Connected to MySQL DB " ) ;
            Statement smt = con.createStatement( ) ;
            smt.executeUpdate( "Update app_user set is_email_verified = 'false' where email = 'vinaypartner18@getnada.com'" ) ;
            smt.executeUpdate("Update verf_user set is_verified = 'true',app_user_id = '3543' where app_user_id = 3243");
//            while ( rs.next ( ) )
//            {
//                String firstname = rs.getString ( "id" ) ;
//                System.out.println ( " Database record is "+ firstname ) ;
//                String emailaddress = rs.getString ( "email" ) ;
//                System.out.println ( " Database record is " + emailaddress ) ;
//            }
        }



}


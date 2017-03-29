package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ozankoyuncu on 29.3.2017.
 */
public class Version {
    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;


        String url = "jdbc:postgresql://localhost/ProjectDB";
        String user = "postgres";
        String password = "ozan4699327";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}

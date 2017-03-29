package Database;

import Modules.Article;
import Modules.Documents;
import Modules.Publication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ozankoyuncu on 29.3.2017.
 */
public class ProjectDB {

    public  static final String URL = "jdbc:postgresql://localhost/ProjectDB";
    public  static final String USER = "postgres";
    public  static final String PASSWORD = "ozan4699327";
    Connection con = null;
    PreparedStatement pst = null;


    public void SetArticleTable(Article article){}
    //public void SetAuthorTable(Documents article){}
    public void SetPublicationTable(Publication publication){
       /* System.out.println("Publication title: "+doc.publications.get(0).getPub_title());
        System.out.println("Publication year: "+ doc.publications.get(0).getPub_year());
        System.out.println("Publication type: "+ doc.publications.get(0).getPub_type());
        System.out.println("Publication publisher: "+ doc.publications.get(0).getPublisher());
        System.out.println("Publication number: "+ doc.publications.get(0).getPub_number());*/

        try {

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            String stm = "INSERT INTO public.publication(\n" +
                    "\tpublication_number, publication_title, publication_type, publication_year, publisher_id)\n" +
                    "\tVALUES (?, ?, ?, ?, ?);";
            pst = con.prepareStatement(stm);
            pst.setInt(1, publication.getPub_number());
            pst.setString(2, publication.getPub_title());
            pst.setString(3, publication.getPub_type());
            pst.setInt(4, publication.getPub_year());
            pst.setInt(5, 1); // TODO: PUBLİSHER ıd sorgulanacak alınacak
            pst.executeUpdate();
            System.out.println("Publication Table'a veri başarıyla Eklendi.");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ProjectDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(ProjectDB.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}

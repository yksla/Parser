import Modules.Documents;
import org.w3c.dom.NodeList;

/**
 * Created by ozankoyuncu on 22.3.2017.
 */
public class MainParser {
    private final String USER_AGENT = "Mozilla/5.0";
        private static String url_Query_Text= "http://ieeexplore.ieee.org/gateway/ipsSearch.jsp?au=Nicola+Bui&hc=5";
    private static String response_xml;

    public static void main(String[] args) throws Exception {
        MyHttpRequest getXmlbyUrl = new MyHttpRequest();
        try {
            response_xml = getXmlbyUrl.sendGet(url_Query_Text); // Get the returned xml document from the Url
            ReadXmlDocument xmlReader = new ReadXmlDocument();
            NodeList docList = xmlReader.ReadXml(response_xml);
            Documents doc = xmlReader.printNodebyDocumentClass(docList);

            // Buraya Arama dönmesi gerekiyor
            // Daha sonra bu listeyi postgresqle parçalara ayırarak atacağız
            // databasee uygun classlar da oluşturulabilir, article, publication gibi

            for (int i = 0; i < doc.articles.size(); i++) {
                System.out.println("Document number: "+ (i+1));
                System.out.println("Article name: "+doc.articles.get(i).getArticle_name());
                System.out.println("Article number: "+doc.articles.get(i).getArticle_number());
                System.out.println("Publication title: "+doc.publications.get(i).getPub_title());
                System.out.println("Publication year: "+ doc.publications.get(i).getPub_year()+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getResponse_xml() {
        return response_xml;
    }

}

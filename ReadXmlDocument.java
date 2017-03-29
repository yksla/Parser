

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;

import Modules.Article;
import Modules.Documents;
import Modules.Publication;
import com.sun.org.apache.xml.internal.security.utils.XPathFactory;
import com.sun.org.apache.xml.internal.utils.ListingErrorHandler;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadXmlDocument {

    public NodeList ReadXml(String xmlCode) {

        NodeList document_list = null;
        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlCode));
            Document doc = dBuilder.parse(is);

           // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            //TODO: HATALI URL DURUMU KONTROL EDILECEK (ERROR TAG VAR MI DİYE BAKILACAK)


            document_list = doc.getElementsByTagName("document");
            if (document_list.getLength()!= 0) {
                System.out.println("Documents count : " + document_list.getLength());
                System.out.println("Total Found: "+ doc.getElementsByTagName("totalfound").item(0).getTextContent());
                System.out.println("Total Searched: "+ doc.getElementsByTagName("totalsearched").item(0).getTextContent());
                printNodebyDocumentClass(document_list);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return document_list;

    }

    public static Documents printNodebyDocumentClass(NodeList document_list)
    {
        DomParser parser = new DomParser();
        Documents doc = new Documents();
        //ArrayList<Node[]> authorNodeArrayList = new ArrayList<Node[]>();
        int length = document_list.getLength();
        Node[] titles = new Node[length];
        Node[] authors = new Node[length];
        Node[] article_numbers = new Node[length];
        Node[] pub_titles = new Node[length];
        Node[] pub_numbers = new Node[length];
        Node[] pub_types = new Node[length];
        Node[] publishers = new Node[length];
        Node[] pub_years = new Node[length];
        Node[] abstracts = new Node[length];
        Node[] pdfs = new Node[length];
        Node[] mdurls = new Node[length];
        Node[] spages = new Node[length];
        Node[] epages = new Node[length];

        for (int count = 0; count < document_list.getLength(); count++) {
            Node tempNode = document_list.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                titles[count] = parser.getNode("title",tempNode.getChildNodes());
                authors[count] = parser.getNode("authors",tempNode.getChildNodes());
                article_numbers[count] = parser.getNode("punumber",tempNode.getChildNodes());
                abstracts[count] = parser.getNode("abstract",tempNode.getChildNodes());
                mdurls[count] = parser.getNode("mdurl",tempNode.getChildNodes());
                pdfs[count] = parser.getNode("pdf",tempNode.getChildNodes());
                spages[count] = parser.getNode("spage",tempNode.getChildNodes());
                epages[count] = parser.getNode("epage",tempNode.getChildNodes());
                pub_titles[count] = parser.getNode("pubtitle",tempNode.getChildNodes());
                pub_numbers[count] = parser.getNode("punumber",tempNode.getChildNodes());
                pub_types[count] = parser.getNode("pubtype",tempNode.getChildNodes());
                publishers[count] = parser.getNode("publisher",tempNode.getChildNodes());
                pub_years[count] = parser.getNode("py",tempNode.getChildNodes());
            }
        }
        for (int i = 0; i < document_list.getLength() ; i++) {
            Article article = new Article();
            article.setArticle_name(titles[i].getTextContent());
            article.setAuthors(authors[i].getTextContent());
            article.setArticle_number(Integer.parseInt(article_numbers[i].getTextContent()));
            article.setAbst(abstracts[i].getTextContent());
            article.setMdurl(mdurls[i].getTextContent());
            article.setPdf(pdfs[i].getTextContent());
            article.setSpage(Integer.parseInt(spages[i].getTextContent()));
            article.setEpage(Integer.parseInt(epages[i].getTextContent()));
            doc.articles.add(article);

        }
        for (int i = 0; i < document_list.getLength(); i++) {
            Publication publication = new Publication();
            publication.setPub_title(pub_titles[i].getTextContent());
            publication.setPub_number(Integer.parseInt(pub_numbers[i].getTextContent()));
            publication.setPub_type(pub_types[i].getTextContent());
            publication.setPublisher(publishers[i].getTextContent());
            publication.setPub_year(Integer.parseInt(pub_years[i].getTextContent()));
            doc.publications.add(publication);

        }
       /* System.out.println(doc.articles.size());
        for (int i = 0; i < doc.publications.size() ; i++) {
            System.out.println(doc.publications.get(i).getPub_year());
        }*/


        return  doc;
    }

    /*public void fromNodeToClass()
    {
        String response = MainParser.getResponse_xml();
        NodeList document_list = ReadXml(response);
        ArrayList<Node[]> nodeArrayList = printNodebyDocumentClass(document_list);

        Article[] articles = new Article[document_list.getLength()];
        for(int i = 0; i < document_list.getLength() ; i++)
        {
            articles[i] = new Article();
            articles[i].setArticle_name(nodeArrayList.get(0)[i].getTextContent());
            articles[i].setAuthors(nodeArrayList.get(1)[i].getTextContent());
            articles[i].setArticle_number(Integer.parseInt(nodeArrayList.get(2)[i].getTextContent()));
        }

        /*for (int i = 0; i< articles.length; i++) {
            System.out.println(articles[i].getArticle_name());
            System.out.println(articles[i].getArticle_number());
            System.out.println(articles[i].getAuthors());
            System.out.println();System.out.println();
        }*/
    }

   /* private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }


    }*/




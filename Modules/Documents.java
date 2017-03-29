package Modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozankoyuncu on 22.3.2017.
 */
public class Documents {
   /* private static int article_number;
    private static String article_doi;
    private static String article_title;
    private static String article_abstract;
    private static String article_mdurl;
    private static String article_pdf;
    private static int publication_id=1;*/
   public List<Article> articles;
   public List<Publication> publications;
    //public List<List<Keyword>> keywordLists = new ArrayList<List<Keyword>>();

    public Documents() {
        articles = new ArrayList<Article>();
        publications = new ArrayList<Publication>();
    }
}

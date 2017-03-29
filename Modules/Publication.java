package Modules;


public class Publication {
    private String pub_title;
    private int pub_number;
    private String pub_type;
    private String publisher;
    private int pub_year;

    public String getPub_title() {
        return pub_title;
    }

    public void setPub_title(String pub_title) {
        this.pub_title = pub_title;
    }

    public int getPub_number() {
        return pub_number;
    }

    public void setPub_number(int pub_number) {
        this.pub_number = pub_number;
    }

    public String getPub_type() {
        return pub_type;
    }

    public void setPub_type(String pub_type) {
        this.pub_type = pub_type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPub_year() {
        return pub_year;
    }

    public void setPub_year(int pub_year) {
        this.pub_year = pub_year;
    }
}

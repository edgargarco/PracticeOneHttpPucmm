package logical;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;

public class Main {
    Document doc;
    Elements img;

    public Main() {
        this.doc = doc;
        this.img = img;
    }

    private int connection(String URL,String tag){
       try {
                doc = Jsoup.connect(URL).get();
                Elements tagElement = doc.getElementsByTag(tag);
                return tagElement.size();
        } catch (IOException e) {
                 e.printStackTrace();
                 return 0;
        }
    }

    public static void main(String[] args) {

        Main n = new Main();
        System.out.println(n.connection("http://localhost:5000/","p"));

    //Elements img = n.connection("http://www.google.com").getElementsByTag("img");





    }
}

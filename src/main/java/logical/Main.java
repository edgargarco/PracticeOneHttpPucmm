package logical;

import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.net.URL;
import java.util.Scanner;

@NoArgsConstructor

public class Main {
    private  static ClientHttp client= null;

    public static ClientHttp getInstance(){
        if(client == null){
            client= new ClientHttp();
        }
        return  client;
    }


    public static void main(String[] args) throws IOException {


       // String url = "http://localhost:5000/";
        getInstance().asciiArt();
        //Scanner console = new Scanner(System.in);
        System.out.println("Please enter a valid URL: ");
        //String url = console.next();
        String url = "http://itachi.avathartech.com:4567/opcion2.html";

        getInstance().connection(url);
        //getInstance().httpProcess();



    }
}

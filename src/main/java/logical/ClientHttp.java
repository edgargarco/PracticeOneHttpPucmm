package logical;

import com.sun.tools.doclint.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClientHttp {
        private CloseableHttpClient client;
        private HttpGet get;
        private CloseableHttpResponse response;
        private HttpEntity entity;
        private Document doc;
        private Elements tagElement;
        private String url;
        private String[] aux;
        private HttpPost httppost;


        public void httpProcess() throws IOException {



        }
        public void connection(String URL){
                setUrl(URL);
                String[] tags = {"p","img","form"};
                try {
                    Connection.Response noParse = Jsoup.connect(getUrl()).execute();
                    int lines = noParse.body().split("\n").length;
                    System.out.println("Number of lines returned from url: "+lines);
                        setDoc(Jsoup.connect(getUrl()).get());


                        for (int i =0;i<tags.length;i++){
                            setTagElement(getDoc().getElementsByTag(tags[i]));
                            if (tags[i] == "p"){
                                System.out.println("    Amount of Paragraphs: "+getTagElement().size());
                                System.out.println("    Amount of Images: "+getTagElement().select("p").select("img").size());

                            }else if(tags[i] == "form"){
                                formClassification(getTagElement());
                            }
                        }

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        public void formClassification(Elements tagElement){

                System.out.println("    Amount of Forms using POST Method: "+tagElement.select("form[method=POST]").size());
                System.out.println("    Amount of Forms using GET Method: "+tagElement.select("form[method=GET]").size());
                System.out.println("Input field types inside Form via POST: ");
                for(int i=0;i<tagElement.select("form[method=POST]").select("input").size();i++){
                        System.out.println("    Type: "+tagElement.select("form[method=POST]").select("input").get(i).attr("type")
                        +" "+"Name: "+tagElement.select("form[method=POST]").select("input").get(i).attr("name"));
                }
                System.out.println("Input field types inside Form via GET: ");
                for(int i=0;i<tagElement.select("form[method=GET]").select("input").size();i++){
                    System.out.println("    Type: "+tagElement.select("form[method=GET]").select("input").get(i).attr("type")
                            +" "+"Name: "+tagElement.select("form[method=POST]").select("input").get(i).attr("name"));
                }
        }

        public void asciiArt(){
                System.out.println("  ____                          _     _                   _  _     _ \n" +
                        " |  _ \\   _ __    __ _    ___  | |_  (_)   ___    ___   _| || |_  / |\n" +
                        " | |_) | | '__|  / _` |  / __| | __| | |  / __|  / _ \\ |_  ..  _| | |\n" +
                        " |  __/  | |    | (_| | | (__  | |_  | | | (__  |  __/ |_      _| | |\n" +
                        " |_|     |_|     \\__,_|  \\___|  \\__| |_|  \\___|  \\___|   |_||_|   |_|\n" +
                        "                                                                     ");
                System.out.println("  _   _   _____   _____   ____             ____   _   _                  _   \n" +
                        " | | | | |_   _| |_   _| |  _ \\           / ___| | | (_)   ___   _ __   | |_ \n" +
                        " | |_| |   | |     | |   | |_) |  _____  | |     | | | |  / _ \\ | '_ \\  | __|\n" +
                        " |  _  |   | |     | |   |  __/  |_____| | |___  | | | | |  __/ | | | | | |_ \n" +
                        " |_| |_|   |_|     |_|   |_|              \\____| |_| |_|  \\___| |_| |_|  \\__|");
                System.out.println("  ____     ___    _    __             _   _   _  _     _____ \n" +
                        " |___ \\   / _ \\  / |  / /_           / | / | | || |   |___ / \n" +
                        "   __) | | | | | | | | '_ \\   _____  | | | | | || |_    |_ \\ \n" +
                        "  / __/  | |_| | | | | (_) | |_____| | | | | |__   _|  ___) |\n" +
                        " |_____|  \\___/  |_|  \\___/          |_| |_|    |_|   |____/ ");
        }



}

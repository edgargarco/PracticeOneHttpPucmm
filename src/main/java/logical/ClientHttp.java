package logical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

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


        public void httpProcess() throws IOException {
                client = HttpClients.createDefault();
                get = new HttpGet("http://localhost:5000/");
                response = client.execute(get);
                entity = getResponse().getEntity();

                //String responseString = EntityUtils.toString(getEntity(), "UTF-8");//Gets me the full HTML code
        }
        public void connection(String URL){
                setUrl(URL);
                String[] tags = new String[3];
                String[] aux =getUrl().split(".com");


                try {
                        setDoc(Jsoup.connect(getUrl()).get());
                        setTagElement(getDoc().getElementsByTag("p"));
                        System.out.println("Amount of Paragraphs in "+Arrays.toString(aux)+": "+getTagElement().size());
                        setTagElement(getDoc().getElementsByTag("img"));
                        System.out.println("Amount of Images in "+Arrays.toString(aux)+": "+getTagElement().size());
                        setTagElement(getDoc().getElementsByTag("form"));
                        formClassification(getTagElement());
                } catch (IOException e) {
                        e.printStackTrace();
                        //return 0;

                }
        }
        public void formClassification(Elements tagElement){

                System.out.println("Amount of Forms using Post Method: "+tagElement.select("form[method=POST]").size());
                System.out.println("Amount of Forms using GET Method: "+tagElement.select("form[method=GET]").size());
                System.out.println("Input field types inside Form via POST: ");
                for(int i=0;i<tagElement.select("form[method=POST]").select("input").size();i++){
                        System.out.println(tagElement.select("form[method=POST]").select("input").get(i).attr("type"));
                }

                System.out.println("Input field types inside Form via GET: ");
                for(int i=0;i<tagElement.select("form[method=GET]").select("input").size();i++){
                        System.out.println(tagElement.select("form[method=GET]").select("input").get(i).attr("type"));
                }



        }

        public void asciiArt(){
                System.out.println("  _____                    _    _                _  _   __    \n" +
                        " |  __ \\                  | |  (_)             _| || |_/_ |   \n" +
                        " | |__) |_ __  __ _   ___ | |_  _   ___  ___  |_  __  _|| |   \n" +
                        " |  ___/| '__|/ _` | / __|| __|| | / __|/ _ \\  _| || |_ | |   \n" +
                        " | |    | |  | (_| || (__ | |_ | || (__|  __/ |_  __  _|| | _ \n" +
                        " |_|    |_|   \\__,_| \\___| \\__||_| \\___|\\___|   |_||_|  |_|(_)\n" +
                        "                                                              \n" +
                        "                                                            ");
                System.out.println("  _    _  _______  _______  _____    _____  _       _____  ______  _   _  _______ \n" +
                        " | |  | ||__   __||__   __||  __ \\  / ____|| |     |_   _||  ____|| \\ | ||__   __|\n" +
                        " | |__| |   | |      | |   | |__) || |     | |       | |  | |__   |  \\| |   | |   \n" +
                        " |  __  |   | |      | |   |  ___/ | |     | |       | |  |  __|  | . ` |   | |   \n" +
                        " | |  | |   | |      | |   | |     | |____ | |____  _| |_ | |____ | |\\  |   | |   \n" +
                        " |_|  |_|   |_|      |_|   |_|      \\_____||______||_____||______||_| \\_|   |_|   \n" +
                        "                                                                                  \n" +
                        "                                                                                 ");
                System.out.println("  ___    ___  __    __         __  __  _  _   ____  \n" +
                        " |__ \\  / _ \\/_ |  / /        /_ |/_ || || | |___ \\ \n" +
                        "    ) || | | || | / /_  ______ | | | || || |_  __) |\n" +
                        "   / / | | | || || '_ \\|______|| | | ||__   _||__ < \n" +
                        "  / /_ | |_| || || (_) |       | | | |   | |  ___) |\n" +
                        " |____| \\___/ |_| \\___/        |_| |_|   |_| |____/ \n" +
                        "                                                    \n" +
                        "                                                   ");
        }





}

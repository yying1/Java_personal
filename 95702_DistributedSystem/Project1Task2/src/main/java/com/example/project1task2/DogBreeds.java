package com.example.project1task2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import javax.net.ssl.*;

@WebServlet(name = "DogBreeds", value = "/getDogBreeds")
public class DogBreeds extends HttpServlet {
    private String dog_breed;



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        dog_breed = request.getParameter("breed");
        String [] result = get_dog_info(dog_breed);
        String pic = get_pic(dog_breed);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Dog: " + dog_breed + "</h1>");
        out.println("<h2>Friendly : " + result[0] + " Stars</h2>");
        out.println("<h2>Intelligence : " + result[1] + " Stars</h2>");
        out.println("<h2>" + result[2] +"</h2>");
        out.println("<h2>" + result[3] + "</h2>");
        out.println("<h2>" + result[4] + "</h2>");
        out.println("<h3>Credit: https://dogtime.com/dog-breeds/profile</h1>");

        out.println("<img src="+ pic+"><br><br>");
        out.println("<h3>Credit: https://dog.ceo/dog-api/");
        out.println("<br/>");
        out.println("<form action=index.jsp>");
        out.println("<input type=\"Submit\" value=\"Continue\">");
        out.println("</form>");
        out.println("</body></html>");

    }

    public void destroy() {
    }

    public String get_pic(String breed) throws IOException {
        //referenced from https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751
        URL url = new URL("https://dog.ceo/api/breed/" + breed.toLowerCase() + "/images");
        String result_link="";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            //Close the scanner
            scanner.close();
            JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
            JsonArray URL_list = jsonObject.get("message").getAsJsonArray();
            int select_int = 0 + (int)(Math.random() * ((URL_list.size() - 0) + 1));
            System.out.println(select_int);
            result_link = String.valueOf(URL_list.get(select_int)).replace("\"","");
            System.out.println(result_link);
        }
        return result_link;
    }


    public String[] get_dog_info(String breed) throws IOException {
        String[] result = new String[5];
        String raw_html = fetch("https://dogtime.com/dog-breeds/profile/"+breed,"TLSV1.3");
        String friendly="";
        String friendly_header = "All Around Friendliness</h3><div class=\"characteristic-star-block\"><div class=\"star star-";
        int friendly_index = raw_html.indexOf(friendly_header);
        if (friendly_index != -1) {
            friendly = raw_html.substring(friendly_index+friendly_header.length(),friendly_index+friendly_header.length()+1);
        }

        String Intelligence="";
        String Intelligence_header = "Intelligence</div><div class=\"characteristic-star-block\"><div class=\"star star-";
        int Intelligence_index = raw_html.indexOf(Intelligence_header);
        if (Intelligence_index != -1) {
            Intelligence = raw_html.substring(Intelligence_index+Intelligence_header.length(),Intelligence_index+Intelligence_header.length()+1);
        }

        Pattern p = Pattern.compile("Height:</div>(.*?)</div>");
        Matcher m = p.matcher(raw_html);
        String height = "";
        while(m.find()) {
           height = m.group(0);
        }

        Pattern p1 = Pattern.compile("Weight:</div>(.*?)</div>");
        Matcher m1 = p1.matcher(raw_html);
        String weight = "";
        while(m1.find()) {
            weight = m1.group(0);
        }

        Pattern p2 = Pattern.compile("Life Span:</div>(.*?)</div>");
        Matcher m2 = p2.matcher(raw_html);
        String LP = "";
        while(m2.find()) {
            LP = m2.group(0);
        }
        result[0] = friendly;
        result[1] = Intelligence;
        result[2] = height;
        result[3] = weight;
        result[4] = LP;

//        //Document doc = Jsoup.connect("https://dogtime.com/dog-breeds/profile/"+breed+"#/slide/1").get();
//        System.out.println("test2");
//        //System.out.println(raw_html);
//        Document doc = Jsoup.parse(raw_html, Parser.xmlParser());
//        Elements friendly_e = doc.getElementsByClass("breed-characteristics-ratings-wrapper paws");
//        //Elements inputElements = friendly.tagName("div");
//        System.out.println(friendly_e);
//        System.out.println(doc.text());
//        String friendly = "";
//        for (Element inputElement : friendly_e) {
//            if(inputElement.text().contains("All Around Friendliness")){
//                Elements divs = inputElement.getElementsByTag("div");
//                for (Element div: divs){
//                    if (div.className().contains("star")){
//                        friendly = div.className();
//                        break;
//                    }
//                }
//            }
//
//        }
        return result;
    }


//adapted from https://github.com/CMU-Heinz-95702/Project1
    private String fetch(String searchURL, String certType) {
        try {
            // Create trust manager, which lets you ignore SSLHandshakeExceptions
            createTrustManager(certType);
        } catch (KeyManagementException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        }

        String response = "";
        try {
            URL url = new URL(searchURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response = response + str;
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Something wrong with URL");
            return null;
        }
        return response;
    }

    private void createTrustManager(String certType) throws KeyManagementException, NoSuchAlgorithmException {
        /**
         * Annoying SSLHandShakeException. After trying several methods, finally this
         * seemed to work.
         * Taken from: http://www.nakov.com/blog/2009/07/16/disable-certificate-validation-in-java-ssl-connections/
         */
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance(certType);
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
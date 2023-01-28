import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        WebScraper webScraper = new WebScraper();
        webScraper.scrapeWeb();
    }
}

class WebScraper {
    public void scrapeWeb() {
        try {
            URL url = new URL("http://example.org");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("User-Agent", "Chrome");
            httpUrlConnection.setReadTimeout(30000);

            int responseCode = httpUrlConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                System.out.println("Er ging iets mis tijdens het laden van de webpagina");
                return;
            }

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));

            String line;

            FileWriter fw = new FileWriter("C:\\Users\\lemly\\Desktop\\WebScraper\\rlm\\com\\copyOfWebsite.html");

            while ((line = inputReader.readLine()) != null) {
                fw.write(line);
                System.out.println(line);
            }

            fw.close();
            inputReader.close();

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
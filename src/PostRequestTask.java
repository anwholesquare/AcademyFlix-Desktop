import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import javafx.concurrent.Task;

public class PostRequestTask extends Task<String> {
   private final String urlString;
   private final Map<String, String> parameters;

   public PostRequestTask(String urlString, Map<String, String> parameters) {
      this.urlString = urlString;
      this.parameters = parameters;
   }

   @Override
   protected String call() throws Exception {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      StringBuilder requestBody = new StringBuilder();
      for (Map.Entry<String, String> entry : parameters.entrySet()) {
         requestBody.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
         requestBody.append("=");
         requestBody.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
         requestBody.append("&");
      }
      requestBody.deleteCharAt(requestBody.length() - 1);

      DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
      dataOutputStream.writeBytes(requestBody.toString());
      dataOutputStream.flush();
      dataOutputStream.close();

      int responseCode = connection.getResponseCode();
      System.out.println("Response code: " + responseCode);

      StringBuilder response = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
         String line;
         while ((line = reader.readLine()) != null) {
            response.append(line);
         }
      }

      connection.disconnect();
      return response.toString();
   }
}

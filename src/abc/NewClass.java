package abc;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.util.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class NewClass {

  // Retrieve the CLIENT_ID and CLIENT_SECRET from an APIs Console project:
  //     https://code.google.com/apis/console
  static String CLIENT_ID = "241478142732-gkp7s3udb5uqbfkkc42ubimt2qn7nm7k.apps.googleusercontent.com";
  static String CLIENT_SECRET = "y8Abcd83F_lR3BD6o6jkTTOt";
  // Change the REDIRECT_URI value to your registered redirect URI for web
  // applications.
  static String REDIRECT_URI = "https://www.example.com/oauth2callback";
  // Add other requested scopes.
  static List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");


public static void main (String args[]) throws Exception , IOException, ServiceException{
    Credential credencial = getCredentials();
    JavaApplication20.printDocuments(credencial);
}


  /**
   * Retrieve OAuth 2.0 credentials.
   * 
   * @return OAuth 2.0 Credential instance.
   */
  static Credential getCredentials() throws Exception , IOException, ServiceException{
    HttpTransport transport = new NetHttpTransport();
    JacksonFactory jsonFactory = new JacksonFactory();

    // Step 1: Authorize -->
    String authorizationUrl =
        new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URI, SCOPES).build();

    // Point or redirect your user to the authorizationUrl.
    System.out.println("Go to the following link in your browser:");
    System.out.println(authorizationUrl);

    // Read the authorization code from the standard input stream.
    //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //System.out.println("What is the authorization code?");
    String code = "4/ZcoluVdxM2_FL1hCkapAuXbiOY7DHX_wySoRRoZ9-qk.wq7p8Vqdvq0fcp7tdiljKKapV91SmgI";
    
    
    // https://www.example.com/oauth2callback?code=4/0pKlll6778XhxWHh3shAbJZrNudqRs3Bk6yEZblXgYo.4uDMfyMyJXMQcp7tdiljKKY71upRmgI#
    
    //https://accounts.google.com/o/oauth2/auth?client_id=241478142732-gkp7s3udb5uqbfkkc42ubimt2qn7nm7k.apps.googleusercontent.com&redirect_uri=https://www.example.com/oauth2callback&response_type=code&scope=https://spreadsheets.google.com/feeds
    
    // End of Step 1 <--

    // Step 2: Exchange -->
    GoogleTokenResponse response =
        new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
            code, REDIRECT_URI).execute();
    
    System.out.println(">>>>>1>>>>>");
    // End of Step 2 <--

    // Build a new GoogleCredential instance and return it.
    return new GoogleCredential.Builder().setClientSecrets(CLIENT_ID, CLIENT_SECRET)
        .setJsonFactory(jsonFactory).setTransport(transport).build()
     .setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
  }

  // …
}
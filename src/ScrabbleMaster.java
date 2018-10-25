
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ScrabbleMaster {
    //you must implement the function to retrieve the content of a specific URL at https://wordfinder-001.appspot.com/wordfinder
    //
    //be aware that at random  the  ResponseCode may be SC_INTERNAL_SERVER_ERROR  or SC_INTERNAL_SERVER_ERROR instead of SC_OK
    //

    public Character getURL( String url) {
        // Get character
        String inputLine;
        URL urlLook;
        int statusCode = 0;

        try {
            // create new URL object
            urlLook = new URL(url);
            // Open new HTTP connection
            HttpURLConnection connection = (HttpURLConnection) urlLook.openConnection();
            // Get the response code from connection
            statusCode = connection.getResponseCode();
            // Check status code and perform action
            switch(statusCode) {
                // When status code is 200 == OK
                case 200:
                    // Create new BufferedReader object to store content
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlLook.openStream()));
                    // Read content
                    while ((inputLine = in.readLine()) != null) {
                        return inputLine.charAt(0);
                    }
                    // Close buffer reader
                    in.close();
                default:
                    // break when status code is not 200, e.g. 404, 403 or 500
                    break;
            }
        }

        // Handle exceptions
        catch (UnknownHostException u) {
            System.out.print("Unknown host");
        }
        catch (MalformedURLException e) {
            System.out.print("Malformed URL");
        }
        catch (IOException i) {
            System.out.print("IO Exeption");;
        }
        return null;
    }
}

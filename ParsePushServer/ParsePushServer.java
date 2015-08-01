package demo.sayan.parsepush;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParsePushServer {
	static final String deviceToken = "APA91bHGulEYyo6DOYQn-Fn42KaTsHR8CFply2JGMa1"
			+ "r6yC29n3vzbgKMcSP9UMBrN4CMPpQReMZFFVfPLtNVLAi9ea40rhmp33EGUy-S6tXLjy9ipzIeOk_BsNxT2Z4zUuOXD6icxJ27UdOjmc39tm23rOL2IGCyw";
	public static void main(String[] args) {
		URL target;
		HttpURLConnection connection;
		try {
			target = new URL("https://api.parse.com/1/push");
			connection = (HttpURLConnection) target.openConnection();
		    connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
		    connection.setRequestProperty("X-Parse-REST-API-Key", "DjCO5OMlLqVz8u5HAHRXqy0SSPl14PrbN3tfwbyL");
		    connection.setRequestProperty("X-Parse-Application-Id", "BtTAMdxzn3a2j6n80S1l4mjX7x0Fm8gN1vnPgR4t");
        	connection.setDoOutput(true);
		    OutputStream out = connection.getOutputStream();
		    String notification = "{\"where\": {\"deviceType\": \"android\",\"deviceToken\": \"" + deviceToken + 
		    		"\"},\"data\": {\"alert\": \"test te$t +es+ !\"}}";
		    
		    System.out.println(notification);
		    out.write(notification.getBytes());
		    out.flush();
		    int responseCode = connection.getResponseCode();
		   	if (responseCode != 200) {
			    System.err.println("Notification Failed");
			}
			connection.disconnect();
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
}

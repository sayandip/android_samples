package demo.sayan.pushnotifierserver;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class PushServer {
	static final String GCM_API_KEY = "AIzaSyDbTYQcJ2AOcxpQTLeuSlIYEoFsMI4PO30";
	static final int retries = 3;
	static final String MESSAGE_KEY = "message";
	static final String regId = "APA91bFjFHaXq_3KDWpSczcuD0Ag2MQ-N00WUNyIAUw7mwzr3NQVxLtbxbvvdjw"
    		+ "WaEFmHT1D2GRj-nL1U-xpLgY4ETcSl-uHRC1"
    		+ "HLhIv_kOWZKKCBUxaD-RKXJa6PmcKez7jYP6ZGhk5";
	public static void main(String[] args) {
		 Sender sender = new Sender(GCM_API_KEY);
		 Message msg = new Message.Builder().timeToLive(30)
					.delayWhileIdle(true).addData(MESSAGE_KEY, "One last push !!!").build();

		    try {
		           	Result result = sender.send(msg, regId, retries);
			    	if(StringUtils.isEmpty(result.getErrorCodeName())) {
			    		System.out.println("GCM notification is sent successfully");
			    	}else{
			    		System.err.println("Error occurred while sending push notification :" + result.getErrorCodeName());
			    	}
		      } catch (InvalidRequestException e) {
		    	  	System.err.println("Invalid Request");
		      } catch (IOException e) {
		    	  	System.err.println("IO Exception");
		      }
	}

}

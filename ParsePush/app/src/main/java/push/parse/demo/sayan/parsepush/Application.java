package push.parse.demo.sayan.parsepush;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.
	Parse.initialize(this, "BtTAMdxzn3a2j6n80S1l4mjX7x0Fm8gN1vnPgR4t", "nHVjS9Jj4e721kubjU0X1IybNCUqFOE4h5p5sdfb");
    final ParseInstallation currentInstallation = ParseInstallation.getCurrentInstallation();
    String deviceToken = currentInstallation.get("deviceToken").toString();
    System.out.println("Device Token :"+deviceToken);
	// Specify an Activity to handle all pushes by default.
	PushService.setDefaultPushCallback(this, MainActivity.class);

  }
}
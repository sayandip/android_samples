package demo.sayan.pushnotifierclient;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;


public class MainActivity extends Activity {
    Button button;
    GoogleCloudMessaging gcm;
    String regId;
    Context context;

    public static final String REG_ID = "regId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(regId)) {
                    regId = registerGCM();
                    Log.d("MainActivity", "GCM RegId: " + regId);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Already Registered with GCM RegId : " + regId,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public String registerGCM() {

        gcm = GoogleCloudMessaging.getInstance(this);
        regId = getRegistrationId(context);
        if (TextUtils.isEmpty(regId)) {
            registerInBackground();
            Log.d("MainActivity",
                    "registerGCM - successfully registered with GCM server - regId: "
                            + regId);
        } else {
            Toast.makeText(getApplicationContext(),
                    "RegId already available. RegId: " + regId,
                    Toast.LENGTH_LONG).show();
        }
        return regId;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i("MainActivity", "Registration not found.");
            return "";
        }
        return registrationId;
    }


    private void registerInBackground() {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String msg = "";
                    try {
                        if (gcm == null) {
                            gcm = GoogleCloudMessaging.getInstance(context);
                        }
                        regId = gcm.register(Config.GOOGLE_PROJECT_ID);
                        Log.d("MainActivity", "registerInBackground - regId: "+ regId);
                        msg = "Device registered, registration ID=" + regId;

                        storeRegistrationId(context, regId);
                    } catch (IOException ex) {
                        msg = "Error :" + ex.getMessage();
                        Log.d("RegisterActivity", "Error: " + msg);
                    }
                    Log.d("MainActivity", "AsyncTask completed: " + msg);
                    return msg;
                }

                @Override
                protected void onPostExecute(String msg) {
                    Toast.makeText(getApplicationContext(),
                            "Registered with GCM Server." + msg, Toast.LENGTH_LONG)
                            .show();
                }
            }.execute(null, null, null);
        }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        Log.i("MainActivity", "Saving regId on app version " );
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.commit();
    }
}

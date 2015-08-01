# android_samples

#### PushNotifierClient

An example of GCM Push. This simple app registers the device to receive GCM Push notifications.
The "regId" is printed in the log, it can be copied from log and used in a simple standalone 
java class <i>demo.sayan.pushnotifierserver.PushServer.java</i> in <b>PushServer</b> project to send push notifications to 
android devices via Google Cloud Messaging.

#### ParsePush

This is a similar app, but developed using Parse SDK. This one prints a "deviceToken" in log. 
Use the token to send push notification using Parse REST API. The notifications can be sent using command-line <i>cURL</i>
or Mozilla RESTClient add-on. A simple java class <i>demo.sayan.parsepush.ParsePushServer.java</i> has also been provided in <b>ParsePushServer</b>.




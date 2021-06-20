# How to use
### MessageCenter
MessagesCenter bases on android support-v4 LocalBroadcastManager is a library. Server side sends messages to client side with actionName.

LEARN MORE ABOUT MessagesCenter:
  - activity-to-activity, fragment-to-fragment, activity-to-fragment.
  - one-to-one, one-to-many, many-to-many.
  - MessagesCenter supports String, int, double, bundle...etc.

3 EASY STEPS TO START:
  - build.gradle in your module：
```Java
dependencies {
    compile 'catherine.mobile:messagescenter:+'
    }
```
  - Server side (fragments or activities)
```Java
AsyncResponse ar = new AsyncResponse() {
    @Override
    public void onFailure(int errorCode) {
        switch (errorCode) {
            case ErrorMessages.NULL_POINTER:
            //Action names or messages are null.
                Log.e("MainActivity", "MULTIPLE_VALUE");
                break;
            case ErrorMessages.MULTIPLE_VALUE:
            //You can't send multiple types of broadcast messages with same actionName at the same time, You need to rename this action.
                Log.e("MainActivity", "NULL_POINTER");
                   break;
           }
       }
};

Server sv = new Server(MainActivity.this, ar);
//There are pushInt(), pushBundle()...whatever you need
sv.pushString("I'm actionName A001", "Hi, bro!");
```
  - Client side (fragments or activities)
```Java
CustomReceiver cr = new CustomReceiver() {
    @Override
    public void onBroadcastReceive(Result result) {
    //Use getInt(), getBundle()...ect, depending on what your server side sends
        String messages = result.getString();
        Log.d("Fragment","I got:" + messages);
    }
};
    client = new Client(getActivity(), cr);
    client.gotMessages("I'm actionName A001");
```
  - Unregister on client side
```Java
@Override
public void onDestroy() {
    super.onDestroyView();
    client.release();
}
```
  - If you would like to show logs, you need following codes
```Java
Config.showDebugLog = true;
```
  - [Sample][github_MessageCenter_sample]

### Version
1.0.0

License
----
[Apache-2.0]


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Apache-2.0]: <https://opensource.org/licenses/Apache-2.0>
   [github_MessageCenter_sample]: <https://github.com/PlatformTech/API/tree/master/app/src/main/java/tw/com/softworld/api>

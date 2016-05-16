# 如何使用
### MessagesCenter
基於android broadcast封裝而成，利用actionName作為訊息識別碼，發送端送出訊息，接受端等待接收訊息。

特點：
  - 用於activities之間、fragments之間、activities與fragments的溝通
  - 一對一、一對多、多對多
  - 支援String, int, double, bundle...等類型的訊息傳遞

如何使用:
  - 導入library，在app的build.gradle中添加：
```Java
dependencies {
    compile 'com.softworld:messagescenter:1.0.0'
    }
```
  - 在發送端implemnets AsyncResponse
```Java
public class MainActivity extends Activity implements AsyncResponse{

@Override
    public void onFailure(int errorCode) {
    //只有發生錯誤時會回傳錯誤碼
        switch (errorCode) {
            case ErrorMessages.MULTIPLE_VALUE:
                //actionName被重複使用了，因此訊息發不出去
                //一個actionName只能打包一種類型的訊息
                break;
            case ErrorMessages.NULL_POINTER:
                //actionName或是訊息內容為空
                break;
        }
    }
}
```
  - 再加入：
```Java
//actionName為訊息識別碼，應為唯一值
BroadcastCenter bc = new BroadcastCenter(MainActivity.this);
bc.pushString("我是actionName A001", "你好啊");
```
  - 在接收端加入：
```Java
        CustomReceiver cr = new CustomReceiver() {
            @Override
            public void onBroadcastReceive(Result result) {
            //根據不同的訊息類型，呼叫getInt(), getBundle()...等等
                String messages ＝ result.getString();
                Log.d("myFragment","我收到了：" + messages);
            }
        };
        BroadcastCenter bc = new BroadcastCenter(getActivity(), cr);
        bc.gotMessages("我是actionName A001");
```
  - 釋放broadcast，在接收端加入：
```Java
    @Override
    public void onDestroy() {
        super.onDestroyView();
        bc.release();
    }
```
  - 預設會打印logs，可呼叫下列代碼進行修改：
```Java
Config.showDebugLog = false;
```
  - [參考範例][github_MessagesCenter_sample]

### Version
1.0.0

License
----
[Apache-2.0]


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Apache-2.0]: <https://opensource.org/licenses/Apache-2.0>
   [github_MessagesCenter_sample]: <https://github.com/PlatformTech/API/tree/master/app/src/main/java/tw/com/softworld/api>

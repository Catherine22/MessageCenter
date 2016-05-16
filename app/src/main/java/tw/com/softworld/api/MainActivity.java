package tw.com.softworld.api;

import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softworld.messagescenter.AsyncResponse;
import com.softworld.messagescenter.BroadcastCenter;
import com.softworld.messagescenter.ErrorMessages;

import tw.com.softworld.api.broadcastSample.InboxFragment1;
import tw.com.softworld.api.broadcastSample.InboxFragment2;
import tw.com.softworld.api.broadcastSample.InboxFragment3;

public class MainActivity  extends FragmentActivity implements AsyncResponse {

    private Button bt_f, bt_back, bt_next;
    private EditText et_msg;
    private Fragment inboxFragment1, inboxFragment2, inboxFragment3;
    private BroadcastCenter bc;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();
    }

    private void findview() {
        inboxFragment1 = new InboxFragment1();
        inboxFragment2 = new InboxFragment2();
        inboxFragment3 = new InboxFragment3();

        replace(inboxFragment1);


        et_msg = (EditText) findViewById(R.id.et_messages);

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    switch (page) {
                        case 2:
                            replace(inboxFragment1);
                            page--;
                            break;
                        case 3:
                            replace(inboxFragment2);
                            page--;
                            break;
                    }
                }
            }
        });
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < 3) {
                    switch (page) {
                        case 1:
                            replace(inboxFragment2);
                            page++;
                            break;
                        case 2:
                            replace(inboxFragment3);
                            page++;
                            break;
                    }
                }
            }
        });

        bt_f = (Button) findViewById(R.id.bt_send_to_f);
        bt_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messages = et_msg.getText().toString();

                Bundle bundle1 = new Bundle();
                bundle1.putString("bundle1", messages);

                bc = new BroadcastCenter(MainActivity.this);
                bc.pushBundle("A001", bundle1);
                bc.pushString("A002", "hello I am a string");


            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onFailure(int errorCode) {
        switch (errorCode) {
            case ErrorMessages.MULTIPLE_VALUE:
                Log.e("MainActivity", "MULTIPLE_VALUE");
                break;
            case ErrorMessages.NULL_POINTER:
                Log.e("MainActivity", "NULL_POINTER");
                break;
        }
    }
}


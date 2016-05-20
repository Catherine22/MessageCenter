package tw.com.softworld.api;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tw.com.softworld.messagescenter.AsyncResponse;
import tw.com.softworld.messagescenter.ErrorMessages;
import tw.com.softworld.messagescenter.Server;

public class MainActivity extends FragmentActivity {

    private EditText et_msg;
    private Fragment fragment1, fragment2, fragment3;
    private Server sv;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();
    }

    private void findview() {
        fragment1 = new fragment1();
        fragment2 = new fragment2();
        fragment3 = new fragment3();

        replace(fragment1);

        //init fragment4
        Fragment fragment4 = new fragment4();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_down, fragment4);
        transaction.commitAllowingStateLoss();

        et_msg = (EditText) findViewById(R.id.et_messages);

        Button bt_back = (Button) findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    switch (page) {
                        case 2:
                            replace(fragment1);
                            page--;
                            break;
                        case 3:
                            replace(fragment2);
                            page--;
                            break;
                    }
                }
            }
        });
        Button bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < 3) {
                    switch (page) {
                        case 1:
                            replace(fragment2);
                            page++;
                            break;
                        case 2:
                            replace(fragment3);
                            page++;
                            break;
                    }
                }
            }
        });

        Button bt_f = (Button) findViewById(R.id.bt_send_to_f);
        bt_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messages = et_msg.getText().toString();

                Bundle bundle1 = new Bundle();
                bundle1.putString("bundle1", messages);

                AsyncResponse ar = new AsyncResponse() {
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
                };

                sv = new Server(MainActivity.this, ar);
                sv.pushBundle("A001", bundle1);
                //sv.pushString("A002", "Hi, bro!");
                //sv.pushInt("A004", 2345);


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

}


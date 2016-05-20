package tw.com.softworld.api;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.com.softworld.messagescenter.Client;
import tw.com.softworld.messagescenter.CustomReceiver;
import tw.com.softworld.messagescenter.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment2 extends Fragment {
    private Client client;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        String msg = "Hello blank fragment2";
        tv.setText(msg);

        CustomReceiver cr = new CustomReceiver() {
            @Override
            public void onBroadcastReceive(Result result) {
                doSomething(result);
            }
        };
        client = new Client(getActivity(), cr);
        client.gotMessages("A002");
        return view;
    }

    private void doSomething(Result result) {
        String text = result.getString();
        tv.setText(text);
    }

    @Override
    public void onDestroy() {
        super.onDestroyView();
        client.release();
    }
}

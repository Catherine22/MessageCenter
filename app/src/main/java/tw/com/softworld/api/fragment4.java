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
public class fragment4 extends Fragment {
    private TextView tv;
    private Client client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        String msg = "Hello blank fragment4";
        tv.setText(msg);

        CustomReceiver cr = new CustomReceiver() {
            @Override
            public void onBroadcastReceive(Result result) {
                doSomething(result);
            }
        };
        client = new Client(getActivity(), cr);
        client.gotMessages("A004");
        return view;
    }

    private void doSomething(Result result) {
        int num = result.getInt();
        String msg = "I got number " + num;
        tv.setText(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroyView();
        client.release();
    }
}

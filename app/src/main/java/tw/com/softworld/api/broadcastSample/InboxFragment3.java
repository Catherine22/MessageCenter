package tw.com.softworld.api.broadcastSample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softworld.messagescenter.BroadcastCenter;
import com.softworld.messagescenter.CustomReceiver;
import com.softworld.messagescenter.Result;

import tw.com.softworld.api.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment3 extends Fragment {

    private BroadcastCenter bc;
    private CustomReceiver cr;
    private TextView tv;


    public InboxFragment3() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("Hello blank fragment3");

        cr = new CustomReceiver() {
            @Override
            public void onBroadcastReceive(Result result) {
                doSomething(result);
            }
        };
        bc = new BroadcastCenter(getActivity(), cr);
        bc.gotMessages("A003");
        return view;
    }

    private void doSomething(Result result) {
        String text = result.getBundle().getString("bundle3");
        tv.setText(text);
    }

    @Override
    public void onDestroy() {
        super.onDestroyView();
        bc.release();
    }
}

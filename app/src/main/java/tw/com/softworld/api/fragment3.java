package tw.com.softworld.api;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.com.softworld.messagescenter.AsyncResponse;
import tw.com.softworld.messagescenter.ErrorMessages;
import tw.com.softworld.messagescenter.Server;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("Hello blank fragment3\nsend number 1234 to fragment4");

        AsyncResponse ar = new AsyncResponse() {
            @Override
            public void onFailure(int errorCode) {
                switch (errorCode) {
                    case ErrorMessages.MULTIPLE_VALUE:
                        Log.e("fragment3", "MULTIPLE_VALUE");
                        break;
                    case ErrorMessages.NULL_POINTER:
                        Log.e("fragment3", "NULL_POINTER");
                        break;
                }
            }
        };
        Server sv = new Server(getActivity(), ar);
        sv.pushInt("A004", 1234);
        return view;
    }
}

package com.ampsoft.sendback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ampsoft.sendback.etc.ProfileActivity;

/**
 * Created by saki on 16. 11. 24..
 */
public class MessageFragment extends Fragment {

    TextView tvMessageSave;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);

        tvMessageSave = (TextView) v.findViewById(R.id.tv_message_save);

        tvMessageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaveActicity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;
    }
}

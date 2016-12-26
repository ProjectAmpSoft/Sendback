package com.ampsoft.sendback.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ampsoft.sendback.DrawerActivity;
import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 11. 24..
 */
public class Guide3Fragment extends Fragment {
    Context mContext;

    TextView tvGuide3Next;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View v = inflater.inflate(R.layout.fragment_guide3, container, false);

        tvGuide3Next = (TextView) v.findViewById(R.id.tv_guide3_next);

        tvGuide3Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DrawerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;
    }
}
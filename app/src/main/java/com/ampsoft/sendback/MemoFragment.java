package com.ampsoft.sendback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.ampsoft.sendback.etc.ProfileActivity;

/**
 * Created by saki on 16. 11. 24..
 */
public class MemoFragment extends Fragment {

    Context mContext;
    ViewFlipper flipper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_memo, container, false);

        flipper = (ViewFlipper) v.findViewById(R.id.viewflipper_memo);

        flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));

        flipper.setFlipInterval(1000 * 5);
        flipper.startFlipping();

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.showNext();
            }
        });

        return v;
    }
}

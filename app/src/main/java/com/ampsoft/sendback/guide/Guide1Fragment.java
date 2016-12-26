package com.ampsoft.sendback.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 11. 24..
 */
public class Guide1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide1, container, false);

        return v;
    }
}

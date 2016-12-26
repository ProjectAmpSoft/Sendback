package com.ampsoft.sendback;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampsoft.sendback.etc.CouponActivity;
import com.ampsoft.sendback.etc.CreditActivity;
import com.ampsoft.sendback.etc.NoticeActivity;
import com.ampsoft.sendback.etc.ProfileActivity;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;

/**
 * Created by saki on 16. 11. 24..
 */
public class EtcFragment extends Fragment {

    TextView tvEtcId, tvEtcEmail, tvEtcNotice, tvEtcCredit, tvEtcCoupon;
    ImageView ivEtcEdit;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_etc, container, false);

        tvEtcId = (TextView) v.findViewById(R.id.tv_etc_id);
        tvEtcEmail = (TextView) v.findViewById(R.id.tv_etc_email);
        ivEtcEdit = (ImageView) v.findViewById(R.id.iv_etc_edit);
        tvEtcNotice = (TextView) v.findViewById(R.id.tv_etc_notice);
        tvEtcCredit = (TextView) v.findViewById(R.id.tv_etc_credit);
        tvEtcCoupon = (TextView) v.findViewById(R.id.tv_etc_coupon);

        SendbackSharedPreferences pref = new SendbackSharedPreferences(getActivity());

        tvEtcId.setText(pref.getString("id"));
        tvEtcEmail.setText(pref.getString("email"));

        ivEtcEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tvEtcNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tvEtcCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreditActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tvEtcCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;
    }
}

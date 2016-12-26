package com.ampsoft.sendback.proc;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.ampsoft.sendback.lib.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saki on 16. 12. 12..
 */
public class ProcessJoin extends AsyncTask<ArrayList<String>, Void, String> {

    private Context mContext;

    public ProcessJoin(Context c) {
        this.mContext = c;
    }

    @Override
    protected String doInBackground(final ArrayList<String>... params) {
        String result = "0";
        Functions functions = new Functions();
        JSONObject json = functions.procJoin(params[0].get(0), params[0].get(1), params[0].get(2), params[0].get(3), params[0].get(4), params[0].get(5));
        Log.d("test01", json.toString());

        try {
            if (json.getString("success") != null) {
                String res = json.getString("success");
                if (Integer.parseInt(res) == 1) {

                } else {
                    result = json.getString("error");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (Integer.parseInt(result) == 0) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(mContext, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    ((Activity) mContext).finish();
                }
            });
        } else if (Integer.parseInt(result) > 0) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast toast = Toast.makeText(mContext, "이미 가입된 계정입니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }

    }
}

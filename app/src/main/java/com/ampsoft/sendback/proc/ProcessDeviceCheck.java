package com.ampsoft.sendback.proc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.ampsoft.sendback.DrawerActivity;
import com.ampsoft.sendback.lib.Functions;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saki on 16. 12. 16..
 */
public class ProcessDeviceCheck extends AsyncTask<ArrayList<String>, Void, String> {
    private Context mContext;

    public ProcessDeviceCheck(Context c) {
        this.mContext = c;
    }

    @Override
    protected String doInBackground(ArrayList<String>... params) {
        String result = "0";
        Functions functions = new Functions();
        JSONObject json = functions.procDeviceCheck(params[0].get(0));

        try {
            if (json.getString("success") != null) {
                String res = json.getString("success");
                if (Integer.parseInt(res) == 1) {
                    SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
//                    json.getString("device_id").equals(pref.getString("device_id"));

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
//            ((Activity) mContext).runOnUiThread(new Runnable() {

//                @Override
//                public void run() {
//                    Intent intent = new Intent(mContext, DrawerActivity.class);
//                    mContext.startActivity(intent);
//                    ((Activity) mContext).finish();
//                }
//            });

        } else if (Integer.parseInt(result) > 0) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast toast = Toast.makeText(mContext, "아이디가 틀렸습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }
    }
}

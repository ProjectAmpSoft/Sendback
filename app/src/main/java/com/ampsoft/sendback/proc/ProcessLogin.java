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
 * Created by saki on 16. 12. 12..
 */
public class ProcessLogin extends AsyncTask<ArrayList<String>, Void, String> {
    private Context mContext;

    public ProcessLogin(Context c) {
        this.mContext = c;
    }

    @Override
    protected String doInBackground(ArrayList<String>... params) {
        String result = "0";
        Functions functions = new Functions();
        JSONObject json = functions.procLogin(params[0].get(0), params[0].get(1));

        try {
            if (json.getString("success") != null) {
                String res = json.getString("success");
                if (Integer.parseInt(res) == 1) {
                    JSONObject userObj = json.getJSONObject("user");

                    SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);

                    Log.d("test01", "" + json.getJSONObject("user"));
                    pref.putString("id", json.getString("uid"));
                    pref.putString("pw", userObj.getString("pw"));
                    pref.putString("name", userObj.getString("name"));
                    pref.putString("phone", userObj.getString("phone"));
                    pref.putString("email", userObj.getString("email"));
                    pref.putString("img", userObj.getString("img"));
                    pref.putString("message", userObj.getString("message"));
                    pref.putString("limite_date", userObj.getString("limite_date"));

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
                    Intent intent = new Intent(mContext, DrawerActivity.class);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                }
            });

        } else if (Integer.parseInt(result) > 0) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast toast = Toast.makeText(mContext, "아이디 또는 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }
    }
}

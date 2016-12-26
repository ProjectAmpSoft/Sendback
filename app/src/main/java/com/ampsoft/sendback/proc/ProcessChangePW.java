package com.ampsoft.sendback.proc;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.ampsoft.sendback.lib.Functions;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saki on 16. 12. 19..
 */
public class ProcessChangePw extends AsyncTask<ArrayList<String>, Void, String> {
    private Context mContext;

    public ProcessChangePw(Context c) {
        this.mContext = c;
    }

    @Override
    protected String doInBackground(final ArrayList<String>... params) {
        String result = "0";
        Functions functions = new Functions();
        JSONObject json = functions.procChangPw(params[0].get(0), params[0].get(1));

        try {
            if (json.getString("success") != null) {
                String res = json.getString("success");
                if (Integer.parseInt(res) == 1) {

                    SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
                    pref.putString("pw", json.getString("pw"));

                }
            } else {
                result = json.getString("error");
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
                    Toast toast = Toast.makeText(mContext, "비밀번호 변경이 완료되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        } else if (Integer.parseInt(result) > 0) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast toast = Toast.makeText(mContext, "비밀번호 변경에 실패하였습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }
    }
}

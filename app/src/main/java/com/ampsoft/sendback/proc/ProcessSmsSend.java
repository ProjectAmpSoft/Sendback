package com.ampsoft.sendback.proc;

import android.content.Context;
import android.os.AsyncTask;

import com.ampsoft.sendback.lib.Functions;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saki on 16. 12. 17..
 */
public class ProcessSmsSend extends AsyncTask<ArrayList<String>, Void, String> {
    private Context mContext;

    public ProcessSmsSend(Context c) {
        this.mContext = c;
    }

    @Override
    protected String doInBackground(ArrayList<String>... params) {
        String result = "";
        Functions functions = new Functions();
        JSONObject json = functions.procSmsSend(params[0].get(0), params[0].get(1));
        return result;
    }
}


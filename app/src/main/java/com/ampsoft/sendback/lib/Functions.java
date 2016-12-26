package com.ampsoft.sendback.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

/**
 * Created by saki on 16. 11. 13..
 */
public class Functions {
    private JSONParser jsonParser;
    private static String SendbackUrl = "http://ampsoft.cafe24.com/sendback/functions.php";

    public Functions() {
        jsonParser = new JSONParser();
    }
    public JSONObject procSmsSend(String num, String phone) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "smssend"));
        params.add(new BasicNameValuePair("num", num));
        params.add(new BasicNameValuePair("phone", phone));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procJoin(String id, String pw, String name, String phone, String deviceid, String email) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "join"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("pw", pw));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("device_id", deviceid));
        params.add(new BasicNameValuePair("email", email));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procDeviceCheck(String id) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "device_check"));
        params.add(new BasicNameValuePair("id", id));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procLogin(String id, String pw) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "login"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("pw", pw));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procDataReflash(String id, String phone) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "datareflash"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("phone", phone));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procIdSearch(String name, String phone) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "idsearch"));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("phone", phone));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procPwSearch(String id, String phone) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "pwsearch"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("phone", phone));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procChangPw(String id, String pw) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "changepw"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("pw", pw));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }

    public JSONObject procChangEmail(String id, String email) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "changeemail"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("email", email));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }


    public JSONObject procCoupon(String id, String pw) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "login"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("pw", pw));

        Log.d("test01", SendbackUrl);
        Log.d("test01", params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SendbackUrl, params);
        return json;
    }
}
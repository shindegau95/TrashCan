package com.pkg.android.trashcan;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAURAV on 29-03-2017.
 */

public class BinInfoLab {
    public static BinInfoLab sBinInfoLab;

    private ArrayList<BinInfo> binList;

    public ArrayList<BinInfo> getBinList() {
        return binList;
    }

    public void setBinList(ArrayList<BinInfo> binList) {
        this.binList = binList;
    }

    public BinInfoLab(Context context) {
        binList = new ArrayList<>();
        new GetData().execute();
        setBinList(binList);
    }

    public static BinInfoLab get(Context context){
        if(sBinInfoLab == null){
            return new BinInfoLab(context);
        }
        return sBinInfoLab;
    }

    public class GetData extends AsyncTask<Void, Void, Void> {

        private final String TAG = "GetData";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("http://sandeepg22.webutu.com/get.php");//(String.valueOf(R.string.url));

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray results = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);

                        String bin_id = c.getString("id");
                        String location = c.getString("location");
                        String datetime = c.getString("datetime");
                        String level = c.getString("level");
                        String measurement_id = c.getString("measurement_id");

                        BinInfo b = new BinInfo(bin_id, location, datetime, level, measurement_id); //here level and datetime are interchanged


                        Log.d("BIN", "BIN " + i + " = " + b.toString());
                        getBinList().add(b);


                    }
                } catch (final JSONException e) {
                    Log.d(TAG, "Json parsing error: " + e.getMessage());


                }
            } else {
                Log.d(TAG, "Couldn't get json from server.");


            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            /**
             * Updating parsed JSON data into ListView
             * */
        }


    }

}

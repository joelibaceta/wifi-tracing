package com.ypasoft.contactracing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ypasoft.contactracing.helpers.PermissionHelper;
import com.ypasoft.contactracing.tools.ShowDialogMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btnSearch;
    public WifiManager wifiManager;
    public ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();
    public ArrayAdapter adapter;
    public RequestQueue mRequestQueue;
    public JsonObjectRequest jsonObjectRequest;
    // public String url = "http://192.168.1.11:3000/positions/save";
    public String url = "http://198.199.73.28:3000/positions/save";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listWifi);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissions();
            }
        });

    }

    public void getPermissions() {
        if (PermissionHelper.getLocation(MainActivity.this)) {
            scan();
        }
    }

    public void goToSttings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                this.getPackageName(),
                null);
        intent.setData(uri);
        startActivity(intent);
    }

    public void scan() {
        arrayList.clear();
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, intentFilter);
        wifiManager.startScan();
        Toast.makeText(this, "Buscando WiFi ...", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PermissionHelper.MY_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scan();
                } else {
                    ShowDialogMessage showDialogMessage = new ShowDialogMessage(MainActivity.this);
                    showDialogMessage.showOpenSettings();
                }
        }
    }


    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        // @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> results = wifiManager.getScanResults();
            Toast.makeText(MainActivity.this, "Actualizado WiFi ...", Toast.LENGTH_SHORT).show();

            List<Double> arrayDistance = new ArrayList<>();
            for (ScanResult result : results) {
                int level = result.level;
                String status = null;
                if (level <= 0 && level >= -50) {
                    //Best signal
                    status = "Best signal " + level;
                } else if (level < -50 && level >= -70) {
                    //Good signal
                    status = "Good signal " + level;
                } else if (level < -70 && level >= -80) {
                    //Low signal
                    status = "Low signal " + level;
                } else if (level < -80 && level >= -100) {
                    //Very weak signal
                    status = "Very weak signal " + level;
                } else {
                    // no signals
                    status = "No signal " + level;
                }
                Double distance = calculateDistance(result.level, result.frequency);
                arrayList.add(result.SSID + " | " + status + " | " + result.BSSID + " | "
                        + result.frequency + " | " + distance);
//                sendDataToWify(arrayDistance, nick);
                arrayDistance.add(distance);
                adapter.notifyDataSetChanged();
            }
            String nick = "Casa";
            sendDataToWify(arrayDistance, nick);
        }
    };

    public double calculateDistance(double signalLevelInDb, double freqInMHz) {
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    public void sendDataToWify(final List<Double> arrayDistance, final String nick) {

        JSONArray jsonArray = new JSONArray();
        for (int x=0; arrayDistance.size() > x; x++) {
            jsonArray.put(arrayDistance.get(x));
        }

        JSONObject obj=new JSONObject();
        try {
            obj.put("distances", jsonArray);
            obj.put("id", nick);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, obj ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                android.util.Log.e("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }

}
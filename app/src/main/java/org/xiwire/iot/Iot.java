package org.xiwire.iot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Iot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        final Switch lampswitch = (Switch) findViewById(R.id.lamp);

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.201:8080/iot/lamp";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(this.getClass().getName(),response);
                        if(response.equalsIgnoreCase("true")){
                            lampswitch.setChecked(true);
                        }else{
                            lampswitch.setChecked(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(this.getClass().getName(),error.getMessage());
                    }
                });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


        lampswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(this.getClass().getName(),isChecked?"ON":"OFF");
                String onOff = "";
                if(isChecked){
                    onOff="on";
                }else{
                    onOff="off";
                }
                String url ="http://192.168.0.201:8080/iot/lamp/"+onOff;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i(this.getClass().getName(),response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(this.getClass().getName(),error.getMessage());
                            }
                        });
// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}

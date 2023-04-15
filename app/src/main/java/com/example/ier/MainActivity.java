package com.example.ier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ib, eb, tb;
    private TextView gtv, ctv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            setTheme(R.style.LightTheme);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            setTheme(R.style.DarkTheme);
        }

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        gtv = findViewById(R.id.textView_greet);
        ctv = findViewById(R.id.textView_connection);

        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);

        if (currentHourIn24Format >= 6 && currentHourIn24Format <=11){
            gtv.setText("Good Morning");
        }
        else if (currentHourIn24Format >= 12 && currentHourIn24Format <= 17){
            gtv.setText("Good Afternoon");
        }
        else if (currentHourIn24Format >= 18 && currentHourIn24Format <= 20){
            gtv.setText("Good Evening");
        }
        else {
            gtv.setText("Good Night");
        }

        ib = findViewById(R.id.button_income);
        eb = findViewById(R.id.button_expenses);
        tb = findViewById(R.id.button_theme);

        ib.setOnClickListener(this);
        eb.setOnClickListener(this);
        tb.setOnClickListener(this);
        ctv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_income) {
            Intent i = new Intent(getApplicationContext(), IncomeActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.button_expenses){
            Intent i2 = new Intent(getApplicationContext(), ExpensesActivity.class);
            startActivity(i2);
        } else if(v.getId() == R.id.button_theme){
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            //finish();
            //startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
        } else if (v.getId() == R.id.textView_connection){
            check_connection();
        }
    }

    public void check_connection(){
        final ProgressDialog loading = ProgressDialog.show(this,"Checking Connection","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setMessage(response.toString())
                                .setTitle("Last Record");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                dialog.dismiss();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                parmas.put("action","testConnection");
                return parmas;
            }
        };

        // 10 seconds
        int socketTimeOut = 10000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}

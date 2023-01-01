package com.example.ier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class IncomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_icat, tv_iamount, tv_icur, tv_ipay;

    private RadioGroup rg_icat, rg_icur, rg_ipay;
    private RadioButton rb_icat1, rb_icat2, rb_icat3, rb_icato;
    private RadioButton rb_icur1, rb_icur2, rb_icur3, rb_icur4, rb_icuro;
    private RadioButton rb_ipay1, rb_ipay2, rb_ipay3, rb_ipay4, rb_ipayo;

    private EditText et_icato, et_idescription, et_iamount, et_icuro, et_ipayo, et_imore;

    private Button isubmitb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.activity_income);

        this.setTitle("Income");

        /*
        // Categories
        tv_icat = findViewById(R.id.textView_income_catt);
        rg_icat = findViewById(R.id.radioGroup_income_cat);
        rb_icat1 = findViewById(R.id.radioButton_income_cat1);
        rb_icat2 = findViewById(R.id.radioButton_income_cat2);
        rb_icat3 = findViewById(R.id.radioButton_income_cat3);
        rb_icato = findViewById(R.id.radioButton_income_cato);

        // Amount
        tv_iamount = findViewById(R.id.textView_income_amountt);

        // Currencies
        tv_icur = findViewById(R.id.textView_income_curt);
        rg_icur = findViewById(R.id.radioGroup_income_cur);
        rb_icur1 = findViewById(R.id.radioButton_income_cur1);
        rb_icur2 = findViewById(R.id.radioButton_income_cur2);
        rb_icur3 = findViewById(R.id.radioButton_income_cur3);
        rb_icur4 = findViewById(R.id.radioButton_income_cur4);
        rb_icuro = findViewById(R.id.radioButton_income_curo);

        // Payment Methods
        tv_ipay = findViewById(R.id.textView_income_payt);
        rg_ipay = findViewById(R.id.radioGroup_income_pay);
        rb_ipay1 = findViewById(R.id.radioButton_income_pay1);
        rb_ipay2 = findViewById(R.id.radioButton_income_pay2);
        rb_ipay3 = findViewById(R.id.radioButton_income_pay3);
        rb_ipay4 = findViewById(R.id.radioButton_income_pay4);
        rb_ipayo = findViewById(R.id.radioButton_income_payo);

        // EditText
        et_icato = findViewById(R.id.editText_income_category);
        et_idescription = findViewById(R.id.editText_income_description);
        et_iamount = findViewById(R.id.editText_income_amount);
        et_icuro = findViewById(R.id.editText_income_currency);
        et_ipayo = findViewById(R.id.editText_income_payment);
        et_imore = findViewById(R.id.editText_income_more);

        // Button
        isubmitb = findViewById(R.id.button_income_submit);

        // Make them GONE
        et_icato.setVisibility(View.GONE);
        et_icuro.setVisibility(View.GONE);
        et_ipayo.setVisibility(View.GONE);

        // Categories
        rg_icat.setOnClickListener(this);
        rb_icat1.setOnClickListener(this);
        rb_icat2.setOnClickListener(this);
        rb_icat3.setOnClickListener(this);
        rb_icato.setOnClickListener(this);

        // Currencies
        rg_icur.setOnClickListener(this);
        rb_icur1.setOnClickListener(this);
        rb_icur2.setOnClickListener(this);
        rb_icur3.setOnClickListener(this);
        rb_icur4.setOnClickListener(this);
        rb_icuro.setOnClickListener(this);

        // Payment Methods
        rg_ipay.setOnClickListener(this);
        rb_ipay1.setOnClickListener(this);
        rb_ipay2.setOnClickListener(this);
        rb_ipay3.setOnClickListener(this);
        rb_ipay4.setOnClickListener(this);
        rb_ipayo.setOnClickListener(this);

        // Button
        isubmitb.setOnClickListener(this);
        */
    }

    @Override
    public void onClick(View v) {
        /*
        switch(v.getId()){
            case R.id.radioButton_income_cato: {
                et_icato.setVisibility(View.VISIBLE);
                et_icato.requestFocus();
                break;
            }
            case R.id.radioButton_income_cat1:
            case R.id.radioButton_income_cat2:
            case R.id.radioButton_income_cat3: {
                et_icato.setVisibility(View.GONE);
                break;
            }
            case R.id.radioButton_income_curo: {
                et_icuro.setVisibility(View.VISIBLE);
                et_icuro.requestFocus();
                break;
            }
            case R.id.radioButton_income_cur1:
            case R.id.radioButton_income_cur2:
            case R.id.radioButton_income_cur3:
            case R.id.radioButton_income_cur4: {
                et_icuro.setVisibility(View.GONE);
                break;
            }
            case R.id.radioButton_income_payo: {
                et_ipayo.setVisibility(View.VISIBLE);
                et_ipayo.requestFocus();
                break;
            }
            case R.id.radioButton_income_pay1:
            case R.id.radioButton_income_pay2:
            case R.id.radioButton_income_pay3:
            case R.id.radioButton_income_pay4: {
                et_ipayo.setVisibility(View.GONE);
                break;
            }
            default:
                break;
        }

        if(v.getId() == R.id.button_income_submit) {
            if(check()) {
                add_to_sheet();
            }
        }*/
    }

    /*public boolean check() {
        // Check the category
        if (rg_icat.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the category", Toast.LENGTH_SHORT).show();
            tv_icat.requestFocus();
            tv_icat.clearFocus();
            return false;
        }
        else {
            // other is checked
            if(rb_icato.isChecked() && et_icato.getText().toString().isEmpty()){
                tv_icat.requestFocus();
                tv_icat.clearFocus();
                et_icato.setError("Please enter the category");
                et_icato.requestFocus();
                return false;
            }
        }

        // Check the amount
        if(et_iamount.getText().toString().isEmpty()){
            tv_iamount.requestFocus();
            tv_iamount.clearFocus();
            et_iamount.setError("Please enter the amount");
            et_iamount.requestFocus();
            return false;
        }

        // Check the currency
        if (rg_icur.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the currency", Toast.LENGTH_SHORT).show();
            tv_icur.requestFocus();
            tv_icur.clearFocus();
            return false;
        }
        else {
            // other is checked
            if(rb_icuro.isChecked() && et_icuro.getText().toString().isEmpty()){
                tv_icur.requestFocus();
                tv_icur.clearFocus();
                et_icuro.setError("Please enter the currency");
                et_icuro.requestFocus();
                return false;
            }
        }

        // Check the payment method
        if (rg_ipay.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the payment method", Toast.LENGTH_SHORT).show();
            tv_ipay.requestFocus();
            tv_ipay.clearFocus();
            return false;
        }
        else {
            // one of the radio buttons is checked
            if(rb_ipayo.isChecked() && et_ipayo.getText().toString().isEmpty()){
                tv_ipay.requestFocus();
                tv_ipay.clearFocus();
                et_ipayo.setError("Please enter the payment method");
                et_ipayo.requestFocus();
                return false;
            }
        }
        return true;
    }*/

    /*private void add_to_sheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Record","Please wait");
        final String type = "Income";
        final String category;
        final String description;
        final String amount;
        final String currency;
        final String payment;
        final String more;

        int icat_selected = rg_icat.getCheckedRadioButtonId();
        if(icat_selected != R.id.radioButton_cato) {
            RadioButton rb_icat_selected = findViewById(icat_selected);
            category = rb_icat_selected.getText().toString();
        }
        else
            category = et_icato.getText().toString().trim();

        description = et_idescription.getText().toString().trim();

        amount = et_iamount.getText().toString().trim();

        int icur_selected = rg_icur.getCheckedRadioButtonId();
        if(icur_selected != R.id.radioButton_income_curo) {
            RadioButton rb_icur_selected = findViewById(icur_selected);
            currency = rb_icur_selected.getText().toString();
        }
        else
            currency = et_icuro.getText().toString().trim();

        int ipay_selected = rg_ipay.getCheckedRadioButtonId();
        if(ipay_selected != R.id.radioButton_income_payo) {
            RadioButton rb_ipay_selected = findViewById(ipay_selected);
            payment = rb_ipay_selected.getText().toString();
        }
        else
            payment = et_ipayo.getText().toString().trim();

        more = et_imore.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyDU1zT-c8L06DKp2bJHYUBNwDLsroPjslEbi4mv8nmLCpkYOtO/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        IncomeActivity.this.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Couldn't add the record for now, please check the connection", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                parmas.put("action","addItem");
                parmas.put("type", type);
                parmas.put("category", category);
                parmas.put("description", description);
                parmas.put("amount", amount);
                parmas.put("currency", currency);
                parmas.put("payment", payment);
                parmas.put("more", more);

                return parmas;
            }
        };


        // 10 seconds
        int socketTimeOut = 10000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
        */


}

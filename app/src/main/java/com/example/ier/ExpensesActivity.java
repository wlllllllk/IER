package com.example.ier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
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

public class ExpensesActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_cat, tv_desc, tv_amount, tv_cur, tv_pay, tv_more;
    private RadioGroup rg_cat_test, rg_cur_test, rg_pay_test;
    private RadioButton rb_cat[], rb_cur[], rb_pay[];
    private String cat[], cur[], pay[];
    private EditText et_description, et_amount, et_curo, et_payo, et_more;
    private ColorStateList rb_tint, et_tint;
    private int back_tint;
    private Drawable sb;
    private Button submitb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            setTheme(R.style.LightTheme);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            rb_tint = ColorStateList.valueOf(getResources().getColor(R.color.black));
            back_tint = getResources().getColor(R.color.black);
            sb = getDrawable(R.drawable.button_rounded);
        } else {
            setTheme(R.style.DarkTheme);
            rb_tint = ColorStateList.valueOf(getResources().getColor(R.color.white));
            back_tint = getResources().getColor(R.color.white);
            sb = getDrawable(R.drawable.button_rounded_dark);
        }

        et_tint = ColorStateList.valueOf(getResources().getColor(R.color.gray));

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(back_tint, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        setContentView(R.layout.activity_expenses);

        this.setTitle("Expenses");

        ScrollView sv = findViewById(R.id.ScrollView);
        LinearLayout ll = findViewById(R.id.LinearLayout);
        ConstraintLayout cl = findViewById(R.id.ConstraintLayout);

        LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        text_params.setMargins(0,50,0,0);

        // Categories
        tv_cat = new TextView(this);
        ll.addView(tv_cat);
        tv_cat.setText(getResources().getString(R.string.expense_title1));
        tv_cat.setTextAppearance(R.style.font_title);
        tv_cat.setLayoutParams(text_params);

        rg_cat_test = new RadioGroup(this);
        cat = getResources().getStringArray(R.array.cat);
        rb_cat = new RadioButton[cat.length];

        for(int i = 0; i <= rb_cat.length - 1; i++) {
            rb_cat[i] = new RadioButton(this);
            rb_cat[i].setId(i);
            rb_cat[i].setText(cat[i]);
            rg_cat_test.addView(rb_cat[i]);
            rb_cat[i].setTextAppearance(R.style.font_option);
            rb_cat[i].setButtonTintList(rb_tint);
            rb_cat[i].setOnClickListener(this);
        }
        ll.addView(rg_cat_test);


        // Description
        tv_desc = new TextView(this);
        ll.addView(tv_desc);
        tv_desc.setText(getResources().getString(R.string.expense_title2));
        tv_desc.setTextAppearance(R.style.font_title);
        tv_desc.setLayoutParams(text_params);

        et_description = new EditText(this);
        ll.addView(et_description);
        et_description.setHint(getResources().getString(R.string.optional));
        et_description.setTextAppearance(R.style.font_option);
        et_description.setBackgroundTintList(et_tint);


        // Amount
        tv_amount = new TextView(this);
        ll.addView(tv_amount);
        tv_amount.setText(getResources().getString(R.string.expense_title3));
        tv_amount.setTextAppearance(R.style.font_title);
        tv_amount.setLayoutParams(text_params);

        et_amount = new EditText(this);
        ll.addView(et_amount);
        et_amount.setHint(getResources().getString(R.string.required));
        et_amount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_amount.setTextAppearance(R.style.font_option);
        et_amount.setBackgroundTintList(et_tint);


        // Currencies
        tv_cur = new TextView(this);
        ll.addView(tv_cur);
        tv_cur.setText(getResources().getString(R.string.expense_title4));
        tv_cur.setTextAppearance(R.style.font_title);
        tv_cur.setLayoutParams(text_params);

        rg_cur_test = new RadioGroup(this);
        cur = getResources().getStringArray(R.array.cur);
        rb_cur = new RadioButton[cur.length];

        for(int i = 0; i <= rb_cur.length - 1; i++) {
            rb_cur[i] = new RadioButton(this);
            rb_cur[i].setId(rb_cat.length + i);
            rb_cur[i].setText(cur[i]);
            rg_cur_test.addView(rb_cur[i]);
            rb_cur[i].setTextAppearance(R.style.font_option);
            rb_cur[i].setButtonTintList(rb_tint);
            rb_cur[i].setOnClickListener(this);
        }
        ll.addView(rg_cur_test);

        et_curo = new EditText(this);
        ll.addView(et_curo);
        et_curo.setHint(getResources().getString(R.string.required));
        et_curo.setVisibility(View.GONE);
        et_curo.setTextAppearance(R.style.font_option);
        et_curo.setBackgroundTintList(et_tint);


        // Payment Methods
        tv_pay = new TextView(this);
        ll.addView(tv_pay);
        tv_pay.setText(getResources().getString(R.string.expense_title6));
        tv_pay.setTextAppearance(R.style.font_title);
        tv_pay.setLayoutParams(text_params);

        rg_pay_test = new RadioGroup(this);
        pay = getResources().getStringArray(R.array.pay);
        rb_pay = new RadioButton[pay.length];

        for(int i = 0; i <= rb_pay.length - 1; i++) {
            rb_pay[i] = new RadioButton(this);
            rb_pay[i].setId(rb_cat.length + rb_cur.length + i);
            rb_pay[i].setText(pay[i]);
            rg_pay_test.addView(rb_pay[i]);
            rb_pay[i].setTextAppearance(R.style.font_option);
            rb_pay[i].setButtonTintList(rb_tint);
            rb_pay[i].setOnClickListener(this);
        }
        ll.addView(rg_pay_test);

        et_payo = new EditText(this);
        ll.addView(et_payo);
        et_payo.setHint(getResources().getString(R.string.required));
        et_payo.setVisibility(View.GONE);
        et_payo.setTextAppearance(R.style.font_option);
        et_payo.setBackgroundTintList(et_tint);


        // More
        tv_more = new TextView(this);
        ll.addView(tv_more);
        tv_more.setText(getResources().getString(R.string.expense_title7));
        tv_more.setTextAppearance(R.style.font_title);
        tv_more.setLayoutParams(text_params);

        et_more = new EditText(this);
        ll.addView(et_more);
        et_more.setHint(getResources().getString(R.string.optional));
        et_more.setTextAppearance(R.style.font_option);
        et_more.setBackgroundTintList(et_tint);


        // Button
        LinearLayout.LayoutParams button_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        button_params.setMargins(0,60,50,60);

        submitb = new Button(this);
        submitb.setBackground(sb);
        ll.addView(submitb);
        submitb.setText(getResources().getString(R.string.submit));
        submitb.setTextColor(Color.parseColor("#FFFFFF"));
        submitb.setLayoutParams(button_params);
        submitb.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id > rb_cat.length - 1) {
            if (id <= rb_cat.length + rb_cur.length - 1) { // Currency
                if(id == rb_cat.length + rb_cur.length - 1){
                    et_curo.setVisibility(View.VISIBLE);
                    et_curo.requestFocus();
                } else {
                    et_curo.setVisibility(View.GONE);
                }
            } else if (id <= rb_cat.length + rb_cur.length + rb_pay.length - 1) { // Payment Method
                if (id == rb_cat.length + rb_cur.length + rb_pay.length - 1) {
                    et_payo.setVisibility(View.VISIBLE);
                    et_payo.requestFocus();
                } else {
                    et_payo.setVisibility(View.GONE);
                }
            }
        }

        if(id == submitb.getId()) {
            if(check()) {
                add_to_sheet();
            }
        }
    }

    public boolean check() {
        // Check the category
        if (rg_cat_test.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the category", Toast.LENGTH_SHORT).show();
            tv_cat.requestFocus();
            tv_cat.clearFocus();
            return false;
        }

        // Check the amount
        if(et_amount.getText().toString().isEmpty()){
            tv_amount.requestFocus();
            tv_amount.clearFocus();
            et_amount.setError("Please enter the amount");
            et_amount.requestFocus();
            return false;
        }

        // Check the currency
        if (rg_cur_test.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the currency", Toast.LENGTH_SHORT).show();
            tv_cur.requestFocus();
            tv_cur.clearFocus();
            return false;
        }
        else {
            // other is checked
            if(rb_cur[rb_cur.length - 1].isChecked() && et_curo.getText().toString().isEmpty()){
                tv_cur.requestFocus();
                tv_cur.clearFocus();
                et_curo.setError("Please enter the currency");
                et_curo.requestFocus();
                return false;
            }
        }

        // Check the payment method
        if (rg_pay_test.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getApplicationContext(), "Please select the payment method", Toast.LENGTH_SHORT).show();
            tv_pay.requestFocus();
            tv_pay.clearFocus();
            return false;
        }
        else {
            // one of the radio buttons is checked
            if(rb_pay[rb_pay.length - 1].isChecked() && et_payo.getText().toString().isEmpty()){
                tv_pay.requestFocus();
                tv_pay.clearFocus();
                et_payo.setError("Please enter the payment method");
                et_payo.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void add_to_sheet() {
        final ProgressDialog loading = ProgressDialog.show(this, "Adding Record", "Please wait");
        final String type = "Expenses";
        final String category;
        final String description;
        final String amount;
        final String currency;
        final String payment;
        final String more;

        int cat_selected = rg_cat_test.getCheckedRadioButtonId();

        RadioButton rb_cat_selected = findViewById(cat_selected);
        category = rb_cat_selected.getText().toString();

        description = et_description.getText().toString().trim();

        amount = et_amount.getText().toString().trim();

        int cur_selected = rg_cur_test.getCheckedRadioButtonId();
        if (cur_selected != rb_cat.length + rb_cur.length - 1) {
            RadioButton rb_cur_selected = findViewById(cur_selected);
            currency = rb_cur_selected.getText().toString();
        } else
            currency = et_curo.getText().toString().trim();

        int pay_selected = rg_pay_test.getCheckedRadioButtonId();
        if (pay_selected != rb_cat.length + rb_cur.length + rb_pay.length - 1) {
            RadioButton rb_pay_selected = findViewById(pay_selected);
            payment = rb_pay_selected.getText().toString();
        } else
            payment = et_payo.getText().toString().trim();

        more = et_more.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        ExpensesActivity.this.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Couldn't add record, please check your connection", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                parmas.put("action", "addItem");
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

        //10 seconds
        int socketTimeOut = 10000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}

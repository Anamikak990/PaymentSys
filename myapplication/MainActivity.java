package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.wallet.callback.PaymentAuthorizationResult;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPay = findViewById(R.id.bt_pay);

        String sAmount = "100";
        int amount = Math.round(Float.parseFloat(sAmount)*100);
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_IaCQ8As9G3CTsz");
                checkout.setImage(R.drawable.rzp_logo);
                JSONObject object = new JSONObject();
                try{
                    object.put("name","Anamika Kumari");
                    object.put("description", "Test Pay");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact","9284914389");
                    object.put("prefill.email","anamikakumari4622@gmail.com");
                    checkout.open(MainActivity.this,object);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_SHORT).show();
    }
}
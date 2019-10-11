 package com.example.helloworld;

 import android.content.Context;
 import android.os.Bundle;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

//import android.text.InputType;

public class MainActivity extends AppCompatActivity {

    EditText usdInput, egpInput;
//    boolean egpFocus = false, usdFocus = false;
    TextWatcher usd, egp;

    public String change(String currency, String value) {
        if (value.length()==0){
            return "";
        }

        if (value.startsWith(".")){

            value = "0." + value.substring(1);
        }
        switch (currency) {
            case "USD": {
                //return String.valueOf(Double.parseDouble(value) * 17);
                return String.format("%.2f",Double.parseDouble(value) * 17);
            }

            case "EGP": {
                //return String.valueOf(Double.parseDouble(value) / 17);
                return String.format("%.2f", Double.parseDouble(value)/17);
            }
        }

    return null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usdInput = findViewById(R.id.usdValue);
        egpInput = findViewById(R.id.egpValue);

         usd = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                egpInput.setText(change("USD",usdInput.getText().toString()));
            }
        };

         egp = new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 usdInput.setText(change("EGP", egpInput.getText().toString()));
             }
         };


        View.OnFocusChangeListener mFocusChangeListener = new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    switch (v.getId()){
                        case R.id.usdValue: {
                            egpInput.removeTextChangedListener(egp);
                            usdInput.addTextChangedListener(usd);
                        } break;
                        case R.id.egpValue: {
                            usdInput.removeTextChangedListener(usd);
                            egpInput.addTextChangedListener(egp);

                        } break;
                    }
                }
            }
        };

        usdInput.setOnFocusChangeListener(mFocusChangeListener);
        egpInput.setOnFocusChangeListener(mFocusChangeListener);

        Context context = getApplicationContext();
        CharSequence text = "Loaded successfully";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

}

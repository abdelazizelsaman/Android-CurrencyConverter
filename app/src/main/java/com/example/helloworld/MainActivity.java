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

        String returnValue = null;

        if (value.length()==0){
            return "";
        }

        if(value.length() == 13){

            Context context = getApplicationContext();

            CharSequence text2 = "Max limit reached!";

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text2, duration);
            toast.show();
        }

        if (value.startsWith(".")){

            value = "0." + value.substring(1);
        }
        switch (currency) {
            case "USD": {
                //return String.valueOf(Double.parseDouble(value) * 17);

                return String.format("%.2f", Double.parseDouble(value) * 16.2);

            }

            case "EGP": {
                //return String.valueOf(Double.parseDouble(value) / 17);

                return String.format("%.2f", Double.parseDouble(value)/16.2);

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
                String m = change("USD",usdInput.getText().toString());
                if(m.endsWith(".")){
                    m = m.substring(0, m.length()-1);
                }
                egpInput.setText(m);
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
                 String l = change("EGP", egpInput.getText().toString());
                 if(l.endsWith(".")){
                     l = l.substring(0,l.length()-1);
                 }
                 usdInput.setText(l);
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
        CharSequence text1 = "Loaded successfully";
        CharSequence text2 = "Max limit reached !";

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text1, duration);
        toast.show();

    }

}

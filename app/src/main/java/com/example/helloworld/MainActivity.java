 package com.example.helloworld;

 import android.os.Bundle;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.View;
 import android.widget.EditText;

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
        switch (currency) {
            case "USD": {
                return String.valueOf(Double.parseDouble(value) * 17);
            }

            case "EGP": {
                return String.valueOf(Double.parseDouble(value) / 17);
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

//        usdInput.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!(usdInput.getText().toString().length() == 0)){
//                    egpInput.setText(usdInput.getText() + "321");
//                }
//                return false;
//            }
//        });
//        egpInput.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!(egpInput.getText().toString().length() == 0)){
//                    usdInput.setText(egpInput.getText() + "123");
//
//                }
//                return false;
//            }
//        });

//
//        usdInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if(hasFocus){
//                    if(!(usdInput.getText().toString().length() == 0)){
//                        egpInput.setText(usdInput.getText() + "321");
//                    }
//
//
//                }
////                if(usdFocus){
////                    egpInput.removeTextChangedListener(egp);
////                    usdFocus = false;
////                }
////                usdInput.addTextChangedListener(usd);
//
//            }
//        });




//        egpInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    if(!(egpInput.getText().toString().length() == 0)){
//                        usdInput.setText(egpInput.getText() + "123");
//
//                    }
//
//                }
////                if (egpFocus) {
////                    usdInput.removeTextChangedListener(usd);
////                    egpFocus = false;
////                }
////                egpInput.addTextChangedListener(egp);
//            }
//        });

//        if(usdInput.isFocusable()){
//            if(usdFocus){
//                egpInput.removeTextChangedListener(egp);
//                usdFocus = false;
//            }
//            usdInput.addTextChangedListener(usd);
//
//        }else if(egpInput.isFocusable()) {
//            if (egpFocus) {
//                usdInput.removeTextChangedListener(usd);
//                egpFocus = false;
//            }
//            egpInput.addTextChangedListener(egp);
//        }




    }

}

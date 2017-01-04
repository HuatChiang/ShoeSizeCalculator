package com.huat.heighttoshoesizecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button calculate = (Button) findViewById(R.id.calculate);


        calculate.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView shoeSize = (TextView) findViewById(R.id.shoeSize);
                        EditText height = (EditText) findViewById(R.id.height);

                        double h = 0;
                        int g = onRadioButtonSex();
                        String shoeSizeFinal = null;
                        String s = null;
                        String height1 = (String) height.getText().toString();
                        if (g != 3) {
                            try {
                                h = Double.parseDouble(height1);
                                shoeSizeFinal = Double.toString(shoeSize(h, g));
                            } catch (Exception e) {
                                shoeSizeFinal = "Height must be a number";
                            }
                            shoeSize.setText(shoeSizeFinal);
                        }
                        else{
                            shoeSize.setText("Enter your gender");
                        }
                    }
                }
        );

    }
    public int onRadioButtonSex(){
        RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton male = (RadioButton) findViewById(R.id.radioButton);
        RadioButton female = (RadioButton) findViewById(R.id.radioButton2);

        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        if(selectedId == male.getId()){
            return 1;
        }
        else if(selectedId == female.getId()){
            return 2;
        }
        return 3;
    }

    public double shoeSize(double h, int g) {
        int gender = g;     //1 = man 2 = woman
        double height = h;
        double shoeSize = 0;

        if(gender == 1){
            shoeSize = height*.155;
            shoeSize = shoeSize*10;
            shoeSize = Math.round(shoeSize);
            shoeSize = shoeSize /10;
            return shoeSize;
        }
        else if(gender == 2){
            shoeSize = height*.145;
            shoeSize = shoeSize*10;
            shoeSize = Math.round(shoeSize);
            shoeSize = shoeSize/10;
            return shoeSize;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

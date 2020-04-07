package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class si extends AppCompatActivity {
EditText name,age,city;
CheckBox male,female;
Button button;
Boolean gender;

public static String sname,sage,sgender,scity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_si);

        gender= true;
name=findViewById(R.id.name);
age=findViewById(R.id.age);
male=findViewById(R.id.male);
female=findViewById(R.id.female);
button=findViewById(R.id.button);
city=findViewById(R.id.city);

male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            female.setChecked(false);
            gender=true;
        }else{
            male.setChecked(false);

        }
    }
});

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    male.setChecked(false);
                    gender=false;

                }else{
                    female.setChecked(false);

                }
            }
        });

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


         sname=name.getText().toString();
         sage=age.getText().toString();
         sgender="female";
         scity=city.getText().toString();
        if(gender) sgender="male";

        SharedPreferences sharedPreferences=getSharedPreferences("a",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",sname);
        editor.putString("age",sage);
        editor.putString("gender",sgender);

editor.commit();
        startActivity(new Intent(si.this,MainActivity.class));

    }
});


    }
}

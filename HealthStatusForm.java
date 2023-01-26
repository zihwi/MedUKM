package com.example.med4ukm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthStatusForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status_form);

            final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
            final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
            final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
            final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
            final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
            final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
            final CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
            final CheckBox checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
            final CheckBox checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
            final CheckBox checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
            final CheckBox checkBox11 = (CheckBox) findViewById(R.id.checkBox11);
            final CheckBox checkBox12 = (CheckBox) findViewById(R.id.checkBox12);



            Button button1 = (Button) findViewById(R.id.next);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = 0;

                    if (checkBox1.isChecked()) {
                        ++count;
                    }
                    if (checkBox2.isChecked()) {
                        ++count;
                    }
                    if (checkBox3.isChecked()) {
                        ++count;
                    }
                    if (checkBox4.isChecked()) {
                        ++count;
                    }
                    if (checkBox5.isChecked()) {
                        ++count;
                    }
                    if (checkBox6.isChecked()) {
                        ++count;
                    }
                    if (checkBox7.isChecked()) {
                        ++count;
                    }
                    if (checkBox8.isChecked()) {
                        ++count;
                    }
                    if (checkBox9.isChecked()) {
                        ++count;
                    }
                    if (checkBox10.isChecked()) {
                        ++count;
                    }
                    if (checkBox11.isChecked()) {
                        ++count;
                    }
                    if (checkBox12.isChecked()) {
                        ++count;
                    }
                  if (count >=1){
                      Intent intent = new Intent(getApplicationContext(), BadHealthReport.class);
                      startActivity(intent);
                  }
                  else{
                      Intent intent = new Intent(getApplicationContext(), HealthReport.class);
                      startActivity(intent);
                  }
                }
            });
        }

    }

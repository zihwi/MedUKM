package com.example.med4ukm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emergencycall extends AppCompatActivity {
Button buttoncallmo;
Button buttoncallp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycall);

        buttoncallmo =findViewById(R.id.upload_oc);
                buttoncallp =findViewById(R.id.call_p);

                buttoncallmo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:0126485541"));
                        startActivity(intent);
                    }
                });
        buttoncallp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0126485542"));
                startActivity(intent);
            }
        });
    }
}
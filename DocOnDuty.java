package com.example.med4ukm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DocOnDuty extends AppCompatActivity {
    ListView list;
    int[] image = {R.drawable.doc1, R.drawable.doc2, R.drawable.doc3, R.drawable.doc4, R.drawable.doc5, R.drawable.doc6, R.drawable.doc7};
    String[] name = {"Dr. Masdalina Binti Azhari", "Dr. Kamal Azura Bin Jaafar", "Dr. Nazalina Binti Mohamed Nawawi", "Dr. Arif Nasar Bin Sulaiman", "Dr. Lubaina Binti Zainal Abidin", "Dr. Dinatul Akmal Binti Abd Kadir", "Dr.Rizal Ariff Bin Zainal Abidin"};
    String[] docPosition = {"Pegawai Perubatan UD54", "Pegawai Perubatan UD52", "Pegawai Perubatan UD52", "Pegawai Perubatan UD48", "Pegawai Perubatan UD43", "Pegawai Perubatan UD48", "Pegawai Perubatan UD43"};
    String[] docPhoneNo = {"03 89213689", "03 89213668", "03 89213667", "03 89213554", "03 89213669", "03 89215171", "03 89213667"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_on_duty);


        list = findViewById(R.id.listviewdoc);

        MyAdapter adapter = new MyAdapter(this,image,name,docPosition,docPhoneNo);
        list.setAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        int image[];
        String myName[];
        String myPosition[];
        String myPhoneNo[];

        MyAdapter(Context c, int[] image, String[] name, String[] position,String[]phoneNo) {
            super(c, R.layout.row, R.id.textName, name);
            this.context = c;
            this.image = image;
            this.myName = name;
            this.myPosition = position;
            this.myPhoneNo = phoneNo;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView imgs = row.findViewById(R.id.profilePic);
            TextView myName = row.findViewById(R.id.textName);
            TextView myPosition = row.findViewById(R.id.textDocPosition);
            TextView myPhoneNo = row.findViewById(R.id.textdocHP);
            imgs.setImageResource(image[position]);
            myName.setText(name[position]);
            myPosition.setText(docPosition[position]);
            myPhoneNo.setText(docPhoneNo[position]);
            return row;
        }
    }
}
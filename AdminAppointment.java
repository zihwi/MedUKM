package com.example.med4ukm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.med4ukm.databinding.ActivityAdminAppointmentBinding;


import java.util.ArrayList;


public class AdminAppointment extends AppCompatActivity {

    ActivityAdminAppointmentBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,
                R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};
        String [] name = {"Christopher","Craig","Sergio","Muba","Mike","Gerba","Helen","Siti"};
        String [] lastMessage = {"Book an appointment","Say hi to Doctor","Book an appointment","Say hi to Doctor",
                                     "Say hi to Doctor","Say hi to Doctor","Say hi to Doctor","Say hi to Doctor"};
        String [] lastmsgTime = {"8:45pm","8:45pm","8:45pm","8:45pm","2:45pm",
                                  "2:45pm","2:45pm","2:45pm",};
        String [] phoneNo = {"012-1294829","012-1294829","012-1294829","012-1294829",
                                  "012-1294829","012-1294829","012-1294829","012-1294829"};
        String[] country = {"Malaysia","Malaysia","Malaysia","Malaysia",
                              "Malaysia","Malaysia","Malaysia","Malaysia"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i = 0; i<imageId.length; i++){
            User user = new User(name[i], lastMessage[i], lastmsgTime[i], phoneNo[i],country[i],imageId[i]);
            userArrayList.add(user);

        }
        //        setContentView(R.layout.activity_admin_appointment);
        ListAdapter listAdapter = new ListAdapter(AdminAppointment.this, userArrayList);


        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(AdminAppointment.this, UserActivity.class);
                i.putExtra("name", name[position]);
                i.putExtra("phone", phoneNo[position]);
                i.putExtra("country", country[position]);
                i.putExtra("imageid", imageId[position]);
                startActivity(i);

            }
        });
    }
}
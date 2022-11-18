package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewProfile extends AppCompatActivity {
    TextView UserName,Name,branch,yearofstudy,mobile,dob,hostelname;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Name = findViewById(R.id.profile_name);
        UserName = findViewById(R.id.Username);
        branch = findViewById(R.id.Branch);
        yearofstudy = findViewById(R.id.YearOfStudy);
        mobile = findViewById(R.id.MobileNo);
        dob = findViewById(R.id.prof_Dob);
        hostelname = findViewById(R.id.prof_hostel);
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String s_uname = snapshot.child("20MIS0251").child("Name").getValue(String.class);
                String s_name = snapshot.child("20MIS0251").child("Name").getValue(String.class);
                String s_mobile = snapshot.child("20MIS0251").child("Mobile").getValue(String.class);
                String s_dob = snapshot.child("20MIS0251").child("Dob").getValue(String.class);

                Name.setText(s_name);
                UserName.setText("20MIS0251");
                mobile.setText(s_mobile);
                dob.setText(s_dob);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("RoomInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Hostel = snapshot.child("20MIS0251").child("Block").getValue(String.class);
                hostelname.setText(Hostel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
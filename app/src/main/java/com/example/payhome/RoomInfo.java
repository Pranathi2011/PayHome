package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomInfo extends AppCompatActivity {
        TextView roomnum,roomtype,block;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        roomnum = findViewById(R.id.textView4);
        roomtype = findViewById(R.id.textView5);
        block = findViewById(R.id.textView6);

        databaseReference.child("RoomInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              MainActivity obj = new MainActivity();
              if(snapshot.equals(obj.User_Name)){
                  final String RoomNum = snapshot.child(obj.User_Name).child("RoomNumber").getValue(String.class);
                  final String RoomType = snapshot.child(obj.User_Name).child("RoomType").getValue(String.class);
                  final String Block = snapshot.child(obj.User_Name).child("Block").getValue(String.class);

                  roomnum.setText(RoomNum);
                  roomtype.setText(RoomType);
                  block.setText(Block);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
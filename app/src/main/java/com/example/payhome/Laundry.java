package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Laundry extends AppCompatActivity {
    EditText name,regNo,date,noOfClothes,roomNo;
    String Name,RegNo,Date,NoOfClothes,RoomNum;
    Button submit;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        name = findViewById(R.id.l_name);
        regNo = findViewById(R.id.l_regNo);
        date  = findViewById(R.id.l_noOfClothes);
        noOfClothes = findViewById(R.id.l_noOfClothes);
        roomNo = findViewById(R.id.l_room_no);

        submit = findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                RegNo = regNo.getText().toString();
                Date = date.getText().toString();
                NoOfClothes = noOfClothes.getText().toString();
                RoomNum = roomNo.getText().toString();
                databaseReference.child("Laundry").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("Laundry").child(RegNo).child("Name").setValue(Name);
                        databaseReference.child("Laundry").child(RegNo).child("Room Number").setValue(RoomNum);
                        databaseReference.child("Laundry").child(RegNo).child("Number Of Clothes").setValue(NoOfClothes);
                        databaseReference.child("Laundry").child(RegNo).child("Date").setValue(Date);
                        Toast.makeText(Laundry.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}
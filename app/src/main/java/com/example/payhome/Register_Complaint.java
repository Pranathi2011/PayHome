package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Complaint extends AppCompatActivity {
    String[] items = {"AC","Electricity","Water","Room Cleaning"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText room_no;
    String ComplaintType;
    Button b1;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String item = adapterView.getItemAtPosition(i).toString();
                ComplaintType = item;
            }
        });

        b1 = findViewById(R.id.but);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_no = findViewById(R.id.rc_room_no);
                String RoomNum = room_no.getText().toString();
                databaseReference.child("Register Complaint").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("Register Complaint").child("ComplaintType").setValue(ComplaintType);
                        databaseReference.child("Register Complaint").child("RoomNumber").setValue(RoomNum);
                        Dialog myDialog = new Dialog(Register_Complaint.this);
                        myDialog.setContentView(R.layout.custom_dialog);
                        Button but = myDialog.findViewById(R.id.but1);
                        but.setText("OK");
                        but.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myDialog.dismiss();
                            }
                        });
                        TextView Title = myDialog.findViewById(R.id.title);
                        TextView desc = myDialog.findViewById(R.id.desc);
                        Title.setText("Complaint Registered");
                        desc.setText("Complaint Type: "+ComplaintType+"\n Room Number:"+RoomNum);
                        myDialog.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}
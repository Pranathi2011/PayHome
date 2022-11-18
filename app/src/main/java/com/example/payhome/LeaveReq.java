package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeaveReq extends AppCompatActivity {
    String[] items = {"Home Town","Special Outing","Medical Leave","Leave with parents"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText fromDate,toDate,visitingPlace;
    String from,to,visitPlace,leavetype;
    Button b1;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_req);
        autoCompleteTextView = findViewById(R.id.auto_complete_text1);
        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);
        visitingPlace = findViewById(R.id.place);

        b1 = findViewById(R.id.submit);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                leavetype = item;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from = fromDate.getText().toString();
                to = toDate.getText().toString();
                visitPlace = visitingPlace.getText().toString();
                databaseReference.child("Leave request").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       databaseReference.child("Leave Request").child("LeaveType").setValue(leavetype);
                       databaseReference.child("Leave Request").child("FromDate").setValue(from);
                       databaseReference.child("Leave Request").child("ToDate").setValue(to);
                       databaseReference.child("Leave Request").child("Visiting Place").setValue(visitPlace);
                        Toast.makeText(LeaveReq.this, "Leave Request Submitted", Toast.LENGTH_SHORT).show();
                        fromDate.setText(null);
                        toDate.setText(null);
                        visitingPlace.setText(null);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}
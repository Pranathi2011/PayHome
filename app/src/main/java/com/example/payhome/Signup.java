package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    EditText name,reg_no,mobile,mailid,address,dateOfBirth;
    Button signup;
    String Name,Reg_No,Mobile,Mail_Id,Address,Dob;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.b1);
        name = findViewById(R.id.Name);
        reg_no =findViewById(R.id.RegNo);
        mobile = findViewById(R.id.phone);
        mailid = findViewById(R.id.mailid);
        address = findViewById(R.id.address);

        dateOfBirth = findViewById(R.id.dob);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                Reg_No = reg_no.getText().toString();
                Mobile = mobile.getText().toString();
                Mail_Id = mailid.getText().toString();
                Address = address.getText().toString();

                Dob = dateOfBirth.getText().toString();
                if (Name.isEmpty()||Reg_No.isEmpty()||Mobile.isEmpty()||Mail_Id.isEmpty()||Address.isEmpty()||Dob.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(Reg_No)){
                                Toast.makeText(Signup.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("Users").child(Reg_No).child("Name").setValue(Name);
                                databaseReference.child("Users").child(Reg_No).child("Mailid").setValue(Mail_Id);
                                databaseReference.child("Users").child(Reg_No).child("Address").setValue(Address);
                                databaseReference.child("Users").child(Reg_No).child("Mobile").setValue(Mobile);
                                databaseReference.child("Users").child(Reg_No).child("Dob").setValue(Dob);
                                databaseReference.child("Users").child(Reg_No).child("Password").setValue(Dob);

                                Toast.makeText(Signup.this, "SignUp Successfull", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }



            }
        });

    }
}
package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResetPassword extends AppCompatActivity {
    EditText uname,newpass,confirmnew;
    String username,New_Pass,Confirm_Pass;
    Button b1 ;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://payhome-671c2-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        uname = findViewById(R.id.reset_uname);
        newpass = findViewById(R.id.reset_pass);
        confirmnew = findViewById(R.id.confirm_pass);

        b1 = findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((username==null)||(New_Pass==null)||(Confirm_Pass==null))
                {
                    username = uname.getText().toString();
                    New_Pass = newpass.getText().toString();
                    Confirm_Pass = confirmnew.getText().toString();
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(username)){
                                if(New_Pass.equals(Confirm_Pass)){
                                    databaseReference.child("Users").child(username).child("Password").setValue(New_Pass);
                                    Toast.makeText(ResetPassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(ResetPassword.this, "Check if Confirm password is matching with new password", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(ResetPassword.this, "User Doesnt exists! \nSign Up to create new account", Toast.LENGTH_SHORT).show();
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
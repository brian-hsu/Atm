package com.brian.atm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserid = findViewById(R.id.userid);
        edPasswd = findViewById(R.id.passwd);

    }

    public void login(View view){
        String userid = edUserid.getText().toString();
        final String passwd = edPasswd.getText().toString();
        Log.d("LoginActivity", "passwd: " + passwd);
        FirebaseDatabase.getInstance().getReference("users").child(userid).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        Log.d("LoginActivity", "pw: " + pw);
                        if (pw.equals(passwd)){
                            setResult(RESULT_OK);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
/*        if (userid.equals("brian") && passwd.equals("1111")){
            setResult(RESULT_OK);
            finish();
        }*/
    }

    public void quit(View view){

    }
}

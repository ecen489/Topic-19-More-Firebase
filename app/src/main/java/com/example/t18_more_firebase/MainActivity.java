package com.example.t18_more_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

   FirebaseDatabase fbdb;
    DatabaseReference dbrf;
    EditText email;
    EditText ID;
    EditText name;
    EditText passwd;
    TextView myTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        email= findViewById(R.id.email);
        ID = findViewById(R.id.ID);
        name = findViewById(R.id.name);
        passwd = findViewById(R.id.passwd);

        myTxt = findViewById(R.id.myTxt);
    }

    public void buttonClick1(View view) {

        String stuEmail = email.getText().toString();
        int stuID = Integer.parseInt(ID.getText().toString());
        String stuName = name.getText().toString();
        String stuPass = passwd.getText().toString();

        Student stud = new Student(stuEmail,stuID,stuName,stuPass);

        DatabaseReference insLoc = dbrf.child("simpsons/students/");
        DatabaseReference ranKey = insLoc.push();
        ranKey.setValue(stud);
    }

    public void buttonClick2(View view) {
        String stuEmail = email.getText().toString();
        String stuIDST= ID.getText().toString();
        int stuID = Integer.parseInt(ID.getText().toString());
        String stuName = name.getText().toString();
        String stuPass = passwd.getText().toString();

        Student stud = new Student(stuEmail,stuID,stuName,stuPass);

        DatabaseReference insLoc = dbrf.child("simpsons/students/");
        insLoc.child(stuIDST).setValue(stud);
    }

    public void buttonClick3(View view) {
        int stuID = 123;
        stuID = Integer.parseInt(ID.getText().toString());
        DatabaseReference passID = dbrf.child("simpsons/students/"+ stuID);

        passID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {

                Student stud = data.getValue(Student.class);
                String name = stud.name;
                myTxt.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //log error
            }
        });

    }


}

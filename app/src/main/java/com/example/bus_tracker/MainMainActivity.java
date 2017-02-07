package com.example.bus_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMainActivity extends AppCompatActivity {

    Button User;
    Button Conductor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmain);


        // to =(EditText)findViewById(R.id.to);

        // destination =  to.getText().toString();

        // bus.add(source);
        // bus.add(destination);
        User = (Button)findViewById(R.id.user);

        User.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {


                    Intent intent = new Intent(MainMainActivity.this, MainActivity.class);
                    startActivity(intent);


            }
        });

        Conductor = (Button)findViewById(R.id.conductor);

        Conductor.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {


                Intent intent = new Intent(MainMainActivity.this, Main2Activity.class);
                startActivity(intent);


            }
        });

    }


}


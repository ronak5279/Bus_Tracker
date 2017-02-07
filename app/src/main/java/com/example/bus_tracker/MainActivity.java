package com.example.bus_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText busNum ;
    EditText to;
    String bus;
    Button ok;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        busNum = (EditText)findViewById(R.id.from);
        ok = (Button)findViewById(R.id.ok_button);

        ok.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                bus = busNum.getText().toString();

                if(!bus.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("bus", bus);
                    startActivity(intent);
                }

            }
        });

       // to =(EditText)findViewById(R.id.to);

       // destination =  to.getText().toString();

       // bus.add(source);
       // bus.add(destination);




    }

}

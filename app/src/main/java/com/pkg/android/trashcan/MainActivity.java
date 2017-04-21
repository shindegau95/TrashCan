package com.pkg.android.trashcan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by GAURAV on 27-03-2017.
 */

public class MainActivity extends AppCompatActivity {


    ArrayList<BinInfo> binList;


    private Button Click;
    private Button mapButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TrashCan app = (TrashCan)getApplicationContext();
        app.makeList();
        binList = app.getBinList();


        Click = (Button)findViewById(R.id.button);
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for(BinInfo b: binList){
                    Toast.makeText(getApplicationContext(), b.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mapButton = (Button)findViewById(R.id.mapbutton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

    }


}

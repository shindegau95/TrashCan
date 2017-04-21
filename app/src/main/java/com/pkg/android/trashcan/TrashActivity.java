package com.pkg.android.trashcan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by GAURAV on 04-04-2017.
 */

public class TrashActivity extends AppCompatActivity {
    private static final String BIN_ID_EXTRA = "bin_id";
    private Button leveldisp[];
    private TextView BinIdText;
    private TextView BinLevelText;
    private TextView BinAddressText;
    private TextView BinDistance;

    public static Intent newIntent(Context packageContext, String bin_id) {
        Intent i = new Intent(packageContext, TrashActivity.class);
        i.putExtra(BIN_ID_EXTRA, bin_id);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        leveldisp = new Button[10];
        leveldisp[0] = (Button) findViewById(R.id.leveldisp1);
        leveldisp[1] = (Button) findViewById(R.id.leveldisp2);
        leveldisp[2] = (Button) findViewById(R.id.leveldisp3);
        leveldisp[3] = (Button) findViewById(R.id.leveldisp4);
        leveldisp[4] = (Button) findViewById(R.id.leveldisp5);
        leveldisp[5] = (Button) findViewById(R.id.leveldisp6);
        leveldisp[6] = (Button) findViewById(R.id.leveldisp7);
        leveldisp[7] = (Button) findViewById(R.id.leveldisp8);
        leveldisp[8] = (Button) findViewById(R.id.leveldisp9);
        leveldisp[9] = (Button) findViewById(R.id.leveldisp10);

        BinIdText = (TextView) findViewById(R.id.bin_id);
        BinLevelText = (TextView) findViewById(R.id.bin_level);
        BinAddressText = (TextView) findViewById(R.id.address);
        BinDistance = (TextView) findViewById(R.id.distance);



        TrashCan trashCan = (TrashCan) getApplicationContext();
        BinInfo bin = trashCan.getBin(getIntent().getStringExtra(BIN_ID_EXTRA));


        double total_height = 100;
        double height = total_height-Double.parseDouble(bin.getLevel());

        BinIdText.setText(bin.getBin_id());
        BinLevelText.setText(bin.getLevel());
        BinAddressText.setText(bin.getLocation());

        int color = 0;
        if (height < total_height && height > 6 * total_height / 10) {
            color = getResources().getColor(R.color.red);
        } else if (height <= 6 * total_height / 10 && height > 3 * total_height / 10) {
            color = getResources().getColor(R.color.yellow);
        } else if (height <= 3 * total_height / 10 && height > 0) {
            color = getResources().getColor(R.color.green);
        }



        if (height < (total_height / 10) && height>0){
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (2 * total_height / 10) && height >= (total_height / 10)){
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (3 * total_height / 10) && height >= (2*total_height / 10)){
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (4 * total_height / 10) && height >= (3*total_height / 10)){
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (5 * total_height / 10) && height >= (4*total_height / 10)){
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }

        if (height < (6 * total_height / 10) && height >= (5*total_height / 10)){
            leveldisp[4].setBackgroundColor(color);
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (7 * total_height / 10) && height >=(6*total_height / 10)){
            leveldisp[3].setBackgroundColor(color);
            leveldisp[4].setBackgroundColor(color);
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (8 * total_height / 10) && height >= (7*total_height / 10)){
            leveldisp[2].setBackgroundColor(color);
            leveldisp[3].setBackgroundColor(color);
            leveldisp[4].setBackgroundColor(color);
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (9 * total_height / 10) && height >= (8*total_height / 10)){
            leveldisp[1].setBackgroundColor(color);
            leveldisp[2].setBackgroundColor(color);
            leveldisp[3].setBackgroundColor(color);
            leveldisp[4].setBackgroundColor(color);
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }
        if (height < (total_height) && height >= (9*total_height / 10)){
            leveldisp[0].setBackgroundColor(color);
            leveldisp[1].setBackgroundColor(color);
            leveldisp[2].setBackgroundColor(color);
            leveldisp[3].setBackgroundColor(color);
            leveldisp[4].setBackgroundColor(color);
            leveldisp[5].setBackgroundColor(color);
            leveldisp[6].setBackgroundColor(color);
            leveldisp[7].setBackgroundColor(color);
            leveldisp[8].setBackgroundColor(color);
            leveldisp[9].setBackgroundColor(color);
        }


    }
}

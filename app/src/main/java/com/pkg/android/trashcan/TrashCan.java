package com.pkg.android.trashcan;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by GAURAV on 29-03-2017.
 */

public class TrashCan extends Application {

    private ArrayList<BinInfo> binList;

    public void  makeList(){
        //write this 2 lines to get bininfo
        BinInfoLab binInfoLab = BinInfoLab.get(getApplicationContext());
        setBinList(binInfoLab.getBinList());
    }
    public ArrayList<BinInfo> getBinList() {
        return binList;
    }

    public void setBinList(ArrayList<BinInfo> binList) {
        this.binList = binList;
    }


    public BinInfo getBin(String bin_id)
    {
        TrashCan trashCan = (TrashCan)getApplicationContext();
        ArrayList<BinInfo> binList = trashCan.getBinList();

        BinInfo bin = null;
        for(BinInfo b: binList){
            if(b.getBin_id().equals(bin_id))
                bin = b;
        }
        return bin;
    }
}

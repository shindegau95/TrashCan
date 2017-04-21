package com.pkg.android.trashcan;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;

/**
 * Created by GAURAV on 29-03-2017.
 */

public class GetLocationPoint {
    public static LatLng getLocationFromAddress(String strAddress, Context context){

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng point = null;



        try {
            address = coder.getFromLocationName(strAddress,1);
            while (address.size()==0) {
                address = coder.getFromLocationName(strAddress, 1);
            }
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();



            point = new LatLng(location.getLatitude(),location.getLongitude());
        } catch (IOException e) {
            Toast.makeText(context, "Abe exception hai", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


            return point;

    }
}

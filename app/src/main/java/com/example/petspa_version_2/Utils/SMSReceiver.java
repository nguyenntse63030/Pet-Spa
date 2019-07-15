package com.example.petspa_version_2.Utils;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import androidx.core.app.ActivityCompat;

public class SMSReceiver extends BroadcastReceiver  {

    private String senderTel;
    private LocationManager manager;
    private LocationListener listener;
    private String provider;

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(senderTel, null, "http://maps.google.com/maps?q=" +
                        location.getLatitude() + "," +
                        location.getLongitude(), null, null
                );
                manager.removeUpdates(listener);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";

        if (bundle != null) {
            senderTel = "";
            Object[] pdus = ((Object[]) bundle.get("pdus"));
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (i == 0) {
                    senderTel = msgs[i].getOriginatingAddress();
                }
                str += msgs[i].getMessageBody().toString();
            }

            if (str.startsWith("Where are you?")) {
                manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                listener = new MyLocationListener();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        6000, 1000, listener);
                Criteria criteria = new Criteria();
                provider = manager.getBestProvider(criteria, false);
                Location location = manager.getLastKnownLocation(provider);
                if (location != null) {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(senderTel, null, "http://maps.google.com/maps?q=" +
                            location.getLatitude() + "," +
                            location.getLongitude(), null, null
                    );
                    manager.removeUpdates(listener);
                }
                this.abortBroadcast();
            }
        }
    }


}

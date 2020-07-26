package com.ypasoft.contactracing.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ypasoft.contactracing.R;

public class ShowDialogMessage {

    public Context context;
    public ShowDialogMessage(Context context) {
        this.context = context;
    }

    public ShowDialogMessage() {
        super();
    }

    public void showOpenSettings() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View alertView = inflater.inflate(R.layout.show_dialog_message, null);
        ((TextView) alertView.findViewById(R.id.sdm_title)).setText(context.getString(R.string.show_title_settings));
        ((TextView) alertView.findViewById(R.id.sdm_messagge)).setText(context.getString(R.string.show_message_settings));
        Button alertButton = (Button) alertView.findViewById(R.id.btn_test);
        alertButton.setText(context.getString(R.string.show_button_settings));
        alertDialog.setView(alertView);

        alertDialog.show();
        final String appPackageName = context.getPackageName();
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + appPackageName));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivityForResult(intent, 168);

            }
        });
    }

    public void showOpenSettingsGPS() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View alertView = inflater.inflate(R.layout.show_dialog_message, null);
        ((TextView) alertView.findViewById(R.id.sdm_title)).setText(context.getString(R.string.show_title_settings));
        ((TextView) alertView.findViewById(R.id.sdm_messagge)).setText(context.getString(R.string.show_message_settings_GPS));
        Button alertButton = (Button) alertView.findViewById(R.id.btn_test);
        alertButton.setText(context.getString(R.string.show_button_settings));
        alertDialog.setView(alertView);

        alertDialog.show();
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
    }

}

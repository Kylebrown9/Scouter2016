package org.ncfrcteams.frcscoutinghub2016.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.ncfrcteams.frcscoutinghub2016.R;

/**
 * Created by pavan on 3/31/16.
 */
public class HubCreateDialog implements View.OnClickListener {

    private static final String POSITIVE_TEXT = "Create";
    private static final String NEGATIVE_TEXT = "Cancel";
    private int[] teams;
    private int matchnum;
    private boolean isQual;
    private EditText phonenum;
    private Dialog dialog;
    private View view;

    public HubCreateDialog(Context context, HubCreateDialogListener listener) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final HubCreateDialog thisDialog = this;

        view = LayoutInflater.from(context).inflate(R.layout.s_post_dialog, null);
        alert.setView(view);

        view.findViewById(R.id.).setOnClickListener(thisDialog);
        thisDialog.phonenum = (EditText) view.findViewById(R.id.);

        final HubCreateDialogListener dialogListener = listener;

        DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialogListener.onNewMatchCreate(thisDialog.teams, 25, true, thisDialog.phonenum.getText().toString());
            }
        };

        DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        };

        alert.setPositiveButton(POSITIVE_TEXT, positiveListener);
        alert.setNegativeButton(NEGATIVE_TEXT, negativeListener);

        dialog = alert.create();
    }


    public void show() {
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch(view.getId()){
            case R.id.challengeNo:
                teams[0] = 0;
                break;
            default:
                break;
        }
    }

    public interface HubCreateDialogListener{
        void onNewMatchCreate(int[] teams, int matchnum, boolean isQual, String phonenum);
    }

}

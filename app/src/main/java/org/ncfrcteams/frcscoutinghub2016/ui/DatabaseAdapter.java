package org.ncfrcteams.frcscoutinghub2016.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ncfrcteams.frcscoutinghub2016.R;
import org.ncfrcteams.frcscoutinghub2016.matchdata.Match;
import org.ncfrcteams.frcscoutinghub2016.matchdata.database.MatchRecord;
import org.ncfrcteams.frcscoutinghub2016.matchdata.schedule.MatchDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavan on 3/30/16.
 */
public class DatabaseAdapter extends ArrayAdapter<Match> {

    private Context context;
    private DatabaseListener listener;
    public List<Match> matches;

    public DatabaseAdapter(Context context, DatabaseListener listener, Match[] matches) {
        super(context, R.layout.h_listview_contents, matches);
        this.context = context;
        this.listener = listener;
        this.matches = new ArrayList<>();
    }

    public DatabaseAdapter(Context context, DatabaseListener listener) {
        super(context, R.layout.h_listview_contents);
        this.context = context;
        this.listener = listener;
        this.matches = new ArrayList<>();
    }

    /*
    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Match getItem(int position) {
        return matches.get(position);
    }
    */

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.h_listview_contents, parent, false);

        Match match = getItem(position);
        TextView listViewText = (TextView) view.findViewById(R.id.listViewText);
        listViewText.setText(match.getText());
        listViewText.setTextColor(match.getColor());

        return view;
    }

    public void setMatchDescriptor(int position, MatchDescriptor matchDescriptor){
        //TODO update matches
        update();
    }

    public void setMatchRecord(int position, MatchRecord matchRecord){
        //TODO update matches
        update();
    }

    private void update(){
        //TODO set up the array adapter with matches
        listener.onListChange();
    }

    public interface DatabaseListener{
        void onListChange();
    }

}

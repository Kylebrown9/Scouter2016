package org.ncfrcteams.frcscoutinghub2016.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ncfrcteams.frcscoutinghub2016.R;
import org.ncfrcteams.frcscoutinghub2016.matchdata.schedule.Match;
import org.ncfrcteams.frcscoutinghub2016.matchdata.schedule.Schedule;

import java.util.List;

/**
 * Created by pavan on 3/30/16.
 */
public class DatabaseAdapter extends ArrayAdapter<Match> implements Schedule.ScheduleChangeListener{

    private Context context;
    private DatabaseListener listener;

    public DatabaseAdapter(Context context, DatabaseListener listener, Match[] matches) {
        super(context, R.layout.h_listview_contents, matches);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.listener.onListChange();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.h_listview_contents, parent, false);
        }

        Match match = getItem(position);
        TextView listViewText = (TextView) view.findViewById(R.id.listViewText);
        listViewText.setText(match.getText());
        listViewText.setTextColor(match.getColor());

        return view;
    }

    @Override
    public void notifyScheduleChanges(List<Match> matches) {
        this.clear();
        this.addAll(matches);
    }

    public interface DatabaseListener{
        void onListChange();
    }

}

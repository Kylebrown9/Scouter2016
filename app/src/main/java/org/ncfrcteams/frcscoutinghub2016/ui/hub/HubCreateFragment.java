package org.ncfrcteams.frcscoutinghub2016.ui.hub;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ncfrcteams.frcscoutinghub2016.R;

public class HubCreateFragment extends Fragment implements View.OnClickListener{

    private HubCreateFragListener mListener;
    public HubCreateFragment() {
    }

    public static HubCreateFragment newInstance() {
        return new HubCreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_frag_create, container, false);

        view.findViewById(R.id.createClickMe).setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HubCreateFragListener) {
            mListener = (HubCreateFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement HubCreateFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.createClickMe:
                int[] teams = {1991,22,45553,5134,643,833};
                mListener.addNewMatch(teams, 3);
                break;
            case R.id.rightLowGoal:
                break;
            case R.id.rightSuccess:
                break;
            case R.id.rightFailure:
                break;
            default:
                break;
        }
    }

    public interface HubCreateFragListener {
        void addNewMatch(int[] teams, int matchnum);
    }
}

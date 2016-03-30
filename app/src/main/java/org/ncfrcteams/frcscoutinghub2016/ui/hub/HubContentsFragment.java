package org.ncfrcteams.frcscoutinghub2016.ui.hub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ncfrcteams.frcscoutinghub2016.R;

public class HubContentsFragment extends Fragment {
    private static final String ARG_PARAM1 = "title";
    private String title;
    private TextView hubfrag1;
    private OnHubContentsFragListener mListener;

    public HubContentsFragment() {
    }

    public static HubContentsFragment newInstance(String title) {
        HubContentsFragment fragment = new HubContentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_frag_contents, container, false);
        hubfrag1 = (TextView) view.findViewById(R.id.hubfrag1);
        hubfrag1.setText(title);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHubContentsFragListener) {
            mListener = (OnHubContentsFragListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHubContentsFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnHubContentsFragListener {
        void onHubContentFragInteraction(Uri uri);
    }
}

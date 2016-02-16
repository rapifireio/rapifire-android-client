package com.rapifire.rapifireclient.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;

/**
 * Created by witek on 15.02.16.
 */
public class ThingCommandsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thing_commands_fragment, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #");
        return view;
    }
}

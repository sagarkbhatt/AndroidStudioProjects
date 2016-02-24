package com.example.sagarbhatt.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Sagar Bhatt on 22-02-2016.
 */
public class MenuFragment extends ListFragment {

    String[] playerName= new String[]{"Sachin","Dhoni","Virat","Zaheer"};
    String [] playerRun= new String[]{"100","80","40","20"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.list_fragment,container,false);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,playerName);
        setListAdapter(adapter);

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextFragment txt=(TextFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change(playerName[position],"Runs:"+playerRun[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}

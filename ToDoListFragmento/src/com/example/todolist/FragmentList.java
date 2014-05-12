package com.example.todolist;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentList extends ListFragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
	  // Create, or inflate the Fragment's UI, and return it.
	  // If this Fragment has no UI then return null.
	  return inflater.inflate(R.layout.fragment_list, container, false);
	}
}

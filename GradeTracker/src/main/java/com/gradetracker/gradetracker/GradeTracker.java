package com.gradetracker.gradetracker;

// import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ExpandableListView;

public class GradeTracker extends ActionBarActivity {
    // More efficient than HashMap for mapping integers to objects
    SparseArray<ExpandableListGroup> groups = new SparseArray<ExpandableListGroup>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradetracker);
        Manager theManager = new Manager();
        createData(theManager);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }

    public void createData(Manager theManager) {
        for (int j = 0; j < theManager.getCourseListSize(); j++) {
            ExpandableListGroup group = new ExpandableListGroup("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.grade_tracker, menu);
        //getMenuInflater().inflate(R.menu.grade_tracker, menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }
}

package com.gradetracker.gradetracker;

// import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;

public class GradeTracker extends Activity {
    // More efficient than HashMap for mapping integers to objects
    SparseArray<ExpandableListGroup> groups = new SparseArray<ExpandableListGroup>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradetracker);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
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
        getMenuInflater().inflate(R.menu.grade_tracker, menu);
        return true;
    }
    
}

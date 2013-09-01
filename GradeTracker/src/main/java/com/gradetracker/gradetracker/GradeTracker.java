package com.gradetracker.gradetracker;

// import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

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
        if (theManager.getCourseListSize() == 0) {
            //Context contextToast = getApplicationContext();
            //CharSequence text = "No courses have been added.";
            //int duration = Toast.LENGTH_LONG;
            //Toast toast = Toast.makeText(contextToast, text, duration);
            Toast.makeText(this, "No courses have been added.", Toast.LENGTH_LONG).show();
            //toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.show();
        }
        for (int j = 0; j < theManager.getCourseListSize(); j++) {
            ExpandableListGroup group = new ExpandableListGroup(theManager.getCourseDetails(j));
            for (int i = 0; i < 5; i++) {
                String s = "";
                Grade grade = new Grade("New Grade", 100.0);
                theManager.getCourse(j).addItem(grade);
                s += theManager.getCourse(j).getGradeInfo(i);
                group.children.add(s);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new_course:
                //Course newCourse = new Course("New course", 4);
                //theManager.addCourse(newCourse);
                addCourse("New Course");
                Toast.makeText(this, "Action refresh selected.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Action settings selected.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addCourse(String name) {
        Course newCourse = new Course(name, 4);
        Manager theManager = new Manager();
        theManager.addCourse(newCourse);
        updateView(theManager);
    }

    public void openSettings() {

    }
    public void updateView(Manager manager) {
        createData(manager);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }
}

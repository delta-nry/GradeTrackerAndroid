package com.gradetracker.gradetracker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class GradeTracker extends ActionBarActivity {
    // More efficient than HashMap for mapping integers to objects
    private SparseArray<ExpandableListGroup> groups = new SparseArray<ExpandableListGroup>();
    private Manager theManager = new Manager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradetracker);
        createData(theManager);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }

    public void createData(Manager theManager) {
        if (theManager.getCourseListSize() == 0) {
            Toast.makeText(this, "No courses have been added.", Toast.LENGTH_LONG).show();
        }
        // Delete 'groups' SparseArray entries that have an index larger than theManager.getCourseListSize()
        // (these entries have already been removed by another method)
        for (int j = theManager.getCourseListSize(); j < groups.size(); j++) {
            // May change to groups.removeAt(j), but requires API 11
            groups.remove(j);
        }

        for (int j = 0; j < theManager.getCourseListSize(); j++) {
            ExpandableListGroup group = new ExpandableListGroup(theManager.getCourseDetails(j));
            for (int i = 0; i < 5; i++) {
                // Create new grades
                Grade grade = new Grade("New Grade", 100.0);
                theManager.getCourse(j).addItem(grade);
                String s = theManager.getCourse(j).getGradeInfo(i);
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
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_remove_course:
                removeCourse(theManager);
                return true;
            case R.id.action_new_course:
                addCourse("New Course", theManager);
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addCourse(String name, Manager theManager) {
        Course newCourse = new Course(name, 4);
        theManager.addCourse(newCourse);
        updateView(theManager);
    }

    public void openSettings() {
        Toast.makeText(this, "Action settings selected.", Toast.LENGTH_SHORT).show();
    }

    public void removeCourse(Manager theManager) {
        theManager.deleteCourse(theManager.getCourseListSize() - 1);
        updateView(theManager);
    }

    public void updateView(Manager manager) {
        createData(manager);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
        listView.setAdapter(adapter);
    }
}

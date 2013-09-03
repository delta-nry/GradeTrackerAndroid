package com.gradetracker.gradetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
            // May change to groups.removeAt(j), but requires Android API 11
            groups.remove(j);
        }

        for (int j = 0; j < theManager.getCourseListSize(); j++) {
            ExpandableListGroup group = new ExpandableListGroup(theManager.getCourseDetails(j));
            // Say that no grades are in a course containing no grades
            if (theManager.getCourse(j).getMarksListSize() == 0) {
                Toast.makeText(this, "No grades are in this course.", Toast.LENGTH_LONG).show();
            }
            for (int i = 0; i < theManager.getCourse(j).getMarksListSize(); i++) {
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
                DialogFragment newFragment = new AddCourseDialogFragment();
                newFragment.show(getSupportFragmentManager(), "add_course");
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class AddCourseDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            builder.setTitle(R.string.action_new_course);
            final View mView = inflater.inflate(R.layout.dialog_addcourse, null);

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(mView)
                    // Add action buttons
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        //@Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Check for NullPointerExceptions
                            EditText mText1;
                            try {
                                mText1 = (EditText)mView.findViewById(R.id.course_name);
                            } catch (NullPointerException e) {
                                return;
                            }
                            EditText mText2 = (EditText)mView.findViewById(R.id.course_credits);
                            String courseName;
                            try {
                                courseName = mText1.getText().toString();
                            } catch (NullPointerException e) {
                                return;
                            }
                            String courseCreditsString;
                            try {
                                courseCreditsString = mText2.getText().toString();
                            } catch (NullPointerException e) {
                                return;
                            }
                            int courseCreditsInt;
                            try {
                                courseCreditsInt = Integer.valueOf(courseCreditsString);
                            } catch (NumberFormatException e) {
                                courseCreditsInt = -1;
                            }
                            // Check for empty/invalid values
                            if (courseName != null && !courseName.isEmpty()) {
                                if (courseCreditsInt >= 0) {
                                    addCourse(courseName, courseCreditsInt, theManager);
                                }
                                else {
                                    addCourse(courseName, 0, theManager);
                                }
                            }
                            else if (courseCreditsInt >= 0) {
                                if (courseName != null && !courseName.isEmpty()) {
                                    addCourse(courseName, courseCreditsInt, theManager);
                                }
                                else {
                                    addCourse("New Course", courseCreditsInt, theManager);
                                }
                            }
                            else {
                                addCourse("New Course", 0, theManager);
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Don't create anything
                        }
                    });
            return builder.create();
        }
    }

    public void addCourse(String name, int credits, Manager theManager) {
        Course newCourse = new Course(name, credits);
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

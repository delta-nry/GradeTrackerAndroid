package com.gradetracker.gradetracker;

/* This class contains an ArrayList which contains the users classes.
* There should only be one Manager instance in Menu.java, as
* this Manager class holds the ArrayList of all the courses the user
* needs, and each course contains all the grades the user needs.
*/

import java.util.List;
import java.util.ArrayList;

public class Manager {

    private List<Course> courseList;
    private String name;
    private double GPA;
    // Constructor that creates an array which contains courses
    public Manager() {
        this.courseList = new ArrayList<Course>();
        this.name = "Course Manager";
        this.GPA = 0.0;
    }
    // Returns the assigned description
    public String getName() {
        return name;
    }
    // Gets a course based on a passed in value
    public Course getCourse(int courseIndexNumber) {
        if (courseIndexNumber < courseList.size()) {
            return courseList.get(courseIndexNumber);
        }
        else {
            return null;
        }
    }
    /* Displays names of courses corresponding to their index values
	 * in courseList. Each name is shown as "[i]. [courseName]", with
	 * each course separated by newlines.
	 */
    public String getCourseNames() {
        // Maybe use an iterator to replace i in the future
        String s = "";
        for (int i = 0; i < courseList.size(); i++) {
            s += "\n";
            s += i + ". " + courseList.get(i);
            // Do not add an extra newline to the last printed
            // courseList element
            if (i != courseList.size() - 1) {
                s += "\n";
            }
        }
        return s;
    }
    // Gets the size of the courseList
    public int getCourseListSize() {
        return courseList.size();
    }
    // Adds the passed in Course to the end of courseList
    public void addItem(Course newItem) {
        courseList.add(newItem);
    }
    // Deletes course in the last index number of courseList.
    public void deleteCourse(int toDel) {
        // Don't delete a nonexistent Course in courseList
        if (toDel >= 0 && toDel < courseList.size()) {
            courseList.remove(toDel);
            System.out.println("\n" + "Deleted the course.");
        }
        else {
            System.out.println("\n" + "Invalid number; try again.");
        }
    }
    /*  Displays names of a course corresponding to its index value
     *  in courseList.
     */
    public String getCourseDetails(int i) {
        String s = courseList.get(i).toString();
        return s;
    }
    /*
	 *	Calculates GPA based on overall mark for each course inserted
	 *	into the courseList
	 */
    public void calculateGPA() {
        int overallCredits = 0;
        double courseContributions = 0.0;
        for (Course c: courseList) {
            courseContributions += this.getCourseContribution(c);
            overallCredits += c.getCreditContribution();
        }
        this.GPA = courseContributions/overallCredits;
    }
    /* Returns the grade points for the passed in course.
	 * Calculations are done based on the following link:
	 * http://studentsuccess.mcmaster.ca/tools/gpa-conversion-chart.html
	 * The grade points for each course are multiplied by their credit
	 * contribution and are then returned.
	 * Results may differ from your school's calculation. Defer to
	 * your school's calculation.
	 */
    public double getCourseContribution(Course c) {
        double mark = c.getOverallMark();
        double temp = 0.0;
        if (mark < 50.0) {
            temp = 0.0;
        } else if (mark < 52.0) {
            temp = 0.70;
        } else if (mark < 55.0) {
            temp = 1.00;
        } else if (mark < 59.0) {
            temp = 1.30;
        } else if (mark < 61.0) {
            temp = 1.70;
        } else if (mark < 63.0) {
            temp = 2.00;
        } else if (mark < 65.0) {
            temp = 2.30;
        } else if (mark < 68.0) {
            temp = 2.70;
        } else if (mark < 71.0) {
            temp = 3.00;
        } else if (mark < 74.0) {
            temp = 3.30;
        } else if (mark < 83.0) {
            temp = 3.70;
        } else if (mark < 92.0) {
            temp = 3.90;
        } else {
            temp = 4.00;
        }
        return temp*c.getCreditContribution();
    }
    // returns the GPA accumulated as of far
    public double getGPA() {
        this.calculateGPA();
        return GPA;
    }
}

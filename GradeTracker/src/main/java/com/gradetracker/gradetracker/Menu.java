package com.gradetracker.gradetracker;

/*  This class consists of several text-based menus which allow users
 *  to manage their courses and grades. There should only be one Menu
 *  instance in "public static void main(String[] args)" as it holds
 *  a sole Manager instance which contains all the courses and grades
 *  the user needs.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {

    private Manager theManager;
    // Constructor creating one Manager instance with a size integer
    // value passed in to create a new course
    public Menu() {
        this.theManager = new Manager();
    }
    // Adds the passed in Course to the list in the Manager object
    public void addCourse(Course newCourse) {
        theManager.addCourse(newCourse);
    }
    // Accesses the user's courses
    public void managerMenu() {
        Scanner userInput = new Scanner(System.in);
        boolean userHasQuit = false;
        // Keep looping until user quits the menu
        while(!userHasQuit) {
            // This could be unsafe
            // Keep looping until valid input is received
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("\n" + "----------");
                    System.out.println("\n" + theManager.getName());
                    System.out.println("\n" + "Enter 1 to get the list of registered course names,");
                    System.out.println("Enter 2 to register a new course,");
                    System.out.println("Enter 3 to delete a registered course,");
                    System.out.println("Enter 4 to manage a registered course,");
                    System.out.println("Enter 9 to quit:");
                    System.out.print(" > ");
                    int choice = userInput.nextInt();
                    validInput = true;
                    switch (choice) {
                    case 1:
                        try {
                            // Check if no courses are registered
                            if (theManager.getCourseNames().isEmpty()) {
                                System.out.println("\n" + "No courses are registered; try adding a new one.");
                                break;
                            }
                            System.out.println(theManager.getCourseNames());
                        } catch (NullPointerException e) {
                            System.out.println("\n" + "No courses are availible; try adding a new one.");
                        }
                        break;
                    case 2:
                        // Flushes garbage input to prevent unintended actions
                        userInput.nextLine();
                        // Default Course name, size and credits are already listed
                        String inputName = "newCourse";
                        int inputCredits = 4;
                        boolean validName = false;
                        boolean validCredits = false;
                        while (!validName) {
                            try {
                                System.out.println("\n" + "Enter a name of the new course you want to add:");
                                System.out.print(" > ");
                                // Check if the name is empty
                                inputName = userInput.nextLine();
                                if (!inputName.isEmpty()) {
                                    validName = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid course name; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        while (!validCredits) {
                            try {
                                System.out.println("\n" + "Enter the number of credits this course is worth:");
                                System.out.print(" > ");
                                inputCredits = userInput.nextInt();
                                validCredits = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid number of credits; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        Course newCourse = new Course(inputName, inputCredits);
                        theManager.addCourse(newCourse);
                        break;
                    case 3:
                        try {
                            int inputInt = 0;
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                            // Check if no courses are registered
                            if (theManager.getCourseNames().isEmpty()) {
                                System.out.println("\n" + "No courses are registered; try adding a new one.");
                                break;
                            }
                            System.out.println("Enter the number of the registered course you want to delete:");
                            System.out.println(theManager.getCourseNames());
                            System.out.print(" > ");
                            inputInt = userInput.nextInt();
                            theManager.deleteCourse(inputInt);
                        } catch (InputMismatchException e) {
                            System.out.println("\n" + "Invalid number; try again.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } catch (NullPointerException e) {
                            System.out.println("\n" + "No courses are registered; try adding a new one.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } 
                        break;
                    case 4:
                        int inputInt;
                        try {
                            if (theManager.getCourseListSize() == 0) {
                                System.out.println("\n" + "No courses are registered; try adding a new one.");
                                break;
                            }
                            System.out.println("Enter the number of the registered course you want to manage:");
                            System.out.println(theManager.getCourseNames());
                            System.out.print(" > ");
                            inputInt = userInput.nextInt();
                            courseMenu(theManager.getCourse(inputInt));
                        } catch (InputMismatchException e) {
                            System.out.println("\n" + "Invalid number; try again.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("\n" + "Invalid number; try again.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        }
                        break;
                    case 9:
                        userHasQuit = true;
                        break;
                    default:
                        System.out.println("\n" + "Couldn't understand request; try again.");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n" + "Couldn't understand request; try again.");
                    // Flushes garbage input to prevent infinite looping
                    userInput.nextLine();
                }
            }
        }
    }
    // Accesses a selected course's statistics
    public void courseMenu(Course selectedCourse) {
        Scanner userInput = new Scanner(System.in);
        boolean userHasQuit = false;
        // Keep looping until user quits the menu
        while(!userHasQuit) {
            // This could be unsafe
            // Keep looping until valid input is received
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("\n" + "----------");
                    System.out.println("\n" + "Menu for course: " + selectedCourse.getName());
                    System.out.println("\n" + "Enter 1 to get a string with this course's details,");
                    System.out.println("Enter 2 to add a new grade to this course,");
                    System.out.println("Enter 3 to get the list of registered grade names,");
                    System.out.println("Enter 4 to get the details of a registered grade,");
                    System.out.println("Enter 5 to delete a registered grade,");
                    System.out.println("Enter 6 to set the mark of a registered grade,");
                    System.out.println("Enter 9 to go back to the course manager:");
                    System.out.print(" > ");
                    int choice = userInput.nextInt();
                    validInput = true;
                    switch (choice) {
                    case 1:
                        System.out.println(selectedCourse.toString());
                        break;
                    case 2:
                        // Flushes garbage input to prevent unintended actions
                        userInput.nextLine();
                        // Default Grade name and worth are listed
                        String inputName = "newGrade";
                        double inputWorth = 10;
                        boolean validName = false;
                        boolean validWorth = false;
                        while (!validName) {
                            try {
                                System.out.println("\n" + "Enter a name of the new grade you want to add:");
                                System.out.print(" > ");
                                // Check if the name is empty
                                inputName = userInput.nextLine();
                                if (!inputName.isEmpty()) {
                                    validName = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid grade name; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        while (!validWorth) {
                            try {
                                System.out.println("\n" + "Enter the number of marks this grade is worth to this course:");
                                System.out.print(" > ");
                                inputWorth = userInput.nextDouble();
                                validWorth = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid number of marks; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        Grade newGrade = new Grade(inputName, inputWorth);
                        selectedCourse.addItem(newGrade);
                        break;
                    case 3:
                        try {
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                            // Check if no grades are registered in the course
                            if (selectedCourse.getGradeNames().isEmpty()) {
                                System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                                break;
                            }
                            System.out.println(selectedCourse.getGradeNames());
                        } catch (NullPointerException e) {
                            System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                        }
                        break;
                    case 4:
                        try {
                            int inputInt = 0;
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                            // Check if no grades are registered in the course
                            if (selectedCourse.getGradeNames().isEmpty()) {
                                System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                                break;
                            }
                            System.out.println("Enter the number of the registered grade you want details about:");
                            System.out.println(selectedCourse.getGradeNames());
                            System.out.print(" > ");
                            inputInt = userInput.nextInt();
                            System.out.print(selectedCourse.getGradeInfo(inputInt));
                        } catch (InputMismatchException e) {
                            System.out.println("\n" + "Invalid number; try again.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } catch (NullPointerException e) {
                            System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        }
                        break;
                    case 5:
                        try {
                            int inputInt = 0;
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                            // Check if no grades are registered in the course
                            if (selectedCourse.getGradeNames().isEmpty()) {
                                System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                                break;
                            }
                            System.out.println("Enter the number of the registered grade you want to delete:");
                            System.out.println(selectedCourse.getGradeNames());
                            System.out.print(" > ");
                            inputInt = userInput.nextInt();
                            selectedCourse.deleteItem(inputInt);
                        } catch (InputMismatchException e) {
                            System.out.println("\n" + "Invalid number; try again.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } catch (NullPointerException e) {
                            System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                            // Flushes garbage input to prevent unintended actions
                            userInput.nextLine();
                        } 
                        break;
                    case 6:
                        // Flushes garbage input to prevent unintended actions
                        userInput.nextLine();
                        // Default Grade name and worth are listed
                        int inputInt = -1;
                        int inputTotalScore = 10;
                        int inputScore = 10;
                        boolean validInt = false;
                        boolean validTotalScore = false;
                        boolean validScore = false;
                        // Check if no grades are registered in the course
                        if (selectedCourse.getGradeNames().isEmpty()) {
                            System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                            break;
                        }
                        while (!validInt) {
                            try {
                                System.out.println("Enter the number of the registered grade you want to modify:");
                                System.out.println(selectedCourse.getGradeNames());
                                System.out.print(" > ");
                                inputInt = userInput.nextInt();
                                validInt = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid number; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            } catch (NullPointerException e) {
                                System.out.println("\n" + "No grades are registered in this course; try adding a new one.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        while (!validTotalScore) {
                            try {
                                System.out.println("\n" + "Enter the maximum amount of marks this grade can be out of:");
                                System.out.print(" > ");
                                inputTotalScore = userInput.nextInt();
                                validTotalScore = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid number of marks; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                        }
                        while (!validScore) {
                            try {
                                System.out.println("\n" + "Enter the amount of marks out of " + inputTotalScore + " that you have earned in this grade:");
                                System.out.print(" > ");
                                inputScore = userInput.nextInt();
                                validScore = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\n" + "Invalid number of marks; try again.");
                                // Flushes garbage input to prevent unintended actions
                                userInput.nextLine();
                            }
                            selectedCourse.setGradeTotalScore(inputInt, inputTotalScore);
                            selectedCourse.setGradeMyScore(inputInt, inputScore);
                            //selectedCourse.setGradeCurrMark(selectedCourse.getMarksListSize() - 1);
                        }
                        break;
                    case 9:
                        userHasQuit = true;
                        break;
                    default:
                        System.out.println("\n" + "Couldn't understand request; try again.");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n" + "Couldn't understand request; try again.");
                    // Flushes garbage input to prevent infinite looping
                    userInput.nextLine();
                }
            }
        }
    }
}

package com.gradetracker.gradetracker;

public class Grade {

	private String name;
	private double currMark;
	private double worth;
	private int myScore;
	private int totalScore;
	/*
     * Constructor for the Grade, which only requires a description 
	 * and worth to course
	 */
	public Grade(String theName, double theWorth) {
		this.name = theName;
		this.worth = theWorth;
		this.currMark = 0.0;
		this.myScore = 0;
		this.totalScore = 0;
	}
	// Re-assigns a description for the Grade
	public void setName(String newName) {
		this.name = newName;
	}	
	// Returns the assigned description
	public String getName() {
		return name;
	}
	// Re-assigns the worth of this Grade towards the course mark
	public void setWorth(double newWorth) {
		this.worth = newWorth;
	}
	// Returns assigned worth towards course mark
	public double getWorth() {
		return worth;
	}
	// Assigns a percentage-based mark to the Grade
	public void setCurrMark(double theMark) {
		this.currMark = theMark;
	}
	// Returns current mark
	public double getCurrMark() {
		return currMark;
	}
	/* Calculates current mark, if your score and total score are 
	 * defined
	 */
	public void calculateCurrMark() {
		double temp = (double)myScore * (double)totalScore;
		this.currMark = temp;
	}
	// Assigns the score you got on the marked item
	public void setMyScore(int newScore) {
		if (totalScore != 0) {
			this.myScore = newScore;
			this.calculateCurrMark();
		} else {
			System.out.println("No total score defined. Define total score.");
		}
	}	
	// Returns the score you got on the marked item
	public int getMyScore() {
		return myScore;
	}
	// Assigns the maximum score you can achieve on this item
	public void setTotalScore(int maxScore) {
		this.totalScore = maxScore;
	}
	// Returns the total score possible on the assignment
	public int getTotalScore() {
		return totalScore;
	}
	// Concatenates any non-zero values into a String to be returned
	public String toString() {
		String s = name + "\n";
		if (myScore != 0) 
			s += "Your score on this item: " + myScore + "\n";
		if (totalScore != 0) 
			s += "Total possible score: " + totalScore + "\n";
		if (currMark != 0.0)
			s += "Current mark: " + currMark + "%\n";
		s += "Worth to final mark: " + worth;
		return s;
	}	
}
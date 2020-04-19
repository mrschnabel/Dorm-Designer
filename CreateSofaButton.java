//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 4000
// Files:           CreateBedButton.java
// Course:          CS 300 Spring Semester 2018
//
// Author:          Vincent Angellotti
// Email:           angellotti@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Matthew Schnabel
// Partner Email:   mrschnabel@wisc.edu
// Lecturer's Name: Alexi Brooks
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __X_ Write-up states that pair programming is allowed for this assignment.
//   __X_ We have both read and understand the course Pair Programming Policy.
//   __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This is the create sofa class that will put a new sofa on the screen whenever
 * the user presses the button.
 * 
 * @author Vinnie Angellotti
 *
 */

public class CreateSofaButton {
	private static final int WIDTH = 96;// constant width
	private static final int HEIGHT = 32;// constant height

	private PApplet processing;// how we load and print images
	private float[] position;// positions of where the button is located
	private String label;// what is going to be in the button

	/**
	 * This is the constructor that will take all of the parameters and sets the
	 * private variables above set to their default values
	 * 
	 * @param x
	 * @param y
	 * @param processing
	 */
	public CreateSofaButton(float x, float y, PApplet processing) {
		this.position = new float[2];
		position[0] = x;
		position[1] = y;
		this.processing = processing;
		this.label = "Create Sofa";
	}

	/**
	 * This method will print a rectangle on the screen that will be grey when the
	 * mouse is over it and a lighter grey when the mouse is not over it
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);// fills button with dark grey if mouse is over
			processing.rect(102, 8, 198, 40);
		} else {
			processing.fill(200);// fills button with lighter grey is mouse is not over
			processing.rect(102, 8, 198, 40);
		}
		processing.fill(0);
		processing.text(label, 150, 24);

	}

	/**
	 * This mouse down method will create a new Furniture object that is a sofa when
	 * the mouse is over the button
	 * 
	 * @return
	 */
	public Furniture mouseDown() {
		if (isMouseOver() == true) {
			return new Furniture("sofa", processing);// create new furniture that is a sofa
		} else {
			return null;
		}
	}

	/**
	 * This method will determine whether or not the mouse is over the
	 * CreateSofaButton
	 * 
	 * @return
	 */
	public boolean isMouseOver() {
		if (((processing.mouseX > position[0] - WIDTH / 2) && (processing.mouseX < position[0] + WIDTH / 2))
				&& ((processing.mouseY > position[1] - HEIGHT / 2) && (processing.mouseY < position[1] + HEIGHT / 2))) {
			return true;
		} else {
			return false;
		}
	}
}

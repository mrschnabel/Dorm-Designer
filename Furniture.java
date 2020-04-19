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
 * This is the Furniture class that deals with moving the furniture around and
 * rotating it. This class determines what picture to load in and then it will
 * keep track of when the user rotates the furniture and whether or not the
 * furniture should follow the mouse or be placed and not move.
 * 
 * @author Vinnie Angellotti
 *
 */
public class Furniture {

	private PApplet processing;// how we load and print out images
	private PImage image;// location of where the image is
	private float[] position;// x and y position of furniture
	private boolean isDragging;// if the bed is being dragged or not
	private int rotations;// how many rotations the bed has
	private String type = null;// the type of furniture it is

	// initializes the fields of a new bed object positioned in the center of the
	// display
	/**
	 * This is the constructor for the furniture class. It sets all of the private
	 * variables listed above to their starting values.
	 * 
	 * @param type
	 * @param processing
	 */
	public Furniture(String type, PApplet processing) {
		this.processing = processing;
		this.type = type;
		PImage bedImage = processing.loadImage("Images/" + type + ".png");
		this.image = bedImage;
		this.position = new float[2];
		position[0] = processing.width / 2;
		position[1] = processing.height / 2;
		this.isDragging = false;

	}

	/**
	 * This is another constructor that is overloaded. We call this constructor when
	 * we are calling the load button. Because of this we have extra parameters
	 * because in the other constructor we already knew things like default
	 * rotations and default position.
	 * 
	 * @param type
	 * @param processing
	 * @param xPos
	 * @param yPos
	 * @param rotations
	 */
	public Furniture(String type, PApplet processing, float xPos, float yPos, int rotations) {
		this.processing = processing;
		this.type = type;
		PImage bedImage = processing.loadImage("Images/" + type + ".png");
		this.image = bedImage;
		this.position = new float[2];
		this.position[0] = xPos;
		this.position[1] = yPos;
		this.rotations = rotations;
		this.isDragging = false;

	}

	/**
	 * Getter of the type of furniture
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * getter of the number of rotations
	 * 
	 * @return
	 */
	public int getRotations() {
		return rotations;
	}

	/**
	 * getter of the x position
	 * 
	 * @return
	 */
	public float getPosition0() {
		return position[0];
	}

	/**
	 * Getter of the y positions
	 * 
	 * @return
	 */
	public float getPosition1() {
		return position[1];
	}

	/**
	 * The update method will move the furniture around the room is the furniture is
	 * not null and the mouse is over the furniture and being pressed down
	 */
	public void update() {
		if (position != null && isDragging == true) {
			processing.image(image, processing.mouseX, processing.mouseY, rotations * PApplet.PI / 2);// applies
																										// rotations
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		} else if (isDragging == false && position != null) {// if the position is not null and we are not dragging
			processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);
		}

	}

	/**
	 * The mouse down method checks if the mouse is over the furniture and the mouse
	 * button is being pressed down
	 */
	public void mouseDown() {
		if (position != null) {
			if (isMouseOver() == true) {// if the mouse is over
				isDragging = true;
			} else {
				isDragging = false;
			}
		}

	}

	/**
	 * The mouseUp method is always called when the user lifts their finger up from
	 * the mouse. When this happens it tells the other methods that the user is no
	 * longer dragging anything
	 */
	public void mouseUp() {
		isDragging = false;

	}

	/**
	 * This is a helper method to determine whether or not the mouse is over any
	 * piece of furniture
	 * 
	 * @return
	 */
	public boolean isMouseOver() {
		if (rotations % 2 == 0) {
			if (((processing.mouseX > position[0] - image.width / 2)
					&& (processing.mouseX < position[0] + image.width / 2))
					&& ((processing.mouseY > position[1] - image.height / 2)
							&& (processing.mouseY < position[1] + image.height / 2))) {
				return true;
			} else {
				return false;
			}
		} else {
			if (((processing.mouseX > position[0] - image.height / 2)
					&& (processing.mouseX < position[0] + image.height / 2))
					&& ((processing.mouseY > position[1] - image.width / 2)
							&& (processing.mouseY < position[1] + image.width / 2))) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * The rotate method increments the number of rotations whenever it is called
	 */
	public void rotate() {

		rotations++;
	}

}

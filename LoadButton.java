
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains the code which impliments the load button. First this
 * class makes a label of height and width we give it. Then, it prints "load
 * button" in the middle of it. Whenever someone clicks on this button all of
 * the current furniture pieces are taken out and replaced with the furniture
 * locations inside the .ddd text file
 * 
 * @author Vinnie Angellotti
 *
 */
public class LoadButton {

	private static final int WIDTH = 96;// width of button
	private static final int HEIGHT = 32;// height of button

	private PApplet processing;
	private float[] position;
	private String label;// name on button
	private String loadedTypes;// name of furniture type
	private int loadedRotations;// how many rotations the furniture is
	private String fileName = "RoomData.ddd";// name of the file we are reading
	private String lines = null;// saves each line from text file in this
	private int numTimes = 0;// how many furniture are placed
	private int totalTimes = 0;// total number of lines we go through
	private String[] colonSeperation = new String[2];// after we separate by colon
	private String[] commaSeperation = new String[3];// after we separate by comma
	private float xPos;// create new furniture with this x position
	private float yPos;// create new furniture with this y position

	/**
	 * This is the constructor for load button. It takes processing that is passed
	 * in and puts it in a variable we can use. Also it takes the x and y coordinate
	 * of where we want the button to be placed
	 * 
	 * @param x
	 * @param y
	 * @param processing
	 */

	public LoadButton(float x, float y, PApplet processing) {
		this.position = new float[2];
		position[0] = x;
		position[1] = y;
		this.processing = processing;
		this.label = "Load Room";
	}

	/**
	 * This is the update method that will print out the button on the dorm designer
	 * background
	 * 
	 */
	public void update() {

		if (isMouseOver() == true) {
			processing.fill(100);// grey color
			processing.rect(702, 8, 798, 40);// where the button is located
		} else {
			processing.fill(200);// lighter grey color
			processing.rect(702, 8, 798, 40);
		}
		processing.fill(0);
		processing.text(label, 750, 24);// where the text is printed

	}

	/**
	 * If this method is called it means the user clicked on the load button. When
	 * this happens our program will go through the text file, search through the
	 * text and pull out the information of furniture type, coordinates, and how
	 * many rotations and then set the furniture equal to this.
	 * 
	 * @param furniture
	 */
	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver() == true) {
			Scanner input = null;
			for (int i = 0; i < furniture.length; i++) {
				furniture[i] = null;// sets every furniture to null
			}

			try {

				FileInputStream text = new FileInputStream(fileName);// getting ready to read input with fileInputStream

				input = new Scanner(text);

				while (totalTimes < 15) {
					if (input.hasNextLine()) {

						if (numTimes >= 6) {// checking to see if the user is trying to load more than 6 furniture
							System.out.println("WARNING: Unable to load more furniture.");
							break;
						}

						lines = input.nextLine().trim();
						if (lines.equals("")) {// if the line is blank skip it
							totalTimes++;
							continue;
						}
						if ((!lines.contains(":")) || (!lines.contains(","))) {// checking to see if the file is
																				// formatted good
							System.out
									.println("WARNING: Found incorrectly formatted line in file: " + (totalTimes + 1));
							totalTimes++;
							continue;
						} else if (lines.indexOf(":") > lines.indexOf(",")) {
							System.out
									.println("WARNING: Found incorrectly formatted line in file: " + (totalTimes + 1));
							totalTimes++;
							continue;
						}

						colonSeperation = lines.split(":");
						colonSeperation[0] = colonSeperation[0].trim();
						loadedTypes = colonSeperation[0];

						if (loadedTypes.equals("")) {// if the user has no furniture type
							System.out
									.println("WARNING: Found incorrectly formatted line in file: " + (totalTimes + 1));
							totalTimes++;
							continue;
						} else {
							File f = new File("images/" + loadedTypes + ".png");// look to see if the furniture type is
																				// valid
							if (!f.exists()) {
								System.out.println("WARNING: Could not find an image for a furniture object of type: "
										+ loadedTypes);
								totalTimes++;
								continue;
							}
						}
						commaSeperation = colonSeperation[1].split(",");
						commaSeperation[0] = commaSeperation[0].trim();
						commaSeperation[1] = commaSeperation[1].trim();
						commaSeperation[2] = commaSeperation[2].trim();
						xPos = Float.parseFloat(commaSeperation[0]);
						yPos = Float.parseFloat(commaSeperation[1]);
						loadedRotations = Integer.parseInt(commaSeperation[2]);

						furniture[numTimes] = new Furniture(loadedTypes, processing, xPos, yPos, loadedRotations);
						// Make a new furniture with the info from .ddd

						numTimes++;
						totalTimes++;

					} else {

						totalTimes++;
						continue;
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("WARNING: Could not load room contents from file RoomData.ddd.");
			} 
			

		}

	}

	/**
	 * The mouseOver method determines whether the mouse is over the load button
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

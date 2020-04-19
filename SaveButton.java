
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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
// Partner Name:    Matt Schnabel
// Partner Email:   mrschnabel@wisc.edu
// Lecturer's Name: Alexander Brooks
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   _x__ We have registered our team prior to the team registration deadline.
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
/*
 * This class is used to create a button that has the ability to save the locations of objects in the room.
 */

public class SaveButton {

    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;

    /*
     * The SaveButton constructor is used to initialize the location and label of the button. 
     */
    public SaveButton(float x, float y, PApplet processing) {
        this.position = new float[2];
        position[0] = x;
        position[1] = y;
        this.processing = processing;
        this.label = "Save Room";
    }
    /*
     * When update() is called, the button is either colored with a darker shade of gray(corresponding to the mouse being over it)
     * or it is colored with a lighter shade of gray(the mouse is not over it).
     */
    public void update() {

        if (isMouseOver() == true) {
            processing.fill(100);
            processing.rect(602, 8, 698, 40);
        } else {
            processing.fill(200);
            processing.rect(602, 8, 698, 40);
        }
        processing.fill(0);
        processing.text(label, 650, 24);

    }
/*
 * The mouseDown() method first determines whether or not the mouse is over the save button when pressed down.
 * If true, a new file is created that will be used to save the locations of the objects in the room.
 * The type, x-coordinate, y-coordinate, and number of rotations are obtained and then written into the file for each existing
 * object. 
 */
    public void mouseDown(Furniture[] furniture) throws IOException   {
        if (isMouseOver() == true) {
            String fileName = "RoomData.ddd";
            System.out.println("Saving");
            FileOutputStream fileByteStream = null;
            PrintWriter outFS = null;

            fileByteStream = new FileOutputStream(fileName);//creating new file
            outFS = new PrintWriter(fileByteStream);//creating new printer to write to file

            for (int i = 0; i < furniture.length; i++) {//the object's location and type are written to the file if the object exists
                if (furniture[i] == null) {
                    outFS.print("\n");
                } else {
                    outFS.println(furniture[i].getType() + ":" + furniture[i].getPosition0() + "," + furniture[i].getPosition1()
                            + "," + furniture[i].getRotations());

                }
            }

            outFS.flush();
            outFS.close();//closing the printWriter

        } else {

        }
    }
    /*
     * This method checks to see if the mouse is over a furniture object when pressed down.
     * The method returns either true or false, depending on whether the mouse is over an object or not.
     */
    public boolean isMouseOver() {
        if (((processing.mouseX > position[0] - WIDTH / 2) && (processing.mouseX < position[0] + WIDTH / 2))//checks if mouse is within the dimensions of the object
                && ((processing.mouseY > position[1] - HEIGHT / 2) && (processing.mouseY < position[1] + HEIGHT / 2))) {
            return true;
        } else {
            return false;
        }
    }
}

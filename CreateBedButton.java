
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 3000
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
//   _x__ Write-up states that pair programming is allowed for this assignment.
//   _x__ We have both read and understand the course Pair Programming Policy.
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
 * This class is used to create a button that has the ability to make new furniture objects when pressed.
 */


public class CreateBedButton {

    private static final int WIDTH = 96; //the dimensions of the button
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;

    /*
     * The createBedButton() constructor is used to initialize the position of the button.
     */
    public CreateBedButton(float x, float y, PApplet processing) {
        this.position = new float[2];
        position[0] = x;
        position[1] = y;
        this.processing = processing;
        this.label = "Create Bed";
    }
/*
 * When update() is called, the button is either colored with a darker shade of gray(corresponding to the mouse being over it)
 * or it is colored with a lighter shade of gray(the mouse is not over it).
 */
    public void update() {
        
        if(isMouseOver() == true) {
        processing.fill(100);//darker gray color
        processing.rect(2, 8, 98, 40);//button is drawn here
        }
        else {
            processing.fill(200);
            processing.rect(2, 8, 98, 40);
        }
        processing.fill(0);
        processing.text(label, 50, 24);//the text written on top of the button
        
    }
/*
 * This method is called from the main class when the mouse is pressed down.
 * If the  mouse is over a furniture object when pressed down, a new furniture object is created.
 */
    public Furniture mouseDown() {
        if(isMouseOver()==true) {
            return new Furniture("bed", processing);
        }
        else {
        return null;
        }
    }
/*
 * This method checks to see if the mouse is over a furniture object when pressed down.
 * The method returns either true or false, depending on whether the mouse is over an object or not.
 */
    public boolean isMouseOver() {
        if (((processing.mouseX > position[0] - WIDTH / 2) && (processing.mouseX < position[0] + WIDTH / 2)) //checks if mouse is within the dimensions of the object
                && ((processing.mouseY > position[1] - HEIGHT / 2) && (processing.mouseY < position[1] + HEIGHT / 2))) {
            return true;
        } else {
            return false;
        }
    }
}

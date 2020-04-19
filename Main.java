//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 3000
// Files:           Main.java
// Course:          CS 300 Spring Semester 2018
//
// Author:          Vincent Angellotti
// Email:           angellotti@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Matt Schnabel
// Partner Email:   mrschnab@wisc.edu
// Lecturer's Name: Alexander Brooks
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___x Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   __x_ We have registered our team prior to the team registration deadline.
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

import java.io.IOException;
/*
 * This is the main class where input from the user is processed.
 * The program is repeatedly updated by calling other classes from main.
 */
public class Main {

    private PApplet processing;
    private PImage backgroundImage;
    private Furniture[] furniture;
    private CreateBedButton bedButton;
    private CreateSofaButton sofaButton;
    private SaveButton saveButton;
    private LoadButton loadButton;

    /*
     * The main constructor is used to initialize all variables as well as to create new instances of the other classes.
     * 6 bed objects are set to null, and the background image is first drawn here.
     * @param processing 
     */
    public Main(PApplet processing) {
        PImage backgroundImage = processing.loadImage("Images/background.png"); 
        this.backgroundImage = backgroundImage;

        this.processing = processing;
        this.furniture = new Furniture[6];

        Furniture Bed0 = null;//makes all bed positions null
        Furniture Bed1 = null;
        Furniture Bed2 = null;
        Furniture Bed3 = null;
        Furniture Bed4 = null;
        Furniture Bed5 = null;

        furniture[0] = Bed0;
        furniture[1] = Bed1;
        furniture[2] = Bed2;
        furniture[3] = Bed3;
        furniture[4] = Bed4;
        furniture[5] = Bed5;

        bedButton = new CreateBedButton(50, 24, processing);
        sofaButton = new CreateSofaButton(150, 24, processing);
        saveButton = new SaveButton(650, 24, processing);
        loadButton = new LoadButton(750, 24, processing);
    }
/*
 * The main method contains the prompt to begin the program.
 * Once called from main, the Utility class repeatedly calls all other methods in main.  
 */
    public static void main(String[] args) {
        Utility.startApplication();  

    }
/*
 * update is called from Utility to update the background image as well as other functions of this program.
 * The update methods of the other classes are called from this method.
 */
    public void update() {
        processing.background(100, 150, 250);// makes background correct color
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        bedButton.update();
        sofaButton.update();
        saveButton.update();
        loadButton.update();
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) { //furniture objects are only updated if they are not set to null
                furniture[i].update();
            }
        }

    }
/*
 * This method is called when the user presses the mouse down.
 * For each non-null bed object, mouseDown() calls the mouseDown method located in the furniture class.
 * The method checks to see whether the mouse is located over a button when pressed down. If true, the corresponding 
 * mouseDown() method located in one of the button classes is called.
 */
    public void mouseDown() {
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                furniture[i].mouseDown();

            }
        }
        if (bedButton.isMouseOver() == true) { //checks to see if mouse is over the button
            for (int i = 0; i < furniture.length; i++) {
                if (furniture[i] == null) {
                    furniture[i] = bedButton.mouseDown();
                    break;
                } else {
                    continue;
                }
            }
        }
        if(sofaButton.isMouseOver()==true) {
            for (int i = 0; i < furniture.length; i++) {
                if (furniture[i] == null) {
                    furniture[i] = sofaButton.mouseDown();
                    break;
                } else {
                    continue;
                }
            }
        }
        if(saveButton.isMouseOver()==true) {
            
            try {
                saveButton.mouseDown(furniture);
            } catch(IOException e) {
                
            }
        }
        if(loadButton.isMouseOver()==true) {
            
                loadButton.mouseDown(furniture);
            
        }
    }
/*
 * This method is only called when the mouse is released after being pressed down.
 * For each bed object, the mouseUp() method in the furniture class is called.
 */
    public void mouseUp() {
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                furniture[i].mouseUp();
            }
        }
    }
/*
 * The keyPressed() method processes the userInput from the keyboard.
 * If the 'd' key is pressed and the mouse is over an object, that object is deleted.
 * If the 'r' key is pressed and the mouse is over an object, that object is rotated 90 degrees after the rotate() method in 
 * the furniture class is called.
 */
    public void keyPressed() {
        
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                if ((processing.key == 'd' || processing.key == 'D') && furniture[i].isMouseOver() == true) {
                    furniture[i] = null;
                } else if ((processing.key == 'r' || processing.key == 'R') && furniture[i].isMouseOver() == true) {
                    furniture[i].rotate();
                }
            }
        }
    }

}

import java.awt.Color;

/**
 *
 * @author James Phelan and Michael Phelan
 */
public class Tolken {//tolkens should be circles or squares to make life ez

    private int trueX;// where trueX and trueY represent the physically drawn location
    private int trueY;
    private int x;//where x and y represent the center of the tolken on the grid
    private int y;// x or y * the gridSize + .5 * gridSize should put the tolken in the center of a square
    private int size; //this is the radius of the circle
    private final int scale = 30; //this is how much the size is multiplied by
    private int passedGridSize; //I guess I have to pass this down from the top
    private Character c; //this contains all the info you could ever want from the character
    private boolean isSelected; //is this tolken selected?
    private Color color; //the color of the tolken
    private boolean canMove; //can this move with WASD?
    private String imagePath; //if there is an image it will have this path

    Tolken(int X, int Y, int Size, Character C, Color COLOR,boolean move ,boolean snap, int gridsize) {//size should be an integer 1-5ish (thats why I multiply) also x and y snap to the grid
        if (snap) {
            trueX = (X * gridsize) + (int) (.25 * gridsize);
            trueY = (Y * gridsize) + (int) (.25 * gridsize);
            size = (Size * scale);
            x = X;
            y = Y;
        } else {
            trueX = X;
            trueY = Y;
            size = (Size * scale);
        }
        passedGridSize = gridsize;
        c = C;
        color = COLOR;
        canMove = move;
    }
    Tolken(int X, int Y, int Size, Character C,String imagepath, Color COLOR,boolean move ,boolean snap, int gridsize) {//size should be an integer 1-5ish (thats why I multiply) also x and y snap to the grid
        if (snap) {
            trueX = (X * gridsize) + (int) (.25 * gridsize);
            trueY = (Y * gridsize) + (int) (.25 * gridsize);
            size = (Size * scale);
            x = X;
            y = Y;
        } else {
            trueX = X;
            trueY = Y;
            size = (Size * scale);
        }
        passedGridSize = gridsize;
        c = C;
        color = COLOR;
        canMove = move;
        imagePath = imagepath;
    }

    public void moveRightOne() {//these four functions increment the fake and real x and y to move the tolkens
        if(canMove){
        x = x + 1;
        trueX = trueX + passedGridSize;
    }}

    public void moveLeftOne() {
        if(canMove){
        x = x - 1;
        trueX = trueX - passedGridSize;
        }
    }

    public void moveDownOne() {
        if(canMove){
        y = y + 1;
        trueY = trueY + passedGridSize;
        }
    }

    public void moveUpOne() {
        if(canMove){
        y = y - 1;
        trueY = trueY - passedGridSize;
        }
    }

    public void moveTopLeft() {//these four just combine pairs from the last four to move diagonally
        moveUpOne();
        moveLeftOne();
    }

    public void moveTopRight() {
        moveUpOne();
        moveRightOne();
    }

    public void moveBottomLeft() {
        moveDownOne();
        moveLeftOne();
    }

    public void moveBottomRight() {
        moveDownOne();
        moveRightOne();
    }

    public void setX(int i) {
        x = i;
        trueX = x * passedGridSize +(int) (.25 * passedGridSize);
    }

    public void setY(int i) {
        y = i;
        trueY = y * passedGridSize + (int) (.25 * passedGridSize);
    }

    public void setTrueX(int i) {
        trueX = i;
        x = i / passedGridSize;
    }

    public void setTrueY(int i) {
        trueY = i;
        y = i / passedGridSize;
    }

    public void setSize(int i) {
        size = (i * scale); //want the size to be the same as up top
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTrueX() {
        return trueX;
    }

    public int getTrueY() {
        return trueY;
    }

    public int getSize() {
        return (size / scale); //want to return what is put in
    }

    public int getRealSize() {
        return size; //returns the physical size of this thing
    }

    public void setCharacter(Character C) {
        c = C;
    }

    public Character getCharacter() {
        return c;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean b) {
        isSelected = b;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color COLOR) {
        color = COLOR;
    }
    public void setCanMove(boolean move){
        canMove = move;
    }
    public boolean getCanMove(){
        return canMove;
    }
    public String getImagePath(){
        return imagePath;
    }
    public void setImagePath(String s){
        imagePath = s;
    }
}

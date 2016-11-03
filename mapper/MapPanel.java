package mapper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.sqrt;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/**
 * @author James Phelan and Michael Phelan
 */

@SuppressWarnings("serial")
public class MapPanel extends JPanel implements KeyListener, MouseListener {
    int maxTolkens = 8;
    Tolken[] tolkens = new Tolken[maxTolkens];
    boolean firstRun = true; //this tells the run stuff to do things the first time but not again (helps with initialization)
    boolean snapToGrid = true; //this boolean decides if we should snap to grid or not
    int gridSize = 30; //this is the length and height of each individual grid
    int mapSize = 20000; //this is the number of squares in each direction on the grid, probably keep it bigish
    int selectedTolken = 0; //this variable shows exactly which tolken will be changed
    boolean ClickDragControl = false; //this is a variable relating to clicking and dragging-> you will still be able to click and drag if it is false
    boolean tapeMeasuring = false; // becomes true for the duration of a measurement
    int initialTapeX; //start points of the tape measure
    int initialTapeY;
    int finalTapeX;//finish points of the tape measure
    int finalTapeY;
    int tapeDistance; //Tape measure's distance
    boolean isTape = false; //just used to clear the tape from the screen
    int jj;//thse two ints are to make it pretty when dragging big objects
    int kk;//now objects will drop like they were picked up
    int activeTolkens = -1; //how many tolkens are on the field
    Tolken[] initiative = new Tolken[maxTolkens];
    int roundNumber = 1; //how many rounds has it been?
    int roundProgress = -1;//this is the progress during a round

    public MapPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1010, 600));

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g) {//this is where things are painted / called to be painted
        super.paintComponent(g);
        paintGrid(g);//does what it says
        drawTolkens(g, tolkens);//goes to draw tolkens and passes an array of tolkens
        drawTape(g);
        drawPane(g);
        drawRightPane(g);
    }

    public void drawPane(Graphics g) {//this draws the side pane and fills it with stuff
        g.setColor(Color.WHITE);//lots of access to the character linked with the tolkens
        g.fillRect(0, 0, 90, 10000);
        g.setColor(Color.BLACK);
        g.drawLine(0, 90, 90, 90);
        g.drawRect(0, 0, 30, 30);
        g.drawString("New", 2, 15);
        g.drawRect(0, 0, 60, 30);
        g.drawString("Size+", 32, 15);
        g.drawRect(0, 0, 90, 30);
        g.drawString("Size-", 62, 15);
        g.drawString("Selected: " + (selectedTolken + 1), 15, 45);
        g.drawString(tolkens[selectedTolken].getCharacter().getName(), 2, 60);
        g.drawString(tolkens[selectedTolken].getCharacter().getRace(), 50, 60);
        g.drawString(tolkens[selectedTolken].getCharacter().getCLASS(), 2, 75);
        g.drawString("lvl: " + tolkens[selectedTolken].getCharacter().getLevel(), 60, 75);
        g.drawString("Size: " + convertSize(tolkens[selectedTolken].getCharacter().getSize()), 10, 88);
        g.drawRect(0, 0, 90, 270);
        g.drawString("Max HP: " + tolkens[selectedTolken].getCharacter().getMaxHP(), 2, 105);
        g.drawString("Current HP: " + tolkens[selectedTolken].getCharacter().getCurrentHP(), 2, 120);
        g.drawString("AC: " + tolkens[selectedTolken].getCharacter().getAC(), 2, 135);
        g.drawString("Initiative: " + tolkens[selectedTolken].getCharacter().getInitiative(), 2, 150);
        g.drawString("Speed: " + tolkens[selectedTolken].getCharacter().getSpeed(), 2, 165);
        g.drawString("STR: " + tolkens[selectedTolken].getCharacter().getSTR(), 2, 180);
        g.drawString("DEX: " + tolkens[selectedTolken].getCharacter().getDEX(), 2, 195);
        g.drawString("CON: " + tolkens[selectedTolken].getCharacter().getCON(), 2, 210);
        g.drawString("INT: " + tolkens[selectedTolken].getCharacter().getINT(), 2, 225);
        g.drawString("WIS: " + tolkens[selectedTolken].getCharacter().getWIS(), 2, 240);
        g.drawString("CHA: " + tolkens[selectedTolken].getCharacter().getCHA(), 2, 255);
        if (tolkens[selectedTolken].getCharacter().getSTRThrow()) {
            g.drawString("☼", 50, 180);
        }
        if (tolkens[selectedTolken].getCharacter().getDEXThrow()) {
            g.drawString("☼", 50, 195);
        }
        if (tolkens[selectedTolken].getCharacter().getCONThrow()) {
            g.drawString("☼", 50, 210);
        }
        if (tolkens[selectedTolken].getCharacter().getINTThrow()) {
            g.drawString("☼", 50, 225);
        }
        if (tolkens[selectedTolken].getCharacter().getWISThrow()) {
            g.drawString("☼", 50, 240);
        }
        if (tolkens[selectedTolken].getCharacter().getCHAThrow()) {
            g.drawString("☼", 50, 255);
        }
        g.drawRect(0, 0, 90, 390);
        g.drawRect(60, 270, 30, 30);
        g.drawRect(0, 270, 60, 30);
        g.drawString("Initiative", 2, 285);
        g.drawString("Rnd", 63, 282);
        g.drawString("" + roundNumber, 65, 297);
        g.drawString("Next", 62, 315);
        g.drawRect(60, 300, 30, 30);
        for (int i = 0; i <= activeTolkens; i++) {
            int d = 315 + (i * 15);//draw them in order
            if (i < 6) {
                g.drawString(initiative[i].getCharacter().getName() + ": " + initiative[i].getCharacter().getInitiative(), 5, d);
            }
        }
    }
    public void drawRightPane(Graphics g){
         g.setColor(Color.WHITE);//lots of access to the character linked with the tolkens
        g.fillRect(this.getWidth()-135, 0, 135, 10000);
        g.setColor(Color.BLACK);
        g.drawString("Acrobatics = " + tolkens[selectedTolken].getCharacter().getAcrobatics(), this.getWidth()-130, 15);
        g.drawString("AnimalHandling = " + tolkens[selectedTolken].getCharacter().getAnimalHandling(), this.getWidth()-130, 30);
        g.drawString("Arcana = " + tolkens[selectedTolken].getCharacter().getArcana(), this.getWidth()-130, 45);
        g.drawString("Athletics = " + tolkens[selectedTolken].getCharacter().getAthletics(), this.getWidth()-130, 60);
        g.drawString("Deception = " + tolkens[selectedTolken].getCharacter().getDeception(), this.getWidth()-130, 75);
        g.drawString("History = " + tolkens[selectedTolken].getCharacter().getHistory(), this.getWidth()-130, 90);
        g.drawString("Insight = " + tolkens[selectedTolken].getCharacter().getInsight(), this.getWidth()-130, 105);
        g.drawString("Intimidation = " + tolkens[selectedTolken].getCharacter().getIntimidation(), this.getWidth()-130, 120);
        g.drawString("Investigation = " + tolkens[selectedTolken].getCharacter().getInvestigation(), this.getWidth()-130, 135);
        g.drawString("Medicine = " + tolkens[selectedTolken].getCharacter().getMedicine(), this.getWidth()-130, 150);
        g.drawString("Nature = " + tolkens[selectedTolken].getCharacter().getNature(), this.getWidth()-130, 165);
        g.drawString("Perception = " + tolkens[selectedTolken].getCharacter().getPerception(), this.getWidth()-130, 180);
        g.drawString("Performance = " + tolkens[selectedTolken].getCharacter().getPerformance(), this.getWidth()-130, 195);
        g.drawString("Persuasion = " + tolkens[selectedTolken].getCharacter().getPersuasion(), this.getWidth()-130, 210);
        g.drawString("Religion = " + tolkens[selectedTolken].getCharacter().getReligion(), this.getWidth()-130, 225);
        g.drawString("SleightOfHand = " + tolkens[selectedTolken].getCharacter().getSleightOfHand(), this.getWidth()-130, 240);
        g.drawString("Stealth = " + tolkens[selectedTolken].getCharacter().getStealth(), this.getWidth()-130, 255);
        g.drawString("Survival = " + tolkens[selectedTolken].getCharacter().getSurvival(), this.getWidth()-130, 270);
    }
    public String convertSize(int size) {
        switch (size) {
            case 1:
                return "Tiny";
            case 2:
                return "Small";
            case 3:
                return "Medium";
            case 4:
                return "Large";
            case 5:
                return "Huge";
            case 6:
                return "Gargatuan";
            default:
                break;
        }
        return "?";
    }

    public void drawTape(Graphics g) {
        if (isTape) {//as long as isTape is true we will show the tape
            g.setColor(Color.BLACK);
            g.drawLine(initialTapeX, initialTapeY, finalTapeX, finalTapeY);
            g.drawString("Distance: " + tapeDistance * 5 / gridSize, finalTapeX, finalTapeY);
        }
    }

    public void paintGrid(Graphics g) { //this draws the grid
        g.setColor(Color.GREEN);
        for (int j = 1; j <= mapSize; j = j + gridSize) {//the very large number lets us make a large grid
            for (int k = 1; k <= mapSize; k = k + gridSize) {
                g.drawRect(j, k, j + gridSize, k + gridSize);//gridSize - 1 on this line removes the bolded lines in the grid
            }
        }
    }

    public void drawTolkens(Graphics g, Tolken[] t) { // this draws the player's tolkens
        //this uses an array of tolkens so we can have like 10000000
        //remember true y and x are the physically drawn locations ALWAYS
        // they just change to reflect the fake x and y if snap to grid is on
        for (int i = 0; i < maxTolkens; i++) { //not all tolkens have to be used at all times
            g.setColor(tolkens[i].getColor());
            if (t[i].getSize() % 2 != 0) {
                g.fillOval(t[i].getTrueX() - 5,
                        t[i].getTrueY() - 5,
                        t[i].getRealSize() - 1,//circles don't draw from the center - > they are inscribed in a square so funkyness get this drawn all right
                        t[i].getRealSize() - 1); // an unused token will have size 0 to not be drawn
                if (t[selectedTolken].getImagePath() != null) {
                    try {//if there is an image attached to the tolken use it
                        BufferedImage image = ImageIO.read(new File(t[selectedTolken].getImagePath()));
@SuppressWarnings("cast")
                        BufferedImage buffered = (BufferedImage) image;
                        g.drawImage(image, t[selectedTolken].getTrueX() - 4,
                                t[selectedTolken].getTrueY() - 4,
                                t[selectedTolken].getRealSize() - 1,
                                t[selectedTolken].getRealSize() - 1,
                                this);
                    } catch (IOException ex) {
                        System.out.println("Oh no");
                    }

                }
            } else {//even sized things are drawn from the corner not the center
                g.fillOval(t[i].getTrueX() - 4,
                        t[i].getTrueY() - 4,
                        t[i].getRealSize() - 1, t[i].getRealSize() - 1);
                if (t[selectedTolken].getImagePath() != null) {
                    try {
                        BufferedImage image = ImageIO.read(new File(t[selectedTolken].getImagePath()));
@SuppressWarnings("cast")
                        BufferedImage buffered = (BufferedImage) image;
                        g.drawImage(image, t[selectedTolken].getTrueX() - 4,
                                t[selectedTolken].getTrueY() - 4,
                                t[selectedTolken].getRealSize() - 1,
                                t[selectedTolken].getRealSize() - 1,
                                this);
                    } catch (IOException ex) {
                        System.out.println("Oh no");
                    }

                }
            }
        }
        g.setColor(tolkens[selectedTolken].getColor());//this section highlights and puts the selected one on top
        if (t[selectedTolken].getSize() % 2 != 0) {
            g.fillOval(t[selectedTolken].getTrueX() - 5,
                    t[selectedTolken].getTrueY() - 5,
                    t[selectedTolken].getRealSize() - 1,
                    t[selectedTolken].getRealSize() - 1);
            g.setColor(Color.YELLOW);
            g.drawOval(t[selectedTolken].getTrueX() - 2,
                    t[selectedTolken].getTrueY() - 2,
                    t[selectedTolken].getRealSize() - 6,
                    t[selectedTolken].getRealSize() - 6);
        } else {//even sized things are drawn from the corner not the center
            g.fillOval(t[selectedTolken].getTrueX() - 4,
                    t[selectedTolken].getTrueY() - 4,
                    t[selectedTolken].getRealSize() - 1,
                    t[selectedTolken].getRealSize() - 1);
            g.setColor(Color.YELLOW);
            g.drawOval(t[selectedTolken].getTrueX() - 1,
                    t[selectedTolken].getTrueY() - 1,
                    t[selectedTolken].getRealSize() - 6,
                    t[selectedTolken].getRealSize() - 6);
        }
        if (t[selectedTolken].getImagePath() != null) {
            if (t[selectedTolken].getSize() % 2 != 0) {
                try {

                    BufferedImage image = ImageIO.read(new File(t[selectedTolken].getImagePath()));
@SuppressWarnings("cast")
                    BufferedImage buffered = (BufferedImage) image;
                    g.drawImage(image, t[selectedTolken].getTrueX() - 4,
                            t[selectedTolken].getTrueY() - 4,
                            t[selectedTolken].getRealSize() - 1,
                            t[selectedTolken].getRealSize() - 1,
                            this);
                    g.setColor(Color.YELLOW);
                    g.drawOval(t[selectedTolken].getTrueX() - 2,
                            t[selectedTolken].getTrueY() - 2,
                            t[selectedTolken].getRealSize() - 6,
                            t[selectedTolken].getRealSize() - 6);
                } catch (IOException ex) {
                    System.out.println("Oh no!");
                }
            } else {
                try {
                    BufferedImage image = ImageIO.read(new File(t[selectedTolken].getImagePath()));
@SuppressWarnings("cast")
                    BufferedImage buffered = (BufferedImage) image;
                    g.drawImage(image, t[selectedTolken].getTrueX() - 4,
                            t[selectedTolken].getTrueY() - 4,
                            t[selectedTolken].getRealSize() - 1,
                            t[selectedTolken].getRealSize() - 1, this);
                    g.setColor(Color.YELLOW);
                    g.drawOval(t[selectedTolken].getTrueX() - 1,
                            t[selectedTolken].getTrueY() - 1,
                            t[selectedTolken].getRealSize() - 6,
                            t[selectedTolken].getRealSize() - 6);
                } catch (IOException ex) {
                    System.out.println("Oh no!");
                }
            }
        }

    }

    public void sortByInitiative() {
        Tolken[] sortedTolkens = new Tolken[activeTolkens + 1];//LET'S SORT!
        for (int i = 0; i < activeTolkens + 1; i++) {
            Tolken d = tolkens[i];//its populated like this to have independent data of the array
            sortedTolkens[i] = d;//populate the array
        }
        sortedTolkens[selectedTolken].setIsSelected(false);
        for (int o = 0; o < activeTolkens + 1; o++) {
            for (int p = 0; p < activeTolkens + 1; p++) {//sorted so largest is first
                if (sortedTolkens[o].getCharacter().getInitiative() > sortedTolkens[p].getCharacter().getInitiative()) {
                    Tolken t = sortedTolkens[o];
                    sortedTolkens[o] = sortedTolkens[p];
                    sortedTolkens[p] = t;
                }
            }
        }//now I have an array
        initiative = sortedTolkens;
        repaint();
    }

    public void newRound() {//if the initiative progresses all the way then there is a new round
        if (roundProgress == activeTolkens) {
            roundNumber = roundNumber + 1;
            roundProgress = -1;
        }

    }

    public void nextTurn() {//moves the first person to last and everyone else moves up
        roundProgress = roundProgress + 1;
        for (int i = activeTolkens; i > 0; i--) {
            Tolken t = initiative[0];
            initiative[0] = initiative[i];
            initiative[i] = t;
        }

        newRound();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();//WASD calls functions to move a particular tolken
        if (key == KeyEvent.VK_W) {//need to find a way to select a tolken (and show it)
            tolkens[selectedTolken].moveUpOne();
            repaint();
        }
        if (key == KeyEvent.VK_A) {
            tolkens[selectedTolken].moveLeftOne();
            repaint();
        }
        if (key == KeyEvent.VK_S) {
            tolkens[selectedTolken].moveDownOne();
            repaint();
        }

        if (key == KeyEvent.VK_D) {
            tolkens[selectedTolken].moveRightOne();
            repaint();
        }
//////////////////These four are for diagonals///////////////////////////////////////////
        if (key == KeyEvent.VK_Q) {
            tolkens[selectedTolken].moveTopLeft();
            repaint();
        }
        if (key == KeyEvent.VK_E) {
            tolkens[selectedTolken].moveTopRight();
            repaint();
        }
        if (key == KeyEvent.VK_Z) {
            tolkens[selectedTolken].moveBottomLeft();
            repaint();
        }

        if (key == KeyEvent.VK_C) {
            tolkens[selectedTolken].moveBottomRight();
            repaint();
        }
//////////////////Switch back and forth with r and f///////////////////////////////////////////
        if (key == KeyEvent.VK_F) {
            if (selectedTolken < tolkens.length - 1 && selectedTolken < activeTolkens) {//stay in the array and active tolkens
                tolkens[selectedTolken].setIsSelected(false);//deselectes previous tolken
                selectedTolken = selectedTolken + 1;
                tolkens[selectedTolken].setIsSelected(true);//selectes next tolken
                repaint();
            } else {
                tolkens[selectedTolken].setIsSelected(false);//deselectes previous tolken
                selectedTolken = 0;
                tolkens[selectedTolken].setIsSelected(true);//selectes next tolken
            }
        }
        if (key == KeyEvent.VK_R) {
            if (selectedTolken > 0) {
                tolkens[selectedTolken].setIsSelected(false);//deselectes previous tolken
                selectedTolken = selectedTolken - 1;//arrays start at 0 but this returns #of entries therefore -1
                tolkens[selectedTolken].setIsSelected(true);//selectes next tolken
                repaint();
            } else {
                tolkens[selectedTolken].setIsSelected(false);//deselectes previous tolken
                selectedTolken = activeTolkens; //arrays start at 0 but this returns #of entries therefore -1
                tolkens[selectedTolken].setIsSelected(true);//selectes next tolken
            }
            repaint();
        }
        if (key == KeyEvent.VK_X) {//press x to clear tape from screen
            isTape = false;
            repaint();
        }
        if (key == KeyEvent.VK_L) {
            JFrame f = new JFrame("Press 'Enter' when done");
            JTextField j = new JTextField("Character Filename", 20);
            f.getContentPane().add(j);
            f.pack();
            f.setVisible(true);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tolkens[selectedTolken].getCharacter().fromText(j.getText());
                    f.setVisible(false);
                    repaint();
                }
            });

        }
        if (key == KeyEvent.VK_K) {
            JFrame f = new JFrame("Press 'Enter' when done");
            JTextField j = new JTextField("Character Filename", 20);
            f.getContentPane().add(j);
            f.pack();
            f.setVisible(true);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tolkens[selectedTolken].getCharacter().toText(j.getText());
                    f.setVisible(false);
                    repaint();
                }
            });
        }

        if (key == KeyEvent.VK_I) {
            JFrame f = new JFrame("Press 'Enter' when done");
            JTextField j = new JTextField("New Initiative", 20);
            f.getContentPane().add(j);
            f.pack();
            f.setVisible(true);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tolkens[selectedTolken].getCharacter().setInitiative(parseInt(j.getText()));
                    f.setVisible(false);
                    repaint();
                }
            });
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e
    ) {
        ///////////////////This is a selection section////////////////////////
@SuppressWarnings("cast")
        int xGrid = (int) (e.getX() / gridSize); //use these to find the grid x,y
@SuppressWarnings("cast")
        int yGrid = (int) (e.getY() / gridSize);

        if (xGrid == 0 && yGrid == 0) {//selects and uses the new button
            if (activeTolkens != -1) {
                selectedTolken = activeTolkens + 1;
            }
            activeTolkens = activeTolkens + 1;
            if (tolkens[activeTolkens].getCanMove() != true) {
                tolkens[activeTolkens].setCanMove(true);
                tolkens[activeTolkens].setSize(1);
                tolkens[activeTolkens].setX(3);
                tolkens[activeTolkens].setY(0);
            }
        }
        if (xGrid == 1 && yGrid == 0) {//makes the tolken bigger
            if (tolkens[selectedTolken].getCanMove()) {//has to be on screen to do this now
                if (tolkens[selectedTolken].getCharacter().getSize() < 3) {
                    tolkens[selectedTolken].getCharacter().setSize(tolkens[selectedTolken].getCharacter().getSize() + 1);
                } else {
                    tolkens[selectedTolken].setSize(tolkens[selectedTolken].getSize() + 1);
                    tolkens[selectedTolken].getCharacter().setSize(tolkens[selectedTolken].getSize() + 2);
                }
            }
        }
        if (xGrid == 2 && yGrid == 0) {//makes the tolken smaller
            if (tolkens[selectedTolken].getSize() > 1) {
                tolkens[selectedTolken].setSize(tolkens[selectedTolken].getSize() - 1);
            }
            if (tolkens[selectedTolken].getCharacter().getSize() - 1 > 0) {
                tolkens[selectedTolken].getCharacter().setSize(tolkens[selectedTolken].getCharacter().getSize() - 1);
            }
        }
        if (xGrid == 0 && yGrid == 9 || xGrid == 1 && yGrid == 9) {//sorts by initiative
            sortByInitiative();
            System.out.println("sorting");
        }
        if (xGrid == 2 && yGrid == 10) {//advances to next turn and potentially round
            nextTurn();
        }
        if (e.getButton() == 1) { //can only be left clicking
            for (int i = 0; i < tolkens.length; i++) {//tolkens take up multiple squares sometimes = tripple for loop
                for (int j = 0; j < tolkens[i].getSize(); j++) {
                    for (int k = 0; k < tolkens[i].getSize(); k++) {
                        if (tolkens[i].getX() + j == xGrid && tolkens[i].getY() + k == yGrid) {//if x and y match up then select the thing and break out of the loop
                            tolkens[selectedTolken].setIsSelected(false);
                            selectedTolken = i; //this makes the tolken found also the tolken selected
                            tolkens[i].setIsSelected(true);
                            ClickDragControl = true;
                            repaint();
                            jj = j;
                            kk = k;
                            break;
                        }
                    }
                }

            }

        }
/////////////////////////This is a tape measure section//////////////////////////////
        if (e.getButton() == 3) {//if the mouse is right clicked
            if (e.isShiftDown()) {
                tapeMeasuring = true;
                initialTapeX = (gridSize) * (e.getX() / (gridSize));//casting to an int then dividing and multiplying to put
                initialTapeY = (gridSize) * (e.getY() / (gridSize));//us in the middle of a square
                initialTapeX = initialTapeX + (int) (.5 * gridSize);
                initialTapeY = initialTapeY + (int) (.5 * gridSize);
            } else {
                tapeMeasuring = true;
                initialTapeX = e.getX();
                initialTapeY = e.getY();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
        //////////////////////this is a tolken moving section///////////////////////////
        @SuppressWarnings("cast")
        int xGrid = (int) (e.getX() / gridSize) - jj; //use these to find the grid x,y
        @SuppressWarnings("cast")
        int yGrid = (int) (e.getY() / gridSize) - kk;
        if (ClickDragControl) {
            tolkens[selectedTolken].setX(xGrid);//moves the selected tolken to where the mouse is released
            tolkens[selectedTolken].setY(yGrid);
        }
        ClickDragControl = false;//to reset to initial condition
        repaint();

///////////////////////////////This is a tape measure section///////////////////////////
        if (e.getButton() == 3) {//if the mouse is released
            if (tapeMeasuring) {//this is for tape measuring
                if (e.isShiftDown()) {//this is the round it off section
                    finalTapeX = (gridSize) * (e.getX() / (gridSize));//where the mouse is rounded
                    finalTapeY = (gridSize) * (e.getY() / (gridSize));
                    finalTapeX = finalTapeX + (int) (.5 * gridSize);//this just centers the final points
                    finalTapeY = finalTapeY + (int) (.5 * gridSize);
                } else {//true value section
                    finalTapeX = e.getX(); //get where the mouse is
                    finalTapeY = e.getY();
                }
                isTape = true; //this lets the tape be drawn on the screen
                int a = initialTapeX - finalTapeX; //change in x
                int b = initialTapeY - finalTapeY; //change in y
                tapeDistance = (int) (sqrt((a * a) + (b * b)));//pythagorean to find distance
                System.out.println((sqrt((a * a) + (b * b))));
            }
        }
        tapeMeasuring = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void run() {//this actually only runs once anyway but I might change that
//I think I have to initialize all the tolkens to not get errors so thats whats happening here///
        if (firstRun) { //but I only initialize them once so thats why the boolean is here
            for (int i = 0; i < maxTolkens; i++) {
                Character C = new Character(); //makes a new blank character for tolkens
                initiative[i] = new Tolken(-1, -1, 0, C, Color.BLACK, false, snapToGrid, gridSize);
                tolkens[i] = new Tolken(-1, -1, 0, C, Color.BLACK, false, snapToGrid, gridSize);
                //tolkens[i] = new Tolken(-1, -1, 0, C, "C:\\Users\\James\\Pictures\\Wallpaper.png", Color.BLACK, false, snapToGrid, gridSize);//makes initial tolkens
                tolkens[i].getCharacter().setInitiative(i);
                if (i == selectedTolken) {//to make sure tolken is created before being modified
                    tolkens[selectedTolken].setIsSelected(true);//just makes sure initial tolken is selected
                }
            }
            firstRun = false;
        }
        try {//do stuff every little while
            Thread.sleep(5);
        } catch (Exception e) {
        }
        repaint();//goes to paint component
    }
}

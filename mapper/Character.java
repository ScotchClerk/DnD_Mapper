import java.io.*;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/**
 * @author James Phelan and Michael Phelan
 */
public class Character {

    private int Level;
    private int MaxHP;
    private int CurrentHP;
    private int AC;
    private int STR;
    private int DEX;
    private int CON;
    private int INT;
    private int WIS;
    private int CHA;
    private int speed;
    private int initiative;
    private boolean Acrobatics;
    private boolean AnimalHandling;
    private boolean Arcana;
    private boolean Athletics;
    private boolean Deception;
    private boolean History;
    private boolean Insight;
    private boolean Intimidation;
    private boolean Investigation;
    private boolean Medicine;
    private boolean Nature;
    private boolean Perception;
    private boolean Performance;
    private boolean Persuasion;
    private boolean Religion;
    private boolean SleightOfHand;
    private boolean Stealth;
    private boolean Survival;
    private boolean STRThrow;
    private boolean DEXThrow;
    private boolean CONThrow;
    private boolean INTThrow;
    private boolean WISThrow;
    private boolean CHAThrow;
    private String Name;
    private String Race;
    private String Class;
    private int Size;// tiny small medium large huge gargantuan

    Character() {
        Level = 0;
        MaxHP = 0;
        CurrentHP = MaxHP;
        AC = 0;
        STR = 0;
        DEX = 0;
        CON = 0;
        INT = 0;
        WIS = 0;
        CHA = 0;
        speed = 0;
        initiative = 0;
        Acrobatics = false;
        AnimalHandling = false;
        Arcana = false;
        Athletics = false;
        Deception = false;
        History = false;
        Insight = false;
        Intimidation = false;
        Investigation = false;
        Medicine = false;
        Nature = false;
        Perception = false;
        Performance = false;
        Persuasion = false;
        Religion = false;
        SleightOfHand = false;
        Stealth = false;
        Survival = false;
        STRThrow = false;
        DEXThrow = false;
        CONThrow = false;
        INTThrow = false;
        WISThrow = false;
        CHAThrow = false;
        Race = "None";
        Class = "None";
        Name = "None";
        Size = 3;
    }

    Character(String name, String race, String CLASS, int size, int lvl, int maxhp, int currenthp, int ac, int str, int dex, int con, int inte, int wis, int cha,
            int spEEd, int init, boolean acrobatics, boolean animalhandling,
            boolean arcana, boolean athletics, boolean deception, boolean history,
            boolean insight, boolean intimidation, boolean investigation, boolean medicine,
            boolean nature, boolean perception, boolean performance, boolean persuasion,
            boolean religion, boolean sleightofhand, boolean stealth, boolean survival, boolean strthrow, boolean dexthrow, boolean conthrow,
            boolean intthrow, boolean wisthrow, boolean chathrow) {
        Level = lvl;
        MaxHP = maxhp;
        CurrentHP = currenthp;
        AC = ac;
        STR = str;
        DEX = dex;
        CON = con;
        INT = inte;
        WIS = wis;
        CHA = cha;
        speed = spEEd;
        initiative = init;
        Acrobatics = acrobatics;
        AnimalHandling = animalhandling;
        Arcana = arcana;
        Athletics = athletics;
        Deception = deception;
        History = history;
        Insight = insight;
        Intimidation = intimidation;
        Investigation = investigation;
        Medicine = medicine;
        Nature = nature;
        Perception = perception;
        Performance = performance;
        Persuasion = persuasion;
        Religion = religion;
        SleightOfHand = sleightofhand;
        Stealth = stealth;
        Survival = survival;
        STRThrow = strthrow;
        DEXThrow = dexthrow;
        CONThrow = conthrow;
        INTThrow = intthrow;
        WISThrow = wisthrow;
        CHAThrow = chathrow;
        Name = name;
        Race = race;
        Class = CLASS;
        Size = size;

    }

    Character(String name, int size, int lvl, int maxhp, int currenthp, int ac, int str, int dex, int con, int inte, int wis, int cha,
            int spEEd, int init, boolean strthrow, boolean dexthrow, boolean conthrow,
            boolean intthrow, boolean wisthrow, boolean chathrow) {
        Name = name;
        Size = size;
        Level = lvl;
        MaxHP = maxhp;
        CurrentHP = currenthp;
        AC = ac;
        STR = str;
        DEX = dex;
        CON = con;
        INT = inte;
        WIS = wis;
        CHA = cha;
        speed = spEEd;
        initiative = init;
        STRThrow = strthrow;
        DEXThrow = dexthrow;
        CONThrow = conthrow;
        INTThrow = intthrow;
        WISThrow = wisthrow;
        CHAThrow = chathrow;
    }

    public void toText(String fileName) {//uses a file writer to dump all info into a txt doc
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Level = " + Level);
            pw.println("MaxHP = " + MaxHP);
            pw.println("CurrentHP = " + CurrentHP);
            pw.println("AC = " + AC);
            pw.println("STR = " + STR);
            pw.println("DEX = " + DEX);
            pw.println("CON = " + CON);
            pw.println("INT = " + INT);
            pw.println("WIS = " + WIS);
            pw.println("CHA = " + CHA);
            pw.println("speed = " + speed);
            pw.println("initiative = " + initiative);
            pw.println("Acrobatics = " + Acrobatics);
            pw.println("AnimalHandling = " + AnimalHandling);
            pw.println("Arcana = " + Arcana);
            pw.println("Athletics = " + Athletics);
            pw.println("Deception = " + Deception);
            pw.println("History = " + History);
            pw.println("Insight = " + Insight);
            pw.println("Intimidation = " + Intimidation);
            pw.println("Investigation = " + Investigation);
            pw.println("Medicine = " + Medicine);
            pw.println("Nature = " + Nature);
            pw.println("Perception = " + Perception);
            pw.println("Performance = " + Performance);
            pw.println("Persuasion = " + Persuasion);
            pw.println("Religion = " + Religion);
            pw.println("SleightOfHand = " + SleightOfHand);
            pw.println("Stealth = " + Stealth);
            pw.println("Survival = " + Survival);
            pw.println("STR Throw = " + STRThrow);
            pw.println("DEX Throw = " + DEXThrow);
            pw.println("CON Throw = " + CONThrow);
            pw.println("INT Throw = " + INTThrow);
            pw.println("WIS Throw = " + WISThrow);
            pw.println("CHA Throw = " + CHAThrow);
            pw.println("Race = " + Race);
            pw.println("Class = " + Class);
            pw.println("Name = " + Name);
            switch (Size) {
                case 1:
                    pw.println("Size = Tiny");
                    break;
                case 2:
                    pw.println("Size = Small");
                    break;
                case 3:
                    pw.println("Size = Medium");
                    break;
                case 4:
                    pw.println("Size = Large");
                    break;
                case 6:
                    pw.println("Size = Huge");
                    break;
                case 7:
                    pw.println("Size = Gargantuan");
                    break;
                default:
                    pw.println("Size = ?");
                    break;

            }

            pw.close();
        } catch (IOException e) {
            System.out.println("nope");
        }
    }

    public void fromText(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String str;
            String s = "";
            int counter = -1;
            while ((str = br.readLine()) != null) { //lines 1 -12 are ints 13-36 are booleans
                counter++;
                System.out.println(counter);
                str.replaceAll("\\s+", "");//remove whitespace
                boolean doit = false;//control over important stuff or naw
                for (int i = 0; i < str.length() - 1; i++) {//everything after = is important
                    if (str.charAt(i) == '=') {
                        doit = true;
                    }
                    if (doit) {
                        while (i < str.length() - 1) {
                            i++;
                            s = s + str.charAt(i);
                        }
                        //the .substring is to stop the whitespace in the string
                        switch (counter) {//assign to variables
                            case 0:
                                Level = parseInt(s.substring(1));
                                break;
                            case 1:
                                MaxHP = parseInt(s.substring(1));
                                break;
                            case 2:
                                CurrentHP = parseInt(s.substring(1));
                                break;
                            case 3:
                                AC = parseInt(s.substring(1));
                                break;
                            case 4:
                                STR = parseInt(s.substring(1));
                                break;
                            case 5:
                                DEX = parseInt(s.substring(1));
                                break;
                            case 6:
                                CON = parseInt(s.substring(1));
                                break;
                            case 7:
                                INT = parseInt(s.substring(1));
                                break;
                            case 8:
                                WIS = parseInt(s.substring(1));
                                break;
                            case 9:
                                CHA = parseInt(s.substring(1));
                                break;
                            case 10:
                                speed = parseInt(s.substring(1));
                                break;
                            case 11:
                                initiative = parseInt(s.substring(1));
                                break;
                            case 12:
                                Acrobatics = parseBoolean(s.substring(1));
                                break;
                            case 13:
                                AnimalHandling = parseBoolean(s.substring(1));
                                break;
                            case 14:
                                Arcana = parseBoolean(s.substring(1));
                                break;
                            case 15:
                                Athletics = parseBoolean(s.substring(1));
                                break;
                            case 16:
                                Deception = parseBoolean(s.substring(1));
                                break;
                            case 17:
                                History = parseBoolean(s.substring(1));
                                break;
                            case 18:
                                Insight = parseBoolean(s.substring(1));
                                break;
                            case 19:
                                Intimidation = parseBoolean(s.substring(1));
                                break;
                            case 20:
                                Investigation = parseBoolean(s.substring(1));
                                break;
                            case 21:
                                Medicine = parseBoolean(s.substring(1));
                                break;
                            case 22:
                                Nature = parseBoolean(s.substring(1));
                                break;
                            case 23:
                                Perception = parseBoolean(s.substring(1));
                                break;
                            case 24:
                                Performance = parseBoolean(s.substring(1));
                                break;
                            case 25:
                                Persuasion = parseBoolean(s.substring(1));
                                break;
                            case 26:
                                Religion = parseBoolean(s.substring(1));
                                break;
                            case 27:
                                SleightOfHand = parseBoolean(s.substring(1));
                                break;
                            case 28:
                                Stealth = parseBoolean(s.substring(1));
                                break;
                            case 29:
                                Survival = parseBoolean(s.substring(1));
                                break;
                            case 30:
                                STRThrow = parseBoolean(s.substring(1));
                                break;
                            case 31:
                                DEXThrow = parseBoolean(s.substring(1));
                                break;
                            case 32:
                                CONThrow = parseBoolean(s.substring(1));
                                break;
                            case 33:
                                INTThrow = parseBoolean(s.substring(1));
                                break;
                            case 34:
                                WISThrow = parseBoolean(s.substring(1));
                                break;
                            case 35:
                                CHAThrow = parseBoolean(s.substring(1));
                                break;
                            case 36:
                                Race = (s.substring(1));
                                break;
                            case 37:
                                Class = (s.substring(1));
                                break;
                            case 38:
                                Name = (s.substring(1));
                                break;
                            case 39:
                                switch (s.substring(1).toLowerCase()) {
                                    case "tiny":
                                        Size = 1;
                                        break;
                                    case "small":
                                        Size = 2;
                                        break;
                                    case "medium":
                                        Size = 3;
                                        break;
                                    case "large":
                                        Size = 4;
                                        break;
                                    case "huge":
                                        Size = 5;
                                        break;
                                    case "gargantuan":
                                        Size = 6;
                                        break;
                                    default:
                                        break;
                                }
                        }
                        s = "";
                    }
                }

            }
            br.close();

        } catch (IOException e) {
            System.out.println("Check file number!");
        }
    }

    public String getName() {
        return Name;
    }

    public String getCLASS() {
        return Class;
    }

    public String getRace() {
        return Race;
    }

    public int getSize() {
        return Size;
    }

    public int getLevel() {
        return Level;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public int getCurrentHP() {
        return CurrentHP;
    }

    public int getAC() {
        return AC;
    }

    public int getSTR() {
        return STR;
    }

    public int getDEX() {
        return DEX;
    }

    public int getCON() {
        return CON;
    }

    public int getINT() {
        return INT;
    }

    public int getWIS() {
        return WIS;
    }

    public int getCHA() {
        return CHA;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {
        return initiative;
    }

    public boolean getAcrobatics() {
        return Acrobatics;
    }

    public boolean getAnimalHandling() {
        return AnimalHandling;
    }

    public boolean getArcana() {
        return Arcana;
    }

    public boolean getAthletics() {
        return Athletics;
    }

    public boolean getDeception() {
        return Deception;
    }

    public boolean getHistory() {
        return History;
    }

    public boolean getInsight() {
        return Insight;
    }

    public boolean getIntimidation() {
        return Intimidation;
    }

    public boolean getInvestigation() {
        return Investigation;
    }

    public boolean getMedicine() {
        return Medicine;
    }

    public boolean getNature() {
        return Nature;
    }

    public boolean getPerception() {
        return Perception;
    }

    public boolean getPerformance() {
        return Performance;
    }

    public boolean getPersuasion() {
        return Persuasion;
    }

    public boolean getReligion() {
        return Religion;
    }

    public boolean getSleightOfHand() {
        return SleightOfHand;
    }

    public boolean getStealth() {
        return Stealth;
    }

    public boolean getSurvival() {
        return Survival;
    }

    public boolean getSTRThrow() {
        return STRThrow;
    }

    public boolean getDEXThrow() {
        return DEXThrow;
    }

    public boolean getCONThrow() {
        return CONThrow;
    }

    public boolean getINTThrow() {
        return INTThrow;
    }

    public boolean getWISThrow() {
        return WISThrow;
    }

    public boolean getCHAThrow() {
        return CHAThrow;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRace(String race) {
        Race = race;
    }

    public void setCLASS(String CLASS) {
        Class = CLASS;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void setLevel(int i) {
        Level = i;
    }

    public void setMaxHP(int i) {
        MaxHP = i;
    }

    public void setCurrentHP(int i) {
        CurrentHP = i;
    }

    public void setAC(int i) {
        AC = i;
    }

    public void setSTR(int i) {
        STR = i;
    }

    public void setDEX(int i) {
        DEX = i;
    }

    public void setCON(int i) {
        CON = i;
    }

    public void setINT(int i) {
        INT = i;
    }

    public void setWIS(int i) {
        WIS = i;
    }

    public void setCHA(int i) {
        CHA = i;
    }

    public void setSpeed(int i) {
        speed = i;
    }

    public void setInitiative(int i) {
        initiative = i;
    }

    public void setAcrobatics(boolean b) {
        Acrobatics = b;
    }

    public void setAnimalHandling(boolean b) {
        AnimalHandling = b;
    }

    public void setArcana(boolean b) {
        Arcana = b;
    }

    public void setAthletics(boolean b) {
        Athletics = b;
    }

    public void setDeception(boolean b) {
        Deception = b;
    }

    public void setHistory(boolean b) {
        History = b;
    }

    public void setInsight(boolean b) {
        Insight = b;
    }

    public void setIntimidation(boolean b) {
        Intimidation = b;
    }

    public void setInvestigation(boolean b) {
        Investigation = b;
    }

    public void setMedicine(boolean b) {
        Medicine = b;
    }

    public void setNature(boolean b) {
        Nature = b;
    }

    public void setPerception(boolean b) {
        Perception = b;
    }

    public void setPerformance(boolean b) {
        Performance = b;
    }

    public void setPersuasion(boolean b) {
        Persuasion = b;
    }

    public void setReligion(boolean b) {
        Religion = b;
    }

    public void setSleightOfHand(boolean b) {
        SleightOfHand = b;
    }

    public void setStealth(boolean b) {
        Stealth = b;
    }

    public void setSurvival(boolean b) {
        Survival = b;
    }

    public void getSTRThrow(boolean b) {
        STRThrow = b;
    }

    public void getDEXThrow(boolean b) {
        DEXThrow = b;
    }

    public void getCONThrow(boolean b) {
        CONThrow = b;
    }

    public void getINTThrow(boolean b) {
        INTThrow = b;
    }

    public void getWISThrow(boolean b) {
        WISThrow = b;
    }

    public void getCHAThrow(boolean b) {
        CHAThrow = b;
    }
}

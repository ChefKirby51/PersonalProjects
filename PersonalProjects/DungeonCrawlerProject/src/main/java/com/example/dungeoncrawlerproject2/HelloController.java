package com.example.dungeoncrawlerproject2;
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //Name: Nick Scribner
    //Program: Dungeon Crawler
    //Class: Intro to Programming (Java) - 2023FA-CIS-1500-O1536
    //Date: 12/11/2023
    ////////////////////////////////////////////////////////////////////////////////////////////////////
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.Random;

public class HelloController {
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //Control Attributes
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Button actionButtonFight;
    @FXML
    private Button actionButtonRun;
    @FXML
    private Button actionButtonSearch;
    @FXML
    private Button actionButtonSleep;
    @FXML
    private Button movementButtonNorth;
    @FXML
    private Button movementButtonEast;
    @FXML
    private Button movementButtonWest;
    @FXML
    private Button movementButtonSouth;
    @FXML
    private TextArea textAreaInformation;
    @FXML
    private TextArea labelPlayerStats;
    @FXML
    private TextArea labelNPCStats;

    NPC newMonster;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public int D6(){
        Random roll = new Random();
        return roll.nextInt(6 + 1);
    }

    public int D20(){
        Random roll = new Random();
        return roll.nextInt(20 + 1);
    }

    public boolean blockChecker(){
        Random roll = new Random();
        int blockRoll = roll.nextInt(10) + 1;
        return blockRoll == 1;
    }
    public boolean monsterChecker(){
        Random roll = new Random();
        int monsterRoll = roll.nextInt(2) + 1;
        return monsterRoll == 1;
    }
    public int goldChecker(){
        Random roll = new Random();
        return roll.nextInt(100) + 1;
    }
    Player newCharacter = new Player(20,D6() + D6() + D6(),
            D6() + D6() + D6(),D6() + D6() + D6(),0);
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //Map Movement and Initialization
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public int movementX = 0;
    public int movementY = 0;
    // This is the Arraylist that holds the rooms for the dungeon
    private ArrayList<ArrayList<Room>> map;
    public void initialize(){
        textAreaInformation.setEditable(false);
        map = new ArrayList<>();
            for (int row = 0; row < 10; row++) {
                map.add(new ArrayList<>());
                for (int column = 0; column < 10; column++) {
                    map.get(row).add(new Room(blockChecker(), monsterChecker(), goldChecker()));
                }
            }
            map.get(movementX).get(movementY);
            textAreaInformation.appendText("You are currently at (" + movementX + ", " + movementY + ")\n");
            labelPlayerStats.appendText("Health Points: " + newCharacter.getHealthPoints() + "\n");
            labelPlayerStats.appendText("\n");
            labelPlayerStats.appendText("Strength: " + newCharacter.getStrength() + "\n");
            labelPlayerStats.appendText("\n");
            labelPlayerStats.appendText("Dexterity: " + newCharacter.getDexterity() + "\n");
            labelPlayerStats.appendText("\n");
            labelPlayerStats.appendText("Intelligence: " + newCharacter.getIntelligence() + "\n");
            labelPlayerStats.appendText("\n");
            labelPlayerStats.appendText("Total Gold: " + newCharacter.getTotalGold() + "\n");
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Action Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fight Method
    public void Fight() {
        D20();
        int playerDamage;
        int npcDamage;
        if (D20() >= newMonster.getDexterity()) {
            playerDamage = newCharacter.getStrength() / 3;
            newMonster.setMonsterHealth(newMonster.getMonsterHealth() - playerDamage);
            textAreaInformation.appendText("You've dealt " + playerDamage + " to the Monster\n");
            labelNPCStats.replaceText(0, 17, "Health Points: " + newMonster.getMonsterHealth());
            if (newMonster.getMonsterHealth() <= 0) {
                movementButtonNorth.setDisable(false);
                movementButtonSouth.setDisable(false);
                movementButtonEast.setDisable(false);
                movementButtonWest.setDisable(false);
                actionButtonSearch.setDisable(false);
                actionButtonSleep.setDisable(false);
                newMonster.setMonsterHealth(0);
                textAreaInformation.appendText("The monster is slain. You can continue your adventure. \n");
                actionButtonFight.setDisable(true);
                labelNPCStats.replaceText(0, 17, "Health Points: " + newMonster.getMonsterHealth());
            }
        } else {
            textAreaInformation.appendText("You missed....\n");
        }
        if (newMonster.getMonsterHealth() > 0) {
            D20();
            if (D20() >= newCharacter.getDexterity()) {
                npcDamage = newMonster.getStrength() / 3;
                newCharacter.setHealthPoints(newCharacter.getHealthPoints() - npcDamage);
                textAreaInformation.appendText("The Monster has dealt " + npcDamage + " to you\n");
                labelPlayerStats.replaceText(0,17,"Health Points: " + newCharacter.getHealthPoints());
            } else {
                textAreaInformation.appendText("The monster missed!");
            }
        }
    }

    // Search Method
    public void Search(){
        goldChecker();
        D20();
            if (D20() < newCharacter.getIntelligence()){
                textAreaInformation.appendText("You've found gold!");
                labelPlayerStats.replaceText(0, 14, "Total Gold: " + newCharacter.getTotalGold() + "\n");
            } else {
                textAreaInformation.appendText("You couldn't find anything....");
            }
        }

    // Run Away Method
    public void RunAway(){
        newMonster = new NPC(D6(),D6() + D6(),D6() + D6(),D6() + D6());
        int damage;
            D20();
        if (D20() < newMonster.getIntelligence()){
            damage = newMonster.getStrength() / 3;
            if (damage < 0) damage = 1;
            newCharacter.setHealthPoints(newCharacter.getHealthPoints() - damage);
            textAreaInformation.appendText("The monster saw you! You take " + damage + " damage as you escape.\n");
        } else {
            textAreaInformation.appendText("You escaped from the monster!\n");
        }
        movementButtonNorth.setDisable(false);
        movementButtonSouth.setDisable(false);
        movementButtonEast.setDisable(false);
        movementButtonWest.setDisable(false);
        actionButtonSearch.setDisable(false);
        actionButtonSleep.setDisable(false);
    }

    // Sleep Method
    public void Sleep(){
        D6();
        int npcDamage;
        if (D6() == 1){
            textAreaInformation.appendText("A monster has awoken you from your sleep, you must fight");
            newMonster = new NPC(D6(), D6() + D6(), D6() + D6(), D6() + D6());
            labelNPCStats.appendText("Health Points: " + newMonster.getMonsterHealth() + "\n");
            labelNPCStats.appendText("\n");
            labelNPCStats.appendText("Strength: " + newMonster.getStrength() + "\n");
            labelNPCStats.appendText("\n");
            labelNPCStats.appendText("Dexterity: " + newMonster.getDexterity() + "\n");
            labelNPCStats.appendText("\n");
            labelNPCStats.appendText("Intelligence: " + newMonster.getIntelligence() + "\n");
            labelNPCStats.appendText("\n");
            D20();
            if (D20() >= newCharacter.getDexterity()) {
                npcDamage = newMonster.getStrength() / 3;
                newCharacter.setHealthPoints(newCharacter.getHealthPoints() - npcDamage);
                textAreaInformation.appendText("The Monster has dealt " + npcDamage + " to you\n");
                labelPlayerStats.replaceText(0,17,"Health Points: " + newCharacter.getHealthPoints());
            } else {
                textAreaInformation.appendText("The monster missed!");
            }
            textAreaInformation.appendText("");
        } else {
            textAreaInformation.appendText("You sleep well and safe, and are restored to full HP\n");
            newCharacter.setHealthPoints(20);
            labelPlayerStats.replaceText(0,17,"Health Points: " + newCharacter.getHealthPoints());
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //Action Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void fightButtonClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == actionButtonFight){
            Fight();
        }
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == actionButtonSearch){
            textAreaInformation.appendText("You Searched for some gold\n");
            Search();
        }
    }

    @FXML
    public void runButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == actionButtonRun){
            RunAway();
        }
    }
    @FXML
    public void sleepButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == actionButtonSleep){
            Sleep();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //Movement methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void northButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == movementButtonNorth) {
            map.get(movementX).get(movementY - 1);
            if (map.get(movementX).get(movementY).isBlocked()) {
                textAreaInformation.appendText("You cannot go that way\n");
            } else {
                movementY -= 1;
                textAreaInformation.appendText("You are currently at (" + movementX + ", " + movementY + ")\n");
            }
            if (map.get(movementX).get(movementY).isHasMonster()) {
                    newMonster = new NPC(D6(), D6() + D6(), D6() + D6(), D6() + D6());
                    textAreaInformation.appendText("There is a monster in the area, Fight or Flee!\n");
                    labelNPCStats.appendText("Health Points: " + newMonster.getMonsterHealth() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Strength: " + newMonster.getStrength() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Dexterity: " + newMonster.getDexterity() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Intelligence: " + newMonster.getIntelligence() + "\n");
                    labelNPCStats.appendText("\n");
                    movementButtonNorth.setDisable(true);
                    movementButtonSouth.setDisable(true);
                    movementButtonEast.setDisable(true);
                    movementButtonWest.setDisable(true);
                    actionButtonSearch.setDisable(true);
                    actionButtonSleep.setDisable(true);
                    actionButtonFight.setDisable(false);
            }
        }
        if (blockChecker()) {
            movementButtonNorth.setDisable(true);
        }
        movementButtonNorth.setDisable(false);
    }
    @FXML
    public void southButtonClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == movementButtonSouth) {
            map.get(movementX).get(movementY + 1);
            if (map.get(movementX).get(movementY).isBlocked()){
                textAreaInformation.appendText("You cannot go that way\n");
            } else {
                movementY += 1;
                textAreaInformation.appendText("You are currently at (" + movementX + ", " + movementY + ")\n");
            }
            if (map.get(movementX).get(movementY).isHasMonster()){
                    newMonster = new NPC(D6(), D6() + D6(), D6() + D6(), D6() + D6());
                    textAreaInformation.appendText("There is a monster in the area, Fight or Flee!\n");
                    labelNPCStats.appendText("Health Points: " + newMonster.getMonsterHealth() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Strength: " + newMonster.getStrength() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Dexterity: " + newMonster.getDexterity() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Intelligence: " + newMonster.getIntelligence() + "\n");
                    labelNPCStats.appendText("\n");
                    movementButtonNorth.setDisable(true);
                    movementButtonSouth.setDisable(true);
                    movementButtonEast.setDisable(true);
                    movementButtonWest.setDisable(true);
                    actionButtonSearch.setDisable(true);
                    actionButtonSleep.setDisable(true);
                    actionButtonFight.setDisable(false);
            }
        }
        if (blockChecker()){
            movementButtonSouth.setDisable(true);
        }
        movementButtonNorth.setDisable(false);
    }
    @FXML
    public void eastButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == movementButtonEast){
            map.get(movementX + 1).get(movementY);
            if (map.get(movementX).get(movementY).isBlocked()){
                textAreaInformation.appendText("You cannot go that way\n");
            } else {
                movementX += 1;
                textAreaInformation.appendText("You are currently at (" + movementX + ", " + movementY + ")\n");
            }
            if (map.get(movementX).get(movementY).isHasMonster()) {
                    newMonster = new NPC(D6(), D6() + D6(), D6() + D6(), D6() + D6());
                    textAreaInformation.appendText("There is a monster in the area, Fight or Flee!\n");
                    labelNPCStats.appendText("Health Points: " + newMonster.getMonsterHealth() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Strength: " + newMonster.getStrength() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Dexterity: " + newMonster.getDexterity() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Intelligence: " + newMonster.getIntelligence() + "\n");
                    labelNPCStats.appendText("\n");
                    movementButtonNorth.setDisable(true);
                    movementButtonSouth.setDisable(true);
                    movementButtonEast.setDisable(true);
                    movementButtonWest.setDisable(true);
                    actionButtonSearch.setDisable(true);
                    actionButtonSleep.setDisable(true);
                    actionButtonFight.setDisable(false);
            }
        }
        if (blockChecker()){
            movementButtonEast.setDisable(true);
        }
        movementButtonEast.setDisable(false);
    }
    @FXML
    public void westButtonClicked(ActionEvent actionEvent){
        if (actionEvent.getSource() == movementButtonWest) {

            map.get(movementX - 1).get(movementY);
            if (map.get(movementX).get(movementY).isBlocked()){
                textAreaInformation.appendText("You cannot go that way\n");
            } else {
                movementX -= 1;
                textAreaInformation.appendText("You are currently at (" + movementX + ", " + movementY + ")\n");
            }
            if (map.get(movementX).get(movementY).isHasMonster()) {
                    newMonster = new NPC(D6(), D6() + D6(), D6() + D6(), D6() + D6());
                    textAreaInformation.appendText("There is a monster in the area, Fight or Flee!\n");
                    labelNPCStats.appendText("Health Points: " + newMonster.getMonsterHealth() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Strength: " + newMonster.getStrength() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Dexterity: " + newMonster.getDexterity() + "\n");
                    labelNPCStats.appendText("\n");
                    labelNPCStats.appendText("Intelligence: " + newMonster.getIntelligence() + "\n");
                    labelNPCStats.appendText("\n");
                    movementButtonNorth.setDisable(true);
                    movementButtonSouth.setDisable(true);
                    movementButtonEast.setDisable(true);
                    movementButtonWest.setDisable(true);
                    actionButtonSearch.setDisable(true);
                    actionButtonSleep.setDisable(true);
                    actionButtonFight.setDisable(false);
            }
        }
        if (blockChecker()){
            movementButtonWest.setDisable(true);
        }
        movementButtonEast.setDisable(false);
    }


} //Be sure to double-check names for methods, they usually don't show in errors
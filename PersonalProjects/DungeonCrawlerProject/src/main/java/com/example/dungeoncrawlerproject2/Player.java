package com.example.dungeoncrawlerproject2;

import java.util.Random;

public class Player {
    // Attributes
    private int healthPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int totalGold;

    // Player constructor
    public Player(int healthPoints, int strength, int dexterity, int intelligence, int totalGold) {
        this.healthPoints = 20;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.totalGold = 0;
    }

    // Dice roller Method that generates a number from 1-6


    // Seeing as every stat can be modified, all attributes get setters alongside their getters
    public int getHealthPoints() { return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        if (healthPoints > 20){
            this.healthPoints = 20;
        }
    }

    public int getStrength() { return strength;
    }

    public int getDexterity() { return dexterity ;
    }

    public int getIntelligence() { return intelligence ;
    }

    public int getTotalGold() { return totalGold;
    }
    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

}

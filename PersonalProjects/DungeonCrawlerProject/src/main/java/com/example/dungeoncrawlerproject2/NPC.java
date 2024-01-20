package com.example.dungeoncrawlerproject2;

import java.util.Random;
public class NPC {
    // NPC (or Monster) attributes
    private int monsterHealth;
    private int strength;
    private int dexterity;
    private int intelligence;

    // Same dice roller from the Player class


    //NPC Constructor
    public NPC(int monsterHealth, int strength, int dexterity, int intelligence) {
        this.monsterHealth = monsterHealth;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    // NPC getters
    public int getMonsterHealth() { return monsterHealth; }
    public void setMonsterHealth(int NPCHealth) { this.monsterHealth = NPCHealth;
    }

    public int getStrength() { return strength;
    }

    public int getDexterity() { return dexterity;
    }

    public int getIntelligence() { return intelligence;
    }
}

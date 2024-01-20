package com.example.dungeoncrawlerproject2;

public class Tester {
    public static void main(String[] args) {
        /*
        This will be where the Dungeon Menu will take place, I think.
        This class will also carry the methods for Searching rooms, Fighting, Running away and Sleeping.
         */

        // Player Statistics tester
        Player newCharacter = new Player(0, 0, 0, 0, 0);

        System.out.println("Your starting health points are " + newCharacter.getHealthPoints());
        System.out.println("Your character's Strength is " + newCharacter.getStrength());
        System.out.println("Your character's Dexterity is " + newCharacter.getDexterity());
        System.out.println("Your character's Intelligence is " + newCharacter.getIntelligence());
        System.out.println("And you start of with " + newCharacter.getTotalGold() + " gold. Good Luck!");

        System.out.println();

        // NPC stats tester
        NPC monster = new NPC(0, 0, 0, 0);

        System.out.println("The Enemy before you has " + monster.getMonsterHealth() + " Health.");
        System.out.println("It has " + monster.getStrength() + " strength, " + monster.getDexterity() + " dexterity"
                + " and " + monster.getIntelligence() + " intelligence.");

        /*
        NPC newMonster = new NPC(D6(),D6() + D6(), D6() + D6(),D6() + D6());
        int damage;
        D6();
        D20();
        if (monsterChecker() == true)
        //First the player
            if (D20() >= newMonster.getDexterity()) {
                damage = newCharacter.getStrength() / 3;
                // In case damage points go into the decimals.
                if (damage < 1) damage = 1;
                newMonster.setMonsterHealth(newMonster.getMonsterHealth() - damage);
                textAreaInformation.appendText("Player hits, dealing " + damage + " points of damage.\n");
            } else {
                textAreaInformation.appendText("That's a miss...\n");
            }
            // Now the Monster
            if (monsterTurn.getMonsterHealth() > 0) {
                if (monsterRoll >= newCharacter.getDexterity()) {
                    damage = newMonster.getStrength() / 3;
                    if (damage < 1) damage = 1;
                    newCharacter.setHealthPoints(newCharacter.getHealthPoints() - damage);
                    textAreaInformation.appendText("You've been hit with " + damage + " points of damage.\n");
                } else {
                    textAreaInformation.appendText("no hit! that was close.\n");
                }
            }
            if (monsterTurn.getMonsterHealth() < 0) {
                textAreaInformation.appendText("The Monster is slain. You may search for loot, sleep or leave.\n");
            }
         */

    }
}

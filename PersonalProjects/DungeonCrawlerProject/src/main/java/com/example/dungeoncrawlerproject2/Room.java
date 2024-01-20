package com.example.dungeoncrawlerproject2;

public class Room {

    private boolean isBlocked;

    private boolean hasMonster;

    private int goldToFind;

    public Room(boolean isBlocked, boolean hasMonster, int goldToFind) {
        this.isBlocked = isBlocked;
        this.hasMonster = hasMonster;
        this.goldToFind = goldToFind;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isHasMonster() {
        return hasMonster;
    }

    public int getGoldToFind() {
        return goldToFind;
    }

    public void setGoldToFind(int goldToFind) {
        this.goldToFind = goldToFind;
    }
}

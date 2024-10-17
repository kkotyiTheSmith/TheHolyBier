import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class Player extends GCharacter {
    Item[] items;
    Heal[] heals;

    protected long maxHp;
    protected long maxArmor;
    protected long armor;
    protected long nextLevel;
    protected long currXp;
    protected long gold;

    long getMaxHp() {
        return maxHp;
    }

    long getMaxArmor() {
        return maxArmor;
    }

    long getArmor() {
        return armor;
    }

    long getNextLevel() {
        return nextLevel;
    }

    long getCurrXp() {
        return currXp;
    }

    long getCurrentDamage(Item item) { //TODO J- make a specialization of f so it takes into conideration
        return damage;                 //passives, weaknesses and the weapon the player atacks with.
    }

    public Player() {

        items = new Item[3];
        items[0] = new Hand();
        items[1] = new Hand();
        items[2] = new Hand();

        heals = new Heal[3];
        heals[0] = new EmptyBottle();
        heals[1] = new EmptyBottle();
        heals[2] = new EmptyBottle();

        level = 1;
        hp = 250;
        maxHp = hp + items[0].hp + items[1].hp + items[2].hp;
        health = maxHp;
        armor = 250;
        maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;
        damage = 250;

        currXp = 0;
        nextLevel = Math.round((Math.pow(Math.E, level)-1) * Math.pow(10,4) / 75);
        gold = 0;

        
    }

    void changeItem(int n, Item i) {
        items[n] = i;
    }

    Item getItem(int slot) {
        return items[slot];
    }

    Heal getHeal(int slot) {
        return heals[slot];
    }

    void playerReward(long xpWorth, long goldWorth) {
        boolean lvlUp = false;
        long xp = xpWorth;
        long oldHp = hp;

        currXp = currXp + xp;
        while (currXp >= nextLevel) {
            xp = xp - (nextLevel- currXp);
            level = level  + 1;
            nextLevel = Math.round((Math.pow(Math.E, level)-1) * Math.pow(10,4) / 75);
            currXp = 0;
            lvlUp = true;
        }
        
        if (lvlUp) {
            currXp = xp;

            hp = Math.round((Math.pow(Math.E, 0.05 * (level+5)) - 1) * Math.pow(10,4));
            armor = Math.round((Math.pow(Math.E, 0.05 * (level+3)) - 1) * Math.pow(10,4) / 10);
            damage = Math.round((Math.pow(Math.E, 0.05 * (level)) - 1) * Math.pow(10,4));
            maxHp = hp + items[0].hp + items[1].hp + items[2].hp;
            // health = health + ((hp-oldHp) / 4);
            maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;

        }
        gold = gold + goldWorth;

    }

        //overriting (dont remember name of this in OOP)
        void lowerHp(long dmg) {
            long realDamage = dmg - maxArmor;
            if (realDamage < 0){
                realDamage = 0;

            }
            
            this.health -= realDamage;
            if (this.health < 0) {
                this.health = 0;
            }
        }
}
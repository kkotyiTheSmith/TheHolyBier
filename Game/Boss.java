import java.util.Random;
import java.io.*;
import java.util.*;
import java.lang.Math.*;

class Boss extends GCharacter{
    String name;
    long xpWorth;
    long coinWorth;

    public Boss() { // TODO for J - Generate a new random boss here.

        Random rand = new Random();

        this.name = "Test Boss";
        this.level = 1; //getRoomLevel(); // Have to create a function which returns the level of the room the boss is in;
                        // or just a variable that can be accesed by all programs.

        int randNum = rand.nextInt(10) + 100;
        this.hp = Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10,4) / 75);
        this.health = this.hp;

        randNum = rand.nextInt(10) + 100;
        this.damage = Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10,4) / 75);

        this.xpWorth = (this.damage + this.hp) / 2;
        this.coinWorth = 10;

        if (level % 20 == 0){
            this.hp = this.hp * 2;
            this.damage = this.damage * 2;
            this.xpWorth = this.xpWorth * 3;
            this.coinWorth = this.coinWorth * 3;
        }
    }

}

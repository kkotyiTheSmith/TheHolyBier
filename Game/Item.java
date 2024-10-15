import java.io.*;
import java.util.*;
import java.lang.Math.*;

abstract class Item {
    String name;
    String type;
    int level;
    long hp;
    long armor;
    long damage;
    int cost;
    boolean unique;
    float critChance;

    Boolean oneShot = false;
    Boolean damageNull = false;
    Boolean doubleDmg = false;
    Boolean lifeSteal = false;

    String getName() {
        return this.name;
    }

    int getLevel() {
        return level;
    }

    long getDamage() {
        return damage;
    }

    int getCost() {
        return cost;
    }

    float getCritChance() {
        return critChance;
    }

    Item generateNewhand() {
            Random rand = new Random();
            Boolean isUnique = false;


            Item hand = new Hand();
            Item uniqueHand = new UniqueHand();

            hand.unique = false;
            uniqueHand.unique = true;

            String[] handTypeArray = new String[]{"sword","axe","grenade","spear"};
            int randNum = rand.nextInt(4);
            String handType = handTypeArray[randNum];
    
            randNum = rand.nextInt(100)+1;
    
            if (handType.equals("sword")) {
    
                hand.name = "Sword";
    
                randNum = rand.nextInt(51) + 100;
                hand.hp = Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 100;
                hand.armor = Math.round(((Math.pow(Math.E, level)-1)/ 75)*randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 100;                
                hand.damage = Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10 , 4) / 75);

            } else if (handType.equals("axe")) {
    
                hand.name = "Axe";
    
                randNum = rand.nextInt(51) + 125;
                hand.hp = Math.round((Math.pow(Math.E + (Math.E / 4), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 75;
                hand.armor = Math.round((Math.pow(Math.E - (Math.E / 2), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 125;
                hand.damage = Math.round((Math.pow(Math.E + (Math.E / 4), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
    
            } else if (handType.equals("grenade")) {
    
                hand.name = "Grenade";
    
                randNum = rand.nextInt(51) + 50;
                hand.hp = Math.round((Math.pow(Math.E - (Math.E / 2), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 50;
                hand.armor = Math.round((Math.pow(Math.E - (Math.E / 2), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 200;
                hand.damage = Math.round((Math.pow(Math.E * 2, 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                
            } else {
    
                hand.name = "Spear";
    
                randNum = rand.nextInt(51) + 125;
                hand.hp = Math.round((Math.pow(Math.E + (Math.E / 4), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 125;
                hand.armor = Math.round((Math.pow(Math.E + (Math.E / 4), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
                randNum = rand.nextInt(51) + 50;
                hand.damage = Math.round((Math.pow(Math.E - (Math.E / 2), 0.05 * level)-1) * randNum * Math.pow(10,4) / 75);
        
            }
    
            hand.type = handType;
            hand.level = level;
            
            if (randNum >= 76 && randNum <= 95) {
                hand.hp = hand.hp * 2;
                hand.armor = hand.armor * 2;
                hand.damage = hand.damage * 2;
            } else if(randNum >= 6 && randNum <= 99) {
                hand.hp = hand.hp * 3;
                hand.armor = hand.armor * 3;
                hand.damage = hand.damage * 3;
            } else if (randNum == 100) {
    
                isUnique = true;
    
                uniqueHand.type = handType;
                uniqueHand.level = level;
    
                uniqueHand.hp = hand.hp * 5;
                uniqueHand.armor = hand.armor * 5;
                uniqueHand.damage = hand.damage * 5;
    
                if (handType.equals("sword")) {
                    uniqueHand.name = "UniqueSword";
                    uniqueHand.lifeSteal = true;
                } else if (handType.equals("axe")) {
                    uniqueHand.name = "UniqueAxe";
                    uniqueHand.doubleDmg = true;
                } else if (handType.equals("grenade")) {
                    uniqueHand.name = "UniqueGrenade";
                    uniqueHand.oneShot = true;
                } else {
                    uniqueHand.name = "UniqueSpear";
                    uniqueHand.damageNull = true;
                }
            }   
            if (isUnique) {
                return uniqueHand;
            } else {
                return hand;
            }
    }
}

class Hand extends Item {
    public Hand() {
        name = "Hand";
        type = "None";
        level = 0;
        hp = 0;
        armor = 0;
        damage = 10;
        cost = 0;
        unique = false;
        critChance = 0.1f;

    }
}

class UniqueHand extends Item {

    public UniqueHand() {
        name = "Hand";
        cost = 0;
        level = 0;
        damage = 10;
        critChance = 0.1f;
        
        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;
    }
}


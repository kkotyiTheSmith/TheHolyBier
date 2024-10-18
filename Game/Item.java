import java.util.*;

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

    Boolean oneShot;
    Boolean damageNull;
    Boolean doubleDmg;
    Boolean lifeSteal;

    long getHp() {
        return hp;
    }

    long getArmor() {
        return armor;
    }

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

    int rarityCalc() {
        
        Random rand = new Random();
        int randNum = rand.nextInt(100)+1;

        if (randNum >= 76 && randNum <= 95) {

            hp = hp * 16;
            armor = armor * 16 ;
            damage = damage * 16;
            critChance = critChance * 2;

        } else if(randNum >= 96 && randNum <= 99) {

            hp = hp * 256;
            armor = armor * 256;
            damage = damage * 256;
            critChance = critChance * 3;

        } else if (randNum == 100) {
            hp = hp * 4000;
            armor = armor * 4000;
            damage = damage * 4000;
            critChance = critChance * 5;

        }
        return randNum;
    }
}

class Hand extends Item {
    public Hand() {
        name = "Hand";
        type = "None";
        level = 1;
        hp = 0;
        armor = 0;
        damage = 10;
        cost = 0;
        unique = false;
        critChance = 0.1f;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

    }
}

class Sword extends Item {
    public Sword() {
        name = "Sword";
        type = "sword";
        level = 1; // create variable that keeps track of levels in main program for this
        unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(11) + 100;
        hp = Math.round((Math.pow(Math.E, 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 100;
        armor = Math.round((Math.pow(Math.E, 0.05 * level) - 1) *randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 100;                
        damage = Math.round((Math.pow(Math.E, 0.05 * level) - 1) * randNum * Math.pow(10 , 4) / 75);

        cost = 0;
        critChance = 0.1f;

        randNum = rarityCalc();

        if (randNum == 100) {
            unique = true;
            name = "uniqueSword";
            lifeSteal = true;
        }

            
    }
}

class Axe extends Item {
    public Axe() {
        name = "Axe";
        type = "axe";
        level = 1; // create variable that keeps track of levels in main program for this
        unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(11) + 125;
        hp = Math.round((Math.pow(Math.E + (Math.E * 0.01), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 75;
        armor = Math.round((Math.pow(Math.E - (Math.E * 0.02), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 125;
        damage = Math.round((Math.pow(Math.E + (Math.E * 0.01), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);

        cost = 0;
        critChance = 0.1f;

        randNum = rarityCalc();

        if (randNum == 100) {
            unique = true;
            name = "uniqueAxe";
            doubleDmg = true;
        }
    }
}

class Grenade extends Item {
    public Grenade() {
        name = "Grenade";
        type = "grenade";
        level = 1; // create variable that keeps track of levels in main program for this
        unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(11) + 50;
        hp = Math.round((Math.pow(Math.E - (Math.E * 0.02), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 50;
        armor = Math.round((Math.pow(Math.E - (Math.E *0.02), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 200;
        damage = Math.round((Math.pow(Math.E + (Math.E * 0.04), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);

        cost = 0;
        critChance = 0.1f;

        randNum = rarityCalc();

        if (randNum == 100) {
            unique = true;
            name = "uniqueGrenade";
            oneShot = true;
        }
    }
}

class Spear extends Item {
    public Spear() {
        name = "Spear";
        type = "spear";
        level = 1; // create variable that keeps track of levels in main program for this
        unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(11) + 125;
        hp = Math.round((Math.pow(Math.E + (Math.E  * 0.01), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 125;
        armor = Math.round((Math.pow(Math.E + (Math.E * 0.01), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);
        randNum = rand.nextInt(11) + 50;
        damage = Math.round((Math.pow(Math.E - (Math.E * 0.02), 0.05 * level) - 1) * randNum * Math.pow(10,4) / 75);

        cost = 0;
        critChance = 0.1f;

        if (randNum == 100) {
            unique = true;
            name = "uniqueSpear";
            damageNull = true;
        }
    }
}
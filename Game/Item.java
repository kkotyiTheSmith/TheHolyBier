import java.util.*;

abstract class Item {
    String name;
    //String type;
    int level;
    long hp;
    long armor;
    long damage;
    int cost = 10;
    //boolean unique;
    int CRIT = 10;

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

    final int rarityCalc() {
        
        Random rand = new Random();
        int randNum = rand.nextInt(100)+1;

        int extraLevel = 0;

        if (randNum >= 76 && randNum <= 95) {

            name = name + " 2";
            extraLevel = 10;

        } else if(randNum >= 96 && randNum <= 99) {

            name = name + " 3";
            extraLevel = 20;

        } else if (randNum == 100) {
            
            extraLevel = 30;

        }
        return extraLevel;
    }
}

class Hand extends Item {
    public Hand() {
        name = "Hand";
        //type = "None";
        level = 1;
        hp = 0;
        armor = 0;
        damage = 10;
        cost = 0;
        //unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

    }
}

class Sword extends Item {
    public Sword() {
        name = "Sword";
        //type = "sword";
        level = Controller.level; // create variable that keeps track of levels in main program for this
        //unique = false;


        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        int extraLevel = rarityCalc();

        randNum = rand.nextInt(11) + 100;
        hp = Math.round((Math.pow(Math.E, 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);
        armor = Math.round(hp*0.1);
        randNum = rand.nextInt(11) + 100;                
        damage = Math.round((Math.pow(Math.E, 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10 , 4)* 0.5 / 75);



        if (extraLevel == 25) {
            //unique = true;
            name = "uniqueSword";
            lifeSteal = true;
        }

            
    }
}

class Axe extends Item {
    public Axe() {
        name = "Axe";
        //type = "axe";
        level = Controller.level; // create variable that keeps track of levels in main program for this
        //unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        int extraLevel = rarityCalc();

        randNum = rand.nextInt(11) + 125;
        hp = Math.round((Math.pow(Math.E + (Math.E * 0.01), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);
        armor = Math.round(hp*0.075);
        randNum = rand.nextInt(11) + 125;
        damage = Math.round((Math.pow(Math.E + (Math.E * 0.01), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4)*0.5 / 75);


        if (extraLevel == 25) {
            //unique = true;
            name = "uniqueAxe";
            doubleDmg = true;
        }
    }
}

class Grenade extends Item {
    public Grenade() {
        name = "Grenade";
        //type = "grenade";
        level = Controller.level; // create variable that keeps track of levels in main program for this
        //unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        int extraLevel = rarityCalc();

        randNum = rand.nextInt(11) + 50;
        hp = Math.round((Math.pow(Math.E - (Math.E * 0.08), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);
        armor = Math.round(hp*0.025);
        randNum = rand.nextInt(11) + 150;
        damage = Math.round((Math.pow(Math.E + (Math.E * 0.04), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);

        

        if (extraLevel == 25) {
            //unique = true;
            name = "uniqueGrenade";
            oneShot = true;
        }
    }
}

class Spear extends Item {
    public Spear() {
        name = "Spear";
        //type = "spear";
        level = Controller.level; // create variable that keeps track of levels in main program for this
        //unique = false;

        oneShot = false;
        damageNull = false;
        doubleDmg = false;
        lifeSteal = false;

        Random rand = new Random();
        int randNum;

        int extraLevel = rarityCalc();

        randNum = rand.nextInt(11) + 125;
        hp = Math.round((Math.pow(Math.E + (Math.E  * 0.01), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);
        //randNum = rand.nextInt(11) + 125;
        armor = Math.round(hp*0.15);
        randNum = rand.nextInt(11) + 50;
        damage = Math.round((Math.pow(Math.E - (Math.E * 0.02), 0.04 * (level + extraLevel)) - 1) * randNum * Math.pow(10,4) / 75);


        if (extraLevel == 25) {
            //unique = true;
            name = "uniqueSpear";
            damageNull = true;
        }
    }
}
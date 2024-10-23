import java.util.Random;

class Boss extends GCharacter{
    protected String name;
    /*protected*/int xpWorth;
    /*protected*/int coinWorth;

    /*
    String weak;
    String strong;
    */

    String getName() {
        return name;
    }

    int getXp() {
        return (int) xpWorth;
    }

    int getGold() {
        return (int) coinWorth;
    }

    /*
    String getWeakness() {
        return weak;
    }

    String getStrength() {
        return strong;
    }

    */

    void dealDamageTo(long dmg, Player character) {
        long totalDmg = dmg - character.getMaxArmor();
        if (totalDmg < 0) {
            totalDmg = 0;
        }
        character.lowerHp(totalDmg);
    }


    public Boss() {

        Random rand = new Random();

        level = Controller.level;
        name = "Boss" + level;
        /*
        weak = "sword";
        strong = "spear";
        */

        int randNum = rand.nextInt(10) + 200;
        hp = Math.round((Math.pow(Math.E, 0.04*level)-1) * randNum * Math.pow(10,4) * 1.75 /100);
        health = hp;

        randNum = rand.nextInt(10) + 200;
        damage = Math.round((Math.pow(Math.E, 0.05*level)-1) * randNum * Math.pow(10,4) / 250);

        xpWorth = Math.round((damage + hp) / 5);
        coinWorth = 10;

        if (level % 25 == 0 && !(level %  100 == 0)){
            name = name + " :|";
            hp = hp * 2;
            health = hp;
            damage = damage * 2;
            xpWorth = xpWorth * 2;
            coinWorth = coinWorth * 2;

        } else if (level %  100 == 0) {
            name = name + " }:(";
            hp = hp * 3;
            health = hp;
            damage = damage * 3;
            xpWorth = xpWorth * 2;
            coinWorth = coinWorth * 2;
        }
    }

}

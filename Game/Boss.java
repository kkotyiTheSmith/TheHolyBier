import java.util.Random;

class Boss extends GCharacter{
    protected String name;
    int xpWorth;
    int coinWorth;

    int getXp() {
        return (int) xpWorth;
    }

    int getGold() {
        return (int) coinWorth;
    }

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

        int randNum = rand.nextInt(10) + 200;
        hp = Math.round((Math.pow(Math.E, 0.04*level)-1) * randNum * Math.pow(10,4) * 1.75 /10000);
        health = hp;

        randNum = rand.nextInt(10) + 200;
        damage = Math.round((Math.pow(Math.E, 0.05*level)-1) * randNum * Math.pow(10,4) / 25000);

        xpWorth = Math.round((damage + hp) / 6);
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

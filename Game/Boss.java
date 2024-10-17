import java.util.Random;

class Boss extends GCharacter{
    String name;
    long xpWorth; // both should be int
    long coinWorth;

    String getName() {
        return name;
    }

    int getXp() {
        return (int) xpWorth;
    }

    int getGold() {
        return (int) coinWorth;
    }


    public Boss() { // TODO for J - Generate a new random boss here.

        Random rand = new Random();

        name = "Test Boss";
        level = 1; //getRoomLevel(); // Have to create a function which returns the level of the room the boss is in;
                        // or just a variable that can be accesed by all programs.

        int randNum = rand.nextInt(10) + 100;
        hp = Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10,4) / 75);
        health = 100;//hp;

        randNum = rand.nextInt(10) + 100;
        damage = 100;//Math.round((Math.pow(Math.E, level)-1) * randNum * Math.pow(10,4) / 75);

        xpWorth = (damage + hp) / 2;
        coinWorth = 10;

        if (level % 20 == 0){
            hp = hp * 2;
            damage = damage * 2;
            xpWorth = xpWorth * 3;
            coinWorth = coinWorth * 3;
        }
    }

}

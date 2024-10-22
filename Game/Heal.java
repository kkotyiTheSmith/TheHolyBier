import java.util.Random;

abstract class Heal {
    protected int cost;
    protected int healPercentage;
    protected int level;
    String name;

    int getCost() {
       return cost; 
    }

    int getHealPercentage() {
        return healPercentage;
    }
    long getHealAmount(long playerTotalHp) {
        long healAmount = playerTotalHp* healPercentage / 100;
        return healAmount;
    }

    Beer generateNewHeal() {
        return new Beer();
    }

    String getName() {
        return name;
    }

    int getLevel() {
        return level;
    }

}

class EmptyBottle extends Heal {
    public EmptyBottle() {
        this.cost = 0;
        this.healPercentage = 0;
        this.name = "Empty Bottle";
    }
}

class Beer extends Heal {
    public Beer() {

        Random rand = new Random();
        String[] nameArray = new String[]{
            "Ale",
            "Lager",
            "IPA",
            "Red Ale",
            "Stout",
            "Witbier",
            "Pilsner",
            "Brown Ale", 
            "Porter", 
            "Stout"
        };
        int randNum = rand.nextInt(10);

        name = nameArray[randNum];
        healPercentage = (rand.nextInt(10)+1) * 10;    
        cost = rand.nextInt(5)+1;
    }
}
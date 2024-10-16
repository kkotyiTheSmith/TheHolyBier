import java.util.Random;

abstract class Heal {
    protected int cost;
    protected int healAmount;
    String name;

    int getCost() {
       return cost; 
    }

    int getHealAmount() {
        return healAmount;
    }

    Heal generateNewHeal() {

        Random rand = new Random();
        String[] nameArray = new String[]{"1","2","3","4","5","6","7","8", "9", "10"};
        int randNum = rand.nextInt(10);
        Heal beer = new Beer();
        beer.name = nameArray[randNum];
        beer.healAmount = (rand.nextInt(6)+5) / 10;    
        beer.cost = rand.nextInt(5)+1;

        return beer;
    }
}

class EmptyBottle extends Heal {
    public EmptyBottle() {
        this.cost = 0;
        this.healAmount = 0;
    }
}

class Beer extends Heal {
    public Beer() {
        this.cost = 0;
        this.healAmount = 0;
    }
}
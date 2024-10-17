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

    Beer generateNewHeal() {
        return new Beer();
    }

    String getName() {
        return this.name;
    }
}

class EmptyBottle extends Heal {
    public EmptyBottle() {
        this.cost = 0;
        this.healAmount = 0;
        this.name = "EmptyBottle";
    }
}

class Beer extends Heal {
    public Beer() {

        Random rand = new Random();
        String[] nameArray = new String[]{"1","2","3","4","5","6","7","8", "9", "10"};
        int randNum = rand.nextInt(10);

        name = nameArray[randNum];
        healAmount = (rand.nextInt(6)+5) / 10;    
        cost = rand.nextInt(5)+1;
    }
}
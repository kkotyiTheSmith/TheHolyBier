abstract class Heal {
    protected int cost;
    protected int healAmount;

    int getCost() {
       return cost; 
    }

    int getHealAmount() {
        return healAmount;
    }
}

class EmptyBottle extends Heal {
    public EmptyBottle() {
        this.cost = 0;
        this.healAmount = 0;
    }
}
abstract class Heal {
    protected int cost;
    protected int healAmount;

    int getCost() {
       return cost; 
    }

    int getHealAmount() {
        return healAmount;
    }

    Heal generateNewHeal() {
        return new EmptyBottle(); // TODO for J - genereate a new random healing item here
    }
}

class EmptyBottle extends Heal {
    public EmptyBottle() {
        this.cost = 0;
        this.healAmount = 0;
    }
}
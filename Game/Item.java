abstract class Item {
    String name;
    int level;
    int cost;
    int damage;
    float critChance;

    String getName() {
        return this.name;
    }

    int getLevel() {
        return level;
    }

    int getDamage() {
        return damage;
    }

    int getCost() {
        return cost;
    }

    float getCritChance() {
        return critChance;
    }

    Item generateNewItem() {
        return new Hand(); // TODO for J - Here a random Item should be generated.
    }
}

class Hand extends Item {
    public Hand() {
        name = "Hand";
        cost = 0;
        level = 0;
        damage = 10;
        critChance = 0.1f;
    }
}

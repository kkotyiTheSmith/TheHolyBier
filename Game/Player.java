public class Player extends GCharacter {
    Item[] items;
    Heal[] heals;

    public Player() {
        level = 1;
        health = 100;
        damage = 10;

        items = new Item[3];
        items[0] = new Hand();
        items[1] = new Hand();
        items[2] = new Hand();

        heals = new Heal[3];
        heals[0] = new EmptyBottle();
        heals[1] = new EmptyBottle();
        heals[2] = new EmptyBottle();
    }

    void changeItem(int n, Item i) {
        items[n] = i;
    }

    Item getItem(int slot) {
        return items[slot];
    }

    Heal getHeal(int slot) {
        return heals[slot];
    }
}

public class Player extends GCharacter {
    Item[] items;

    public Player() {
        level = 1;
        health = 100;
        damage = 10;
        items = new Item[3];
        items[0] = new Hand();
        items[1] = new Hand();
        items[2] = new Hand();
    }

    void changeItem(int n, Item i) {
        items[n] = i;
    }

    Item getItem(int slot) {
        return items[slot];
    }
}

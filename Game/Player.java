
public class Player extends GCharacter {
    Item[] items;
    Heal[] heals;

    protected long maxHp;
    protected long armor;
    protected long nextLevel;
    protected long currXp;

    long getMaxHp() {
        return maxHp;
    }

    long getArmor() {
        return armor;
    }

    long getNextLevel() {
        return nextLevel;
    }

    long getCurrXp() {
        return currXp;
    }

    long getCurrentDamage(Item item) { //TODO J- make a specialization of f so it takes into conideration
        return damage;                 //passives, weaknesses and the weapon the player atacks with.
    }

    Item getItem(int n) {
        return items[n];
    }

    public Player() {
        level = 1;
        hp = 250;
        health = 250;
        damage = 250;

        items = new Item[3];
        items[0] = new Hand();
        items[1] = new Hand();
        items[2] = new Hand();

        maxHp = hp + items[0].hp + items[1].hp + items[2].hp;

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

    void changeHeal(int n, Heal h) {
        heals[n] = h;
    }

    //overriting (dont remember name of this in OOP)
    void lowerHp(int dmg) {
        this.health -= (dmg - (armor + items[0].armor + items[1].armor + items[2].armor));
        if (this.health < 0) {
            this.health = 0;
        }
    }
}
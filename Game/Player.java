
public class Player extends GCharacter {
    Item[] items;
    Heal[] heals;

    protected long maxHp;
    protected long maxArmor;
    protected long armor;
    protected long nextLevel;
    protected long currXp;
    protected int gold; // Changed to int, 

    long getMaxHp() { // No idea what this does
        return maxHp;
    }

    long getMaxArmor() { // ?
        return maxArmor;
    }

    long getArmor() { // ?
        return armor;
    }

    long getNextLevel() { // ?
        return nextLevel;
    }

    long getCurrXp() { // ?
        return currXp;
    }

    int getGold() {
        return gold;
    }
    
    long getCurrentDamage(Item item) { //TODO J- make a specialization of f so it takes into conideration
        return damage;                 //passives, weaknesses and the weapon the player atacks with.
    } 

    boolean decreaseGold(int n) {
        if (gold >= n) {
            gold -= n;
            return true;
        } else {
            return false;
        }

    }
    
    public Player() {

        items = new Item[3];
        items[0] = new Hand();
        items[1] = new Hand();
        items[2] = new Hand();

        heals = new Heal[3];
        heals[0] = new EmptyBottle();
        heals[1] = new EmptyBottle();
        heals[2] = new EmptyBottle();

        level = 1;
        health = 250;
        maxHp = health + items[0].hp + items[1].hp + items[2].hp;
        health = maxHp;
        armor = 250;
        maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;
        damage = 10;

        currXp = 0;
        nextLevel = Math.round((Math.pow(Math.E, level)-1) * Math.pow(10,4) / 75);
        gold = 0;        
    }

    void changeItem(int n, Item i) {
        items[n] = i;
    }

    void changeHeal(int n, Heal h) {
        heals[n] = h;
    }

    Item getItem(int slot) {
        return items[slot];
    }

    Heal getHeal(int slot) { 
        return heals[slot];
    }

    void increaseHealth() { // This stays this way!
        for (int i = 0; i < 3; i++) {
            if (heals[i].getHealAmount() > 0) {
                this.health += heals[i].getHealAmount();
                heals[i] = new EmptyBottle();
                System.out.println(heals[0] + "...." + heals[1] + "...." + heals[2]);
                break;
            }
        }
    }

    void increaseGold(int g){ // This already works!
        gold += g;
    }

    void incereaseXP(int xp) { // This already works!
        currXp += xp;
    }

    void playerReward(long xpWorth, int goldWorth) { // TODO
        boolean lvlUp = false;
        long xp = xpWorth;
        long oldHp = health;

        currXp = currXp + xp;
        while (currXp >= nextLevel) {
            xp = xp - (nextLevel- currXp);
            level = level  + 1;
            nextLevel = Math.round((Math.pow(Math.E, level)-1) * Math.pow(10,4) / 75);
            currXp = 0;
            lvlUp = true;
        }
        
        if (lvlUp) {
            currXp = xp;

            health = Math.round((Math.pow(Math.E, 0.05 * (level+5)) - 1) * Math.pow(10,4));
            armor = Math.round((Math.pow(Math.E, 0.05 * (level+3)) - 1) * Math.pow(10,4) / 10);
            damage = Math.round((Math.pow(Math.E, 0.05 * (level)) - 1) * Math.pow(10,4));
            maxHp = health + items[0].hp + items[1].hp + items[2].hp;
            // health = health + ((hp-oldHp) / 4);
            maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;

        }
        gold = gold + goldWorth;

    }
}
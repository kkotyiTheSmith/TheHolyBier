import java.util.*;

public class Player extends GCharacter {
    Item[] items;
    Heal[] heals;

    protected long maxHp; // Total (though max) amount of hp the player has when adding his and his weapons' hp.
    protected long maxArmor; // Total (though max) amount of armor the player has when adding his and his weapons' armor.
    protected long armor;
    protected long nextLevel; // Xp needed for the next level.
    protected long currXp; // Amount of Xp the player has.
    protected int gold;


    long getMaxHp() { 
        return maxHp;
    }
    void setMaxHp() {
        maxHp = hp + items[0].hp + items[1].hp + items[2].hp; 
    }

    long getMaxArmor() {
        return maxArmor;
    }

    void setMaxArmor() {
        maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;
    }

    long getDamage() {
        return damage;
    }

    long getArmor() { // Used when displaying stats
        return armor;
    }

    long getNextLevel() { // Used when displaying stats
        return nextLevel;
    }

    long getCurrXp() { // Used when displaying stats
        return currXp;
    }

    int getGold() {
        return gold;
    }
    
    //overriding
    long getCurrentDamage(Item item, Boss boss) {
        
        Random rand = new Random();
        int randNum;

        long totalDamage = item.getDamage();
        /*
        String weakness = boss.getWeakness();
        String strength = boss.getStrength();
        String itemType = item.getType();

        //Typing system
        if(strength.equalsIgnoreCase(itemType)){
            totalDamage = totalDamage * (2/3);
        } else if(weakness.equalsIgnoreCase(itemType)){
            totalDamage = totalDamage * (3/2);
        }
        */
        totalDamage = totalDamage + damage;

        //Unique Items mechanics
        if (item.doubleDmg){
            randNum = rand.nextInt(100)+1;
            if (randNum <= 80) {
                totalDamage = totalDamage*2;
            }

        } else if (item.oneShot){
            randNum = rand.nextInt(100)+1;
            if (randNum <= 20) {
                totalDamage = boss.getHp();
            }

        } else if (item.lifeSteal){
            health = Math.round(health + (totalDamage*0.25));
            if (health > maxHp) {
                health = maxHp;
            }
        }

        //Adding critical chance to the atack
        randNum = rand.nextInt(100)+1;

        if(randNum <= item.CRIT){
            totalDamage = totalDamage * (3/2);
        }
        return totalDamage;                 
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
        hp = Math.round((Math.pow(Math.E, 0.045 * (level+1)) - 1) * Math.pow(10,4)*2.5);;
        maxHp = hp + items[0].hp + items[1].hp + items[2].hp;
        health = maxHp;
        armor = Math.round(hp*0.1);
        maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;;
        damage = Math.round((Math.pow(Math.E, 0.04 * (level)) - 1) * Math.pow(10,4))*2;

        currXp = 0;
        nextLevel = Math.round((Math.pow(Math.E, 0.03*level)-1) * Math.pow(10,4) / 40);
        gold = 5;        
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

    void increaseHealth(long playerTotalHp) { // This stays this way!
        for (int i = 0; i < 3; i++) {
            if (heals[i].getHealAmount(playerTotalHp) > 0) {
                this.health += heals[i].getHealAmount(playerTotalHp);
                if (this.health > maxHp) {
                    this.health = maxHp;
                }

                heals[i] = new EmptyBottle();
                System.out.println(heals[0] + "...." + heals[1] + "...." + heals[2]);
                break;
            }
        }
    }
    /*
    void increaseGold(int g){ // This already works!
        gold += g;
    }

    void incereaseXP(int xp) { // This already works!
        currXp += xp;
    }
    */


    void playerReward(long xpWorth, int goldWorth) {
        boolean lvlUp = false;
        long xp = xpWorth;
        long oldHp = hp;
        
        // Use a while loop so the player keeps leveling up if it can gain more than one level with the amount of Xp gained.
        currXp = currXp + xp;
        while (currXp >= nextLevel) {
            currXp = xp - (nextLevel- currXp);
            level = level  + 1;
            nextLevel = Math.round((Math.pow(Math.E, 0.045*level)-1) * Math.pow(10,4)*1.2);
            lvlUp = true;
        }
        
        // Update player stats if the player leveled up
        if (lvlUp) {
            currXp = xp;

            hp = Math.round((Math.pow(Math.E, 0.045 * (level+1)) - 1) * Math.pow(10,4)*2.5);
            armor = Math.round(hp*0.05);
            damage = Math.round((Math.pow(Math.E, 0.04 * (level)) - 1) * Math.pow(10,4));
            maxHp = hp + items[0].hp + items[1].hp + items[2].hp;
            health = health + ((hp-oldHp)/2);
            if (health > maxHp) {
                health = maxHp;
            }
            maxArmor = armor + items[0].armor + items[1].armor + items[2].armor;

        }
        gold = gold + goldWorth;

    }
}
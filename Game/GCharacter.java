abstract class GCharacter {
    protected long level;
    protected long health;
    protected long hp;
    protected long damage;

    long getLevel() { 
        return level;
    }

    long getHp() { // used for dissplaying the hp of the boss 
        return hp;
    }

    long getHealth() { // used for dissplaying the health of the boss
        return health;
    }

    long getCurrentDamage() {
        return damage;
    }

    void lowerHp(long dmg) {
        this.health -= dmg; 
        if (this.health < 0) {
            this.health = 0;
        }
    }

    void dealDamageTo(long dmg, GCharacter c) {
        c.lowerHp(dmg);
    }

    

}




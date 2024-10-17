abstract class GCharacter {
    protected long level;
    protected long hp;
    protected long health;
    protected long damage;

    long getLevel() {
        return level;
    }

    long getHp() {
        return hp;
    }

    long getHealth() {
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
        c.lowerHp(this.getCurrentDamage());
    }

    void increaseHealth(long h) {
        this.health += h;
        if (this.health > hp) {
            this.health = hp;
        }
    }

}




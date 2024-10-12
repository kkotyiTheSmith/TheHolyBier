abstract class GCharacter {
    protected int level;
    protected int health;
    protected int damage;

    int getCurrentDamage() {
        return damage + level;
    }

    int getLevel() {
        return level;
    }

    int getHealth() {
        return health;
    }

    void lowerHp(int dmg) {
        this.health -= dmg;
    }

    void dealDamageTo(int dmg, GCharacter c) {
        c.lowerHp(this.getCurrentDamage());
    }

    void increaseHealt(int h) {
        this.health += h;
    }

}




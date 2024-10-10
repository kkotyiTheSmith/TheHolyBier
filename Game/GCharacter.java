abstract class GCharacter {
    protected String name;
    protected int level;
    protected int health;
    protected int damage;

    String getName() {
        return name;
    }

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
        c.health -= this.getCurrentDamage();
    }

}




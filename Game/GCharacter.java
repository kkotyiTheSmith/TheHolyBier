abstract class GCharacter {
    String name;
    int level;
    int health;
    int damage;

    int getCurrentDamage() {
        return damage + level;
    }

    void lowerHp(int dmg) {
        this.health -= dmg;
    }

    void dealDamageTo(int dmg, GCharacter c) {
        c.health -= this.getCurrentDamage();
    }

}




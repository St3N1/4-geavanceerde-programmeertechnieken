package dungeon_crawler.components;

public class HealthComponent extends Component {
    private int health;
    private int maxHealth;

    public HealthComponent(int health) {
        this.health = health;
        this.maxHealth = health;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int amount) {
        health -= amount;
        if (health < 0)
            health = 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth)
            health = maxHealth;
    }

    public boolean isDead() {
        return health <= 0;
    }
}

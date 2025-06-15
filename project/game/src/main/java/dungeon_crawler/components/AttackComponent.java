package dungeon_crawler.components;

public class AttackComponent extends Component {
    private int damage;
    private int attackSpeed;
    private long lastAttackTime = System.currentTimeMillis();

    public AttackComponent(int damage, int attackSpeed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public boolean canAttack() {
        return System.currentTimeMillis() - lastAttackTime >= attackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void updateLastAttackTime() {
        this.lastAttackTime = System.currentTimeMillis();
    }
}

package dungeon_crawler.components;

public class AttackComponent extends Component {
    private int damage;
    private int attackSpeed;

    public AttackComponent(int damage, int attackSpeed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}

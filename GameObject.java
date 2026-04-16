import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class GameObject implements Cloneable, Icon {
    protected int id;
    protected float x;
    protected float y;
    protected int size;
    protected float speed;
    protected Color color;
    protected int health;
    protected int attackDamage;
    protected float attackRange;
    protected float attackCooldown;
    protected float lastAttackTime;
    protected int fraction;
    protected boolean isAlive = true;
    protected int direction = 1;
    protected Engine engine = Engine.getInstance();

    public GameObject() {
        id = -1;
        size = 50;
        speed = 0;
        color = Color.BLACK;
    }

    public GameObject(int id, float x, float y, int size, float speed, Color color) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
    }

    public GameObject(int id, float x, float y, int size, float speed) {
        this(id, x, y, size, speed, Color.BLACK);
    }

    public GameObject(float x, float y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int size) {
        this.size = size;
    }

    /**
     * Базовое обновление. Переопределяется в наследниках.
     */
    protected void update(float dt) {
        // Базовое движение, если нужно просто идти вперед
        // x += speed * dt;
    }

    /**
     * Плавное движение к цели.
     * Использует нормализацию вектора и deltaTime для независимости от FPS.
     */
    public void moveTowards(GameObject target, float dt) {
        if (target == null) return;

        float dx = target.x - this.x;
        float dy = target.y - this.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Если мы достаточно близко, можно остановиться или считать, что дошли
        if (distance > 1.0f) {
            // Нормализация вектора направления
            float nx = dx / distance;
            float ny = dy / distance;

            // Перемещение на скорость * время кадра
            this.x += nx * this.speed ;
            this.y += ny * this.speed ;
        }
    }

    public void takeDamage(int damage) {
        // Реализуется в наследниках
    }

    public void destroy() {
        this.isAlive = false;
    }

    public boolean canAttack(float currentTime) {
        return currentTime - lastAttackTime >= attackCooldown;
    }

    public void attack(GameObject target, float currentTime) {
        if (target == null || !target.isAlive()) return;
        if (distanceTo(target) > attackRange) return;
        if (currentTime - lastAttackTime < attackCooldown) return;

        target.takeDamage(this.attackDamage);
        lastAttackTime = currentTime;
    }

    public float distanceTo(GameObject other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float distanceSqTo(GameObject other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return dx * dx + dy * dy;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Float(x, y, size, size));
    }

    // Геттеры и сеттеры
    public float getX() { return x; }
    public float getY() { return y; }
    public Color getColor() { return color; }
    public int getSize() { return size; }
    public int getId() { return id; }
    public float getSpeed() { return speed; }

    public void setId(int id) { this.id = id; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setSize(int size) { this.size = size; }
    public void setSpeed(float speed) { this.speed = speed; }
    public void setColor(Color color) { this.color = color; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getAttackDamage() { return attackDamage; }
    public void setAttackDamage(int attackDamage) { this.attackDamage = attackDamage; }

    public float getAttackRange() { return attackRange; }
    public void setAttackRange(float attackRange) { this.attackRange = attackRange; }

    public float getAttackCooldown() { return attackCooldown; }
    public void setAttackCooldown(float attackCooldown) { this.attackCooldown = attackCooldown; }

    public float getLastAttackTime() { return lastAttackTime; }
    public void setLastAttackTime(float lastAttackTime) { this.lastAttackTime = lastAttackTime; }

    public int getFraction() { return fraction; }
    public void setFraction(int fraction) { this.fraction = fraction; }

    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return id == that.id && Float.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }

    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        this.x = x;
        this.y = y;
        draw(g);
    }

    @Override
    public int getIconWidth() { return size; }
    @Override
    public int getIconHeight() { return size; }

    @Override
    public String toString() {
        return "GameObject{" + "id=" + id + ", x=" + x + ", y=" + y + ", size=" + size + '}';
    }

    public void start(float moveSpeed) {
        this.speed = moveSpeed;
    }

    public void stop() {
        this.speed = 0f;
    }
}
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class GameObject implements Cloneable {
    protected final int id;
    protected float x, y;
    protected final float speed;


    public GameObject(int id, float x, float y, float size, float speed) {
        this.id = id;
        this.x = x;
        this.y = y;

        this.speed = speed;
    }

    public void update() {
        x += (int) (speed);
    }

    public void moveTowards(GameObject target) {
        float dirX = target.x - this.x;
        float dirY = target.y - this.y;
        float distance = (float) Math.sqrt(dirX * dirX + dirY * dirY);

        if (distance > 1.0f) {
            float normX = dirX / distance;
            float normY = dirY / distance;

            this.x += normX * speed;
            this.y += normY * speed;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new Rectangle2D.Float()); // float coords
//        g.fillRect((int) x, (int) y, size, size); // int coords
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public int getId() { return id; }
    public float getSpeed() { return speed; }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return id == that.id && Float.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0  && Float.compare(speed, that.speed) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, speed);
    }

    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

import java.awt.*;

/**
 * Лучник: спавнится, идёт к башне (посередине карты),
 * останавливается и атакует, пока башня не разрушится.
 *
 * by Bebron28 & AmericanCoolBoyUSA777
 */
public class UnitArcher extends BaseUnit {

    // настройки лучника
    private static final float ARROW_SPEED = 600f;

    public static Builder builder() {
        return new UnitArcher().new Builder();
    }

    public class Builder extends BaseUnit.Builder {
        private Builder() {
            super();
        }

        public UnitArcher build() {
            return UnitArcher.this;
        }
    }
    /**
     * Выстрел по цели.
     */
    @Override
    public void attack(GameObject target, float currentTime) {
        float angle = Arrow.calculateArrowAngle(x, y, target.getX(), target.getY(), ARROW_SPEED);
        Arrow arrow = new Arrow(x, y, angle, ARROW_SPEED);
        arrow.setAttackDamage(attackDamage);
        arrow.setFraction(fraction);
        engine.spawnObject(arrow);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) this.x;
        int y = (int) this.y;

        float k = this.size / 100.0f;
        if (k <= 0) k = 1.0f;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // голова
        g2d.setColor(new Color(255, 218, 185));
        g2d.fillOval(Math.round(x), Math.round(y),
                Math.round(70 * k), Math.round(80 * k));

        // глаза
        g2d.setColor(Color.BLACK);
        g2d.fillOval(Math.round(x + 25 * k), Math.round(y + 30 * k),
                Math.round(10 * k), Math.round(12 * k));
        g2d.fillOval(Math.round(x + 50 * k), Math.round(y + 30 * k),
                Math.round(10 * k), Math.round(12 * k));

        // тело
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillOval(Math.round(x - 10 * k), Math.round(y + 80 * k),
                Math.round(90 * k), Math.round(140 * k));

        // лук (дуга)
        g2d.setColor(new Color(101, 67, 33));
        g2d.setStroke(new BasicStroke(4.0f * k));
        g2d.drawArc(Math.round(x + 30 * k), Math.round(y + 50 * k),
                Math.round(100 * k), Math.round(150 * k),
                270, 190);

        // тетива
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.0f * k));
        int bowCenterX = Math.round(x + 80 * k);
        int bowTopY = Math.round(y + 52 * k);
        int bowBottomY = Math.round(y + 200 * k);
        g2d.drawLine(bowCenterX, bowTopY, bowCenterX, bowBottomY);

        // колчан
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(Math.round(x - 30 * k), Math.round(y + 100 * k),
                Math.round(25 * k), Math.round(60 * k));
        g2d.fillOval(Math.round(x - 30 * k), Math.round(y + 95 * k),
                Math.round(25 * k), Math.round(20 * k));

        // стрелы в колчане
        g2d.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 3; i++) {
            int yOffset = 110 + i * 15;
            g2d.drawLine(Math.round(x - 25 * k), Math.round(y + yOffset * k),
                    Math.round(x - 10 * k), Math.round(y + yOffset * k));
        }

        // полоска здоровья
        drawHealthBar(g2d, k);
    }

}
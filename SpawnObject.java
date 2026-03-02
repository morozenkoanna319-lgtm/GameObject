package org;

import java.awt.*;

public class SpawnObject {
    private float x, y;
    private int width, height;
    private Color color;
    private float speed;

    public SpawnObject(float x, float y, int width, int height, Color color, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed = speed;
    }

    public void update(float deltaTime) {
        // Движение объекта (можно переопределить в наследниках)
        y += speed * deltaTime;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);
    }

    // Геттеры
    public float getX() { return x; }
    public float getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }
    public float getSpeed() { return speed; }

    // Сеттеры
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
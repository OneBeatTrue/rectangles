package ru.onebeattrue.entities;

import java.awt.*;

public abstract class AbstractShape implements Shape {
    private Color highColor;

    private Color lowColor;

    protected Color color;

    protected AbstractShape() {
        this.highColor = Color.BLACK;
        this.lowColor = Color.LIGHT_GRAY;
        this.color = lowColor;
    }

    protected AbstractShape(Color highColor, Color lowColor) {
        this.highColor = highColor;
        this.lowColor = lowColor;
        this.color = lowColor;
    }

    @Override
    public void highlight() {
        this.color = this.highColor;
    }

    @Override
    public void lowlight() {
        this.color = this.lowColor;
    }
}

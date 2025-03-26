package ru.onebeattrue.entities;

import ru.onebeattrue.models.DrawInfo;

import java.awt.*;

public class Vertex extends AbstractShape {
    public double x, y;
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex(String x, String y) throws IllegalArgumentException {
        this.x = getDouble(x);
        this.y = getDouble(y);
    }

    private double getDouble(String str) throws IllegalArgumentException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coordinate " + str + " must be numeric.");
        }
    }

    public Vertex rotate(double phi, Vertex center) {
        Vertex shiftedVertex = new Vertex(this.x - center.x, this.y - center.y);
        Vertex rotatedVertex = new Vertex(
                shiftedVertex.x * Math.cos(phi) - shiftedVertex.y * Math.sin(phi),
                shiftedVertex.x * Math.sin(phi) + shiftedVertex.y * Math.cos(phi)
        );
        return new Vertex(rotatedVertex.x + center.x, rotatedVertex.y + center.y);
    }

    public double distance(Vertex other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public DrawInfo getDrawInfo() { return null; }
}

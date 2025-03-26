package ru.onebeattrue.entities;

import ru.onebeattrue.models.DrawInfo;

import java.awt.*;
import java.util.ArrayList;

public class Line extends AbstractShape {
    // ax + by + c = 0
    double a, b, c;
    Line(Vertex firstVertex, Vertex secondVertex) {
        this.a = secondVertex.y - firstVertex.y;
        this.b = firstVertex.x - secondVertex.x;
        this.c = secondVertex.x * firstVertex.y - secondVertex.y * firstVertex.x;
    }

    Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line getParallel(Vertex vertex) {
        return new Line(this.a, this.b, - a * vertex.x - b * vertex.y);
    }

    public Line getPerpendicular(Vertex vertex) {
        return new Line(this.b, - this.a, a * vertex.y - b * vertex.x);
    }

    public double check(Vertex vertex){
        return this.a * vertex.x + this.b * vertex.y + this.c;
    }

    public Vertex intersect(Line other) {
        if (this.a * other.b - this.b * other.a == 0) {
            return null;
        }
        return new Vertex(
                (this.b * other.c - this.c * other.b) / (this.a * other.b - this.b * other.a),
                (this.c * other.a - this.a * other.c) / (this.a * other.b - this.b * other.a)
        );
    }

    @Override
    public DrawInfo getDrawInfo() { return null; }
}

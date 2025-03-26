package ru.onebeattrue.entities;

import ru.onebeattrue.models.DrawInfo;

import java.awt.*;
import java.util.ArrayList;

public class Segment extends AbstractShape {
    Vertex firstVertex, secondVertex;

    public Segment(Vertex firstVertex, Vertex secondVertex){
        super(Color.RED, Color.LIGHT_GRAY);
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public double length() {
        return firstVertex.distance(secondVertex);
    }


    @Override
    public DrawInfo getDrawInfo() {
        return new DrawInfo(new ArrayList<>() {{ add(firstVertex); add(secondVertex); }}, this.color);
    }
}

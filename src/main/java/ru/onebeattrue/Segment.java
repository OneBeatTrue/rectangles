package ru.onebeattrue;

public class Segment {
    Vertex firstVertex, secondVertex;

    Segment(Vertex firstVertex, Vertex secondVertex){
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public double length() {
        return firstVertex.distance(secondVertex);
    }
}

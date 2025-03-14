package ru.onebeattrue;

import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle extends Polygon {
    Rectangle(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    Rectangle(Vertex firstVertex, Vertex secondVertex, double phi) {
        super();
        Vertex center = new Vertex((firstVertex.x + secondVertex.x) / 2, (firstVertex.y + secondVertex.y) / 2);
        Vertex thirdVertex = firstVertex.rotate(phi, center);
        Vertex fourthVertex = thirdVertex.rotate(Math.PI, center);
        vertices.add(firstVertex);
        vertices.add(thirdVertex);
        vertices.add(secondVertex);
        vertices.add(fourthVertex);
    }

    Rectangle(Vertex firstVertex, Vertex secondVertex) {
        super(new ArrayList<>(Arrays.asList(
                firstVertex,
                new Vertex(firstVertex.x, secondVertex.y),
                secondVertex,
                new Vertex(secondVertex.x, firstVertex.y)
        )));
    }
}

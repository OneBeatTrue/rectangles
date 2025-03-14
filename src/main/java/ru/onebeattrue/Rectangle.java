package ru.onebeattrue;

import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle extends Polygon {
    Rectangle(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    Rectangle(Vertex firstVertex, Vertex secondVertex) {
        super(new ArrayList<>() {{
            Vertex thirdVertex = new Vertex(secondVertex.x, firstVertex.y);
            Vertex fourthVertex = new Vertex(firstVertex.x, secondVertex.y);

            if ((firstVertex.x - secondVertex.x) * (firstVertex.y - secondVertex.y) > 0) {
                add(firstVertex);
                add(thirdVertex);
                add(secondVertex);
                add(fourthVertex);
            }
            else {
                add(firstVertex);
                add(fourthVertex);
                add(secondVertex);
                add(thirdVertex);
            }
              }});
    }

    Rectangle(Vertex firstVertex, Vertex secondVertex, double phi) {
        super(
                new Rectangle(firstVertex, secondVertex).
                        rotate(
                                phi,
                                new Vertex(
                                        (firstVertex.x + secondVertex.x) / 2,
                                        (firstVertex.y + secondVertex.y) / 2
                                )
                        ).vertices
        );
    }
}

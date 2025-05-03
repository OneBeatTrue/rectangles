package ru.onebeattrue.entities;

import java.util.ArrayList;

public class Rectangle extends Polygon {
//    Rectangle(ArrayList<Vertex> vertices) {
//        super(vertices);
//    }

//    public Rectangle(Vertex firstVertex, Vertex secondVertex) {
//        super(new ArrayList<>() {{
//            Vertex thirdVertex = new Vertex(secondVertex.x, firstVertex.y);
//            Vertex fourthVertex = new Vertex(firstVertex.x, secondVertex.y);
//
//            if ((firstVertex.x - secondVertex.x) * (firstVertex.y - secondVertex.y) > 0) {
//                add(firstVertex);
//                add(thirdVertex);
//                add(secondVertex);
//                add(fourthVertex);
//            }
//            else {
//                add(firstVertex);
//                add(fourthVertex);
//                add(secondVertex);
//                add(thirdVertex);
//            }
//              }});
//    }

//    public Rectangle(Vertex firstVertex, Vertex secondVertex, double phi) {
//        super(
//                new Rectangle(firstVertex, secondVertex).
//                        rotate(
//                                phi,
//                                new Vertex(
//                                        (firstVertex.x + secondVertex.x) / 2,
//                                        (firstVertex.y + secondVertex.y) / 2
//                                )
//                        ).vertices
//        );
//    }

    public Rectangle(Vertex firstVertex, Vertex secondVertex, Vertex thirdVertex) {
        super(new ArrayList<>() {{
            Line givenLine = new Line(firstVertex, secondVertex);
            if (givenLine.check(thirdVertex) == 0.0) {
                throw new IllegalArgumentException("Points must not lay ont the same line.");
            }
            else {
                Line firstPerpendicularLine = givenLine.getPerpendicular(firstVertex);
                Line secondPerpendicularLine = givenLine.getPerpendicular(secondVertex);
                Line parallelLine = givenLine.getParallel(thirdVertex);
                if (givenLine.check(thirdVertex) <= 0) {
                    add(firstVertex);
                    add(secondVertex);
                    add(secondPerpendicularLine.intersect(parallelLine));
                    add(firstPerpendicularLine.intersect(parallelLine));
                } else {
                    add(firstVertex);
                    add(firstPerpendicularLine.intersect(parallelLine));
                    add(secondPerpendicularLine.intersect(parallelLine));
                    add(secondVertex);
                }
            }
        }});
    }
}

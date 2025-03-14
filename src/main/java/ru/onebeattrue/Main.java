package ru.onebeattrue;

public class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(new Vertex(0, 0), new Vertex(4, 5));
        Rectangle r2 = new Rectangle(new Vertex(2, 0), new Vertex(2, 6), Math.PI / 2);
        Polygon p = r1.intersect(r2);
        Segment s = p.findMaxDistance();
        System.out.println();
        for (Vertex v : r1.vertices) {
            System.out.println(v.x + " " + v.y);
        }
        System.out.println();
        for (Vertex v : r2.vertices) {
            System.out.println(v.x + " " + v.y);
        }
        System.out.println();
        for (Vertex v : p.vertices) {
            System.out.println(v.x + " " + v.y);
        }
        System.out.println();
        System.out.println(s.length());
        System.out.println(s.firstVertex.x + " " + s.firstVertex.y);
        System.out.println(s.secondVertex.x + " " + s.secondVertex.y);
    }
}
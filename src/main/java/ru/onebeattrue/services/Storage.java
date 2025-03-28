package ru.onebeattrue.services;

import ru.onebeattrue.entities.*;
import ru.onebeattrue.entities.Polygon;
import ru.onebeattrue.entities.Rectangle;
import ru.onebeattrue.entities.Shape;
import ru.onebeattrue.models.CoordinateRanges;
import ru.onebeattrue.models.DrawInfo;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Polygon> polygons = new ArrayList<>();

    private ArrayList<Shape> highlights = new ArrayList<>();

    private Logger logger;

    public Storage(Logger logger) {
        this.logger = logger;
    }

    public void add(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineIndex = 0;
            while ((line = reader.readLine()) != null) {
                lineIndex++;

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 6) {
                    this.error("(Line " + lineIndex + ") " + "Must be exactly 6 arguments, line has " + parts.length + ".");
                    continue;
                }

                try {
                    Vertex firstVertex = new Vertex(parts[0], parts[1]);
                    Vertex secondVertex = new Vertex(parts[2], parts[3]);
                    Vertex thirdVertex = new Vertex(parts[4], parts[5]);
                    Polygon polygon = new Rectangle(firstVertex, secondVertex, thirdVertex);
                    this.add(polygon);
                } catch (IllegalArgumentException e) {
                    this.error("(Line " + lineIndex + ") " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            this.error("File " + filename + " not found.");
        } catch (IOException e) {
            this.error("Failure while reading " + filename);
        }
    }

    public void add(String x1, String y1, String x2, String y2, String x3, String y3) {
        try {
            Vertex firstVertex = new Vertex(x1, y1);
            Vertex secondVertex = new Vertex(x2, y2);
            Vertex thirdVertex = new Vertex(x3, y3);
            this.add(firstVertex, secondVertex, thirdVertex);
        } catch (IllegalArgumentException e) {
            this.error(e.getMessage());
        }
    }

    public void add(CoordinateRanges ranges) {
        this.add(generateVertex(ranges), generateVertex(ranges), generateVertex(ranges));
    }

    public void add(Vertex firstVertex, Vertex secondVertex, Vertex thirdVertex) {
        try {
            Polygon polygon = new Rectangle(firstVertex, secondVertex, thirdVertex);
            this.add(polygon);
        } catch (IllegalArgumentException e) {
            this.error(e.getMessage());
        }
    }

    public void add(Polygon polygon) {
        this.lowlight();
        this.polygons.add(polygon);
        this.logger.log("Rectangle successfully added.");
    }

    public void clear() {
        this.polygons.clear();
        this.logger.log("Field is clear.");
        this.lowlight();
    }

    private void error(String errorMessage) {
        logger.log("[ERROR] " + errorMessage);
    }

    private Vertex generateVertex(CoordinateRanges ranges) {
        double x = (Math.random() - 0.5) * ranges.xRange();
        double y = (Math.random() - 0.5) * ranges.yRange();
        return new Vertex(x, y);
    }
    private void lowlight(){
        if (!this.highlights.isEmpty()) {
            for (Shape shape : this.highlights) {
                shape.lowlight();
            }
            this.highlights.clear();
        }
    }

    public void findMaxDistance() {
        this.lowlight();
        Segment maxSegment = null;
        for (int i = 0; i < this.polygons.size(); i++) {
            for (int j = i + 1; j < this.polygons.size(); j++) {
                Polygon firstPolygon = this.polygons.get(i);
                Polygon secondPolygon = this.polygons.get(j);
                Polygon intersection = firstPolygon.intersect(secondPolygon);
                Segment segment = intersection.findMaxDistance();
                if (segment == null) {
                    continue;
                }
                if (maxSegment == null || segment.length() > maxSegment.length()) {
                    maxSegment = segment;
                    Segment finalMaxSegment = maxSegment;
                    this.highlights = new ArrayList<>() {{
                        add(firstPolygon);
                        add(secondPolygon);
                        add(finalMaxSegment);
                    }};
                }
            }
        }

        for (Shape shape: this.highlights) {
            shape.highlight();
        }

        if (maxSegment == null) {
            this.error("Intersections not found.");
        }
        else {
            this.logger.log("Maximal intersection diameter is " + String.format("%.3f", maxSegment.length()) + " long.");
        }
    }


    public ArrayList<DrawInfo> getDrawInfo() {
        ArrayList<DrawInfo> drawInfo = new ArrayList<>();
        for (Polygon polygon : this.polygons) {
            drawInfo.add(polygon.getDrawInfo());
        }
        for (Shape shape : this.highlights) {
            drawInfo.add(shape.getDrawInfo());
        }
        return drawInfo;
    }
}

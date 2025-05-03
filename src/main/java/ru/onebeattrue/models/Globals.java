package ru.onebeattrue.models;

public class Globals {
    public static double maxScale = 1e3;
    public static double minScale = 1e-1;
    public static int gridCell = 50;
    public static double maxCoordinateX = 20 * gridCell / minScale;
    public static double minCoordinateX = -maxCoordinateX;
    public static double maxCoordinateY = 8 * gridCell / minScale;
    public static double minCoordinateY = -maxCoordinateY;
}

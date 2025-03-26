package ru.onebeattrue.models;

import ru.onebeattrue.entities.Vertex;

import java.awt.*;
import java.util.ArrayList;

public record DrawInfo(ArrayList<Vertex> vertices, Color color) {}

package ru.onebeattrue.entities;

import ru.onebeattrue.models.DrawInfo;


public interface Shape {
    void highlight();
    void lowlight();
    DrawInfo getDrawInfo();
}

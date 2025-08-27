package factory;

import models.button.*;

public class ButtonFactory {
    public static Button createButton(String label, String type) {
        switch(type.toUpperCase()){
            case "UP":
                return new UpButton(label);
            case "DOWN":
                return new DownButton(label);
            case "OPEN":
                return new OpenButton(label);
            case "CLOSE":
                return new CloseButton(label);
            case "FLOOR":
                return new FloorButton(label);
            default:
                throw new IllegalArgumentException("Unknown button type: " + type);
        }
    }
}

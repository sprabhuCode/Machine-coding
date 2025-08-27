package models.pannel;

import factory.ButtonFactory;
import models.button.Button;
import models.button.DownButton;
import models.button.UpButton;

import java.util.List;

public class OutterControlPanel extends ControlPanel{

    private int floorId;

    public OutterControlPanel(int floorId) {
        this.floorId = floorId;
        this.buttons = List.of(ButtonFactory.createButton("UP","UP"),
                ButtonFactory.createButton("DOWN","DOWN"));
    }

    @Override
    public void pressButton(Button button) {
        button.onPress();
    }
}

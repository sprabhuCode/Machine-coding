package models.pannel;

import factory.ButtonFactory;
import models.button.Button;
import models.button.CloseButton;
import models.button.FloorButton;
import models.button.OpenButton;

import java.util.ArrayList;

public class InnerControlPanel extends ControlPanel{

    public InnerControlPanel(int floors){
        buttons = new ArrayList<>();
        for (int i = 1; i <= floors; i++) {
            buttons.add(ButtonFactory.createButton("FLOOR", "FLOOR-" + i));
        }
        buttons.add(ButtonFactory.createButton("OPEN", "OPEN"));
        buttons.add(ButtonFactory.createButton("CLOSE", "CLOSE"));
    }
    @Override
    public void pressButton(Button button) {
        button.onPress();
    }
}

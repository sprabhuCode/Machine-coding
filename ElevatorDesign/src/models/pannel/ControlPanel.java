package models.pannel;

import java.util.ArrayList;
import java.util.List;
import models.button.Button;

abstract class ControlPanel {
    protected List<Button> buttons = new ArrayList<>();

    public void addButton(Button button) {
        buttons.add(button);
    }

    public abstract void pressButton(Button button);
}

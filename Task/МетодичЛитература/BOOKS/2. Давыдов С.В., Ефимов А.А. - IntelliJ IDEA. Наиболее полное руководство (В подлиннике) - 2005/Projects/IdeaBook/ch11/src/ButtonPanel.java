package ideabook.ch11;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * "IntelliJ IDEA Book"
 * Stanislav Davydov (davidovsv@yandex.ru) (c) 2004
 */
public class ButtonPanel <T extends AbstractButton> extends JPanel {
    private ButtonGroup buttonGroup;

    public ButtonPanel() {
        buttonGroup = new ButtonGroup();
    }

    public void add (T button) {
        add(button);
        buttonGroup.add(button);
    }
}

package views;

import controllers.AddLoanController;
import models.MaterialModel;

import javax.swing.*;

public class AddLoanView extends JPanel {
    private final AddLoanController controller;

    public AddLoanView(MaterialModel material) {
        controller = new AddLoanController(material);
    }
}

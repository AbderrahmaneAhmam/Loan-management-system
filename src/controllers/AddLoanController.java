package controllers;

import models.LoansModel;
import models.MaterialModel;

public class AddLoanController {
    private final MaterialModel material;

    public AddLoanController(MaterialModel material) {
        this.material = material;
    }
}

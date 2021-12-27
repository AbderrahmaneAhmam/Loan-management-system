package common.interfaces;

import models.LoansModel;
import models.UserModel;

import java.util.ArrayList;

public interface ILoansManager {
    ArrayList<LoansModel> getLoans();
    ArrayList<LoansModel> getLoansByUser(UserModel user);
    boolean addLoan(LoansModel loan);
    boolean deleteLoan(LoansModel loan);
    boolean deleteLoan(int id);
    boolean updateLoan(LoansModel loan);
}

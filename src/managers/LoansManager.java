package managers;

import common.interfaces.ILoansManager;
import models.LoansModel;
import models.UserModel;

import java.util.ArrayList;

class LoansManager extends Manager implements ILoansManager{

    public LoansManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<LoansModel> getLoans() {
        return null;
    }

    @Override
    public ArrayList<LoansModel> getLoansByUser(UserModel user) {
        return null;
    }

    @Override
    public boolean addLoan(LoansModel loan) {
        return false;
    }

    @Override
    public boolean deleteLoan(LoansModel loan) {
        return false;
    }

    @Override
    public boolean deleteLoan(int id) {
        return false;
    }

    @Override
    public boolean updateLoan(LoansModel loan) {
        return false;
    }
}

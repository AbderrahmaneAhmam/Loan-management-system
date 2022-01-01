package common.interfaces;

import models.LoansModel;
import models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ILoansManager extends IManager<LoansModel> {
    ArrayList<LoansModel> getLoans() throws SQLException;
    ArrayList<LoansModel> getLoansByUser(UserModel user) throws SQLException;
    boolean addLoan(LoansModel loan) throws SQLException;
    boolean deleteLoan(LoansModel loan) throws SQLException;
    boolean deleteLoan(int id) throws SQLException;
    boolean updateLoan(LoansModel loan) throws SQLException;
    ResultSet LonsCount() throws SQLException;
    ResultSet LonsAvailable() throws SQLException ;
    ArrayList<LoansModel> getCurrentLoans(String name) throws SQLException;
    ArrayList<LoansModel> getDelayedLoans(String name) throws SQLException;
}

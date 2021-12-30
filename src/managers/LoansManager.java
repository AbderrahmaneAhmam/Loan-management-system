package managers;

import common.interfaces.ILoansManager;
import models.LoansModel;
import models.MaterialModel;
import models.UserModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class LoansManager extends Manager implements ILoansManager{

    public LoansManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<LoansModel> getLoans() throws SQLException {
        var result = db.getStatement().executeQuery("select l.id,l.date_loan,l.date_back,l.duration,m.id,m.name,m.picture,u.id,u.first_name,u.last_name,u.email from loans l inner join users u on u.id=l.user_id inner join materials m on m.id=l.material_id");
        return getLoansModels(result);
    }

    @Override
    public ArrayList<LoansModel> getLoansByUser(UserModel user) throws SQLException {
        var ps = db.getConnection().prepareStatement("select l.id,l.date_loan,l.date_back,l.duration,m.id,m.name,m.picture,u.id,u.first_name,u.last_name,u.email from loans l inner join users u on u.id=l.user_id inner join materials m on m.id=l.material_id where u.id=?");
        ps.setInt(1,user.getId());
        var result = ps.executeQuery();
        return getLoansModels(result);
    }

    private ArrayList<LoansModel> getLoansModels(ResultSet result) throws SQLException {
        var loans = new ArrayList<LoansModel>();
        while(result.next()){
            loans.add(new LoansModel(result.getInt(1),result.getDate(2),result.getDate(3), result.getInt(4),
                    new MaterialModel(result.getInt(5),result.getString(6),result.getString(7)),
                    new UserModel(result.getInt(8),result.getString(9),result.getString(10),result.getString(11))));
        }
        return loans;
    }

    @Override
    public boolean addLoan(LoansModel loan) throws SQLException {
        var prs = db.getConnection().prepareStatement("insert into loans (user_id, material_id, duration, date_loan) values (?,?,?,?)");
        prs.setInt(1,loan.getUser().getId());
        prs.setInt(2,loan.getMaterial().getId());
        prs.setInt(3, loan.getDuration());
        prs.setDate(4, loan.getLoanDate());
        int row = prs.executeUpdate();
        return row != 0;
    }

    @Override
    public boolean deleteLoan(LoansModel loan) throws SQLException {
        var prs = db.getConnection().prepareStatement("delete from loans where id=?");
        prs.setInt(1,loan.getId());
        int row = prs.executeUpdate();
        return row != 0;
    }

    @Override
    public boolean deleteLoan(int id) throws SQLException {
        var prs = db.getConnection().prepareStatement("delete from loans where id=?");
        prs.setInt(1,id);
        int row = prs.executeUpdate();
        return row != 0;
    }

    @Override
    public boolean updateLoan(LoansModel loan) throws SQLException {
        var prs = db.getConnection().prepareStatement("update loans set date_back=? where id=?");
        prs.setInt(1,loan.getId());
        prs.setDate(2, loan.getBackDate());
        int row = prs.executeUpdate();
        return row != 0;
    }
}

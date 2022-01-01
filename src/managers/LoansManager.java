package managers;

import common.interfaces.ILoansManager;
import models.LoansModel;
import models.MaterialModel;
import models.UserModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

class LoansManager extends Manager<LoansModel> implements ILoansManager{

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
        events.forEach(e->e.onRowAdd(loan));
        return row != 0;
    }

    @Override
    public boolean deleteLoan(LoansModel loan) throws SQLException {
        var prs = db.getConnection().prepareStatement("delete from loans where id=?");
        prs.setInt(1,loan.getId());
        int row = prs.executeUpdate();
        events.forEach(e->e.onRowAdd(loan));
        return row != 0;
    }

    @Override
    public boolean deleteLoan(int id) throws SQLException {
        var prs = db.getConnection().prepareStatement("delete from loans where id=?");
        prs.setInt(1,id);
        int row = prs.executeUpdate();
        events.forEach(e->e.onRowAdd(new LoansModel(id)));
        return row != 0;
    }

    @Override
    public boolean updateLoan(LoansModel loan) throws SQLException {
        var prs = db.getConnection().prepareStatement("update loans set date_back=? where id=?");
        prs.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
        prs.setInt(2,loan.getId());
        int row = prs.executeUpdate();
        events.forEach(e->e.onRowAdd(loan));
        return row != 0;
    }


    public ResultSet LonsCount() throws SQLException {
        var rs = db.getStatement().executeQuery("select MONTHNAME(date_loan) As 'Month',count(*) As 'Number of Loans' from materials m, loans l where m.id =  l.material_id \n" +
                "and ( MONTH(date_loan) = MONTH(Now())\n" +
                "OR (YEAR(date_loan) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(date_loan) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH))\n" +
                "OR (YEAR(date_loan) = YEAR(CURRENT_DATE - INTERVAL 2 MONTH) AND MONTH(date_loan) = MONTH(CURRENT_DATE - INTERVAL 2 MONTH))\n" +
                "OR (YEAR(date_loan) = YEAR(CURRENT_DATE - INTERVAL 3 MONTH) AND MONTH(date_loan) = MONTH(CURRENT_DATE - INTERVAL 3 MONTH)))\n" +
                "group by MONTHNAME(date_loan) Order by date_loan");
        return rs;
    }
    public ResultSet LonsAvailable() throws SQLException {
        var rs = db.getStatement().executeQuery("select (SELECT count(*)from materials)-(SELECT count(*) from vue) , count(*) from vue");
        return rs;
    }
    public  ArrayList<LoansModel> getCurrentLoans(String name) throws SQLException {
        var ps = db.getConnection().prepareStatement("select l.id,l.date_loan,l.date_back,l.duration,m.id,m.name,m.picture,u.id,u.first_name,u.last_name,u.email from loans l , users u,materials m where u.id=l.user_id and m.id=l.material_id and (l.date_back is null ) and (m.name like ? or u.first_name like ? or u.last_name like ? or u.email like ?)");
        ps.setString(1,"%"+name+"%");
        ps.setString(2,"%"+name+"%");
        ps.setString(3,"%"+name+"%");
        ps.setString(4,"%"+name+"%");
        var rs = ps.executeQuery();
        return getLoansModels(rs);
    }
    public ArrayList<LoansModel> getDelayedLoans(String name) throws SQLException {
        var ps = db.getConnection().prepareStatement("select u.id, u.first_name, u.last_name, u.email, m.id, m.name, m.picture ,l.id,l.duration,l.date_loan,l.date_back  from users u,materials m,loans l where u.id = user_id and m.id = material_id and DATEDIFF( Now(), l.date_loan) > l.duration and  l.date_back is null and (m.name like ? or u.first_name like ? or u.last_name like ? or u.email like ?)");
        ps.setString(1,"%"+name+"%");
        ps.setString(2,"%"+name+"%");
        ps.setString(3,"%"+name+"%");
        ps.setString(4,"%"+name+"%");
        var rs = ps.executeQuery();
        var loans = new ArrayList<LoansModel>();
        while (rs.next()) {
            loans.add(new LoansModel(rs.getInt(8),
                    rs.getDate(10),
                    rs.getDate(11),
                    rs.getInt(9),
                    new MaterialModel(rs.getInt(5), rs.getString(6), rs.getString(7)),
                    new UserModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))));
        }
        return loans;
    }
}

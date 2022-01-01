package managers;

import common.interfaces.IUsersManager;
import models.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;

class UsersManager extends Manager<UserModel> implements IUsersManager {

    public UsersManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<UserModel> getUsers() throws SQLException {
        var result = db.getStatement().executeQuery("select id 'ID',first_name 'First name',last_name 'Last name',email 'Email' from users");
        var users = new ArrayList<UserModel>();
        while(result.next()){
            users.add(new UserModel(result.getInt(1)
                    ,result.getString(2),
                    result.getString(3),
                    result.getString(4)));
        }
        return users;
    }

    @Override
    public boolean addUser(UserModel user) throws SQLException {
        try {
                var prs = db.getConnection().prepareStatement("insert into users(first_Name,last_Name,email) values (?,?,?)");
                prs.setString(1, user.getFirstName());
                prs.setString(2, user.getLastName());
                prs.setString(3, user.getEmail());

                int row = prs.executeUpdate();
                events.forEach(e->e.onRowAdd(user));
                if (row == 0){
                    return false;
            }

            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean updateUser(UserModel user) {
        try {
            var prs = db.getConnection().prepareStatement("Update users set first_Name = ?, last_Name = ? , email = ? where id = ?");
            prs.setString(1,user.getFirstName() );
            prs.setString(2, user.getLastName());
            prs.setString(3, user.getEmail());
            prs.setInt(4, user.getId());
            int row = prs.executeUpdate();
            events.forEach(e->e.onRowAdd(user));

            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(UserModel user) {
        try {

            var prs = db.getConnection().prepareStatement("delete from users where id = ?");
            prs.setInt(1,user.getId());
            int row = prs.executeUpdate();
            events.forEach(e->e.onRowAdd(user));
           if (row != 1){
               return false;
           }
           return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try{
        var prs = db.getConnection().prepareStatement("delete from users where id = ?");
        prs.setInt(1,id);
        int row = prs.executeUpdate();
        events.forEach(e->e.onRowAdd(new UserModel(id,"","","")));
        if (row != 1){
            return false;
        }
        return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public UserModel getUserByEmail(String email) {
        try {
            var prs = db.getConnection().prepareStatement("SELECT u.id,u.first_name,u.last_name,u.email FROM users u WHERE u.email = ?");
            prs.setString(1,email);
            var rs = prs.executeQuery();
            rs.next();
            return new UserModel(rs.getInt(1), rs.getString(2) , rs.getString(3), rs.getString(4));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<UserModel> getUsersByName(String name) throws SQLException {
        try {
            var prs = db.getConnection().prepareStatement("select * from users where first_name like ? or last_name like ? or email like ?");
            prs.setString(1,"%"+name+"%");
            prs.setString(2,"%"+name+"%");
            prs.setString(3,"%"+name+"%");
            var rs = prs.executeQuery();
            var Ausers = new ArrayList<UserModel>();
            while(rs.next()){
                Ausers.add(new UserModel(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }
            return Ausers;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
}

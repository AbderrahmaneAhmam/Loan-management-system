package managers;

import common.interfaces.IUsersManager;
import models.UserModel;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

class UsersManager extends Manager implements IUsersManager {

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
    public boolean addUser() {
        return false;
    }

    @Override
    public boolean updateUser(UserModel user) {
        return false;
    }

    @Override
    public boolean deleteUser(UserModel user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return null;
    }

    @Override
    public ArrayList<UserModel> getUsersByName(String name) {
        return null;
    }
}

package common.interfaces;

import models.LoansModel;
import models.UserModel;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IUsersManager extends IManager<UserModel>  {
    ArrayList<UserModel> getUsers() throws SQLException;
    boolean addUser(UserModel user) throws SQLException;
    boolean updateUser(UserModel user);
    boolean deleteUser(UserModel user);
    boolean deleteUser(int id);
    UserModel getUserByEmail(String email);
    ArrayList<UserModel> getUsersByName(String name) throws SQLException;
}

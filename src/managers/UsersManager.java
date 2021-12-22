package managers;

import common.interfaces.IUsersManager;
import models.UserModel;

import java.util.ArrayList;

class UsersManager extends Manager implements IUsersManager {

    public UsersManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<UserModel> getUsers() {
        return null;
    }
}

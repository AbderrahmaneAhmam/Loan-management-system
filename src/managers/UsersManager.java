package managers;

import common.interfaces.IUsersManager;

public class UsersManager extends Manager implements IUsersManager {

    public UsersManager(DBManager db) {
        super(db);
    }
}

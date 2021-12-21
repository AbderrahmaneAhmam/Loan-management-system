package managers;

import common.interfaces.ILoansManager;
import common.interfaces.IMaterialsManager;
import common.interfaces.IUsersManager;

public class AppSDK {
    public static DBManager db;
    public static IMaterialsManager MaterialsManager;
    public static IUsersManager UsersManager;
    public static ILoansManager LoansManager;

    public AppSDK(){
        db = new DBManager();
        UsersManager = new UsersManager(db);
    }
}

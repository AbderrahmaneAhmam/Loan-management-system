package managers;

import common.interfaces.ILoansManager;
import common.interfaces.IMaterialsManager;
import common.interfaces.IUsersManager;

public class AppSDK {
    private DBManager db;
    public IMaterialsManager MaterialsManager;
    public IUsersManager UsersManager;
    public ILoansManager LoansManager;

    public AppSDK(){
        db = new DBManager();
    }
}

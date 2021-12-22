package managers;
import common.interfaces.ILoansManager;
import common.interfaces.IMaterialsManager;
import common.interfaces.IUsersManager;

public class AppSDK {
    public final static DBManager db = new DBManager();
    public final static IMaterialsManager MaterialsManager = new MaterialsManager(db);
    public final static IUsersManager UsersManager = new UsersManager(db);
    public final static ILoansManager LoansManager = new LoansManager(db);
}

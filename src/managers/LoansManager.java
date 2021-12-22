package managers;

import common.interfaces.ILoansManager;
class LoansManager extends Manager implements ILoansManager{

    public LoansManager(DBManager db) {
        super(db);
    }
}

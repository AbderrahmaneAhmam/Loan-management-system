package managers;

import common.interfaces.IMaterialsManager;

class MaterialsManager extends Manager implements IMaterialsManager {

    public MaterialsManager(DBManager db) {
        super(db);
    }
}

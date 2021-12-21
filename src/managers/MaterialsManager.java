package managers;

import common.interfaces.IMaterialsManager;

public class MaterialsManager extends Manager implements IMaterialsManager {

    public MaterialsManager(DBManager db) {
        super(db);
    }
}

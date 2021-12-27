package managers;

import common.interfaces.IMaterialsManager;
import models.MaterialModel;
import models.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;

class MaterialsManager extends Manager implements IMaterialsManager {

    public MaterialsManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<MaterialModel> getMaterials() {
        return null;
    }

    @Override
    public ArrayList<MaterialModel> getMaterialsByName(String name) {
        return null;
    }

    @Override
    public ArrayList<MaterialModel> getAvailableMaterials() throws SQLException {
        var result = db.getStatement().executeQuery("select ma.id,ma.name,ma.picture from materials ma where ma.id in (select DISTINCT lo.material_id from loans lo where lo.material_id=ma.id and lo.date_back is not null)");
        var materials = new ArrayList<MaterialModel>();
        while(result.next()){
            materials.add(new MaterialModel(result.getInt(1)
                    ,result.getString(2),
                    result.getString(3)));
        }
        return materials;
    }

    @Override
    public ArrayList<MaterialModel> getAvailableMaterialsByName(String name) throws SQLException {
        var stm = db.getConnection().prepareStatement("select ma.id, ma.name, ma.picture from materials ma where ma.name LIKE ? and ma.id in (select DISTINCT lo.material_id from loans lo where lo.material_id=ma.id and lo.date_back is not null);");
        stm.setString(1,"%"+name+"%");
        var result = stm.executeQuery();
        var materials = new ArrayList<MaterialModel>();
        while(result.next()){
            materials.add(new MaterialModel(result.getInt(1)
                    ,result.getString(2),
                    result.getString(3)));
        }
        return materials;
    }

    @Override
    public boolean addMaterial(MaterialModel material) {
        return false;
    }

    @Override
    public boolean updateMaterial(MaterialModel material) {
        return false;
    }

    @Override
    public boolean deleteMaterial(MaterialModel material) {
        return false;
    }
}

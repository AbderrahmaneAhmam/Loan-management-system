package managers;

import common.interfaces.IMaterialsManager;
import models.MaterialModel;
import java.sql.SQLException;
import java.util.ArrayList;

class MaterialsManager extends Manager implements IMaterialsManager {

    public MaterialsManager(DBManager db) {
        super(db);
    }

    @Override
    public ArrayList<MaterialModel> getMaterials() throws SQLException {
        var result = db.getStatement().executeQuery("select id,name,picture from materials");
        var materials = new ArrayList<MaterialModel>();
        while(result.next()){
            materials.add(new MaterialModel(result.getInt(1)
                    ,result.getString(2),
                    result.getString(3)));
        }
        return materials;
    }

    @Override
    public ArrayList<MaterialModel> getMaterialsByName(String name) throws SQLException {
        try {
            var prs = db.getConnection().prepareStatement("select id,name,picture from materials where name like ? ");
            prs.setString(1,"%"+name+"%");
            var rs = prs.executeQuery();
            var materials = new ArrayList<MaterialModel>();
            while(rs.next()){
                materials.add(new MaterialModel(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
            return materials;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
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
    public boolean addMaterial(MaterialModel material){
        try {
            var prs = db.getConnection().prepareStatement("insert into materials (name,picture) values (?,?)");
            prs.setString(1, material.getName());
            prs.setString(2, material.getPicture());
            int row = prs.executeUpdate();
            return row != 0;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateMaterial(MaterialModel material) {
        try {
            var prs = db.getConnection().prepareStatement("update materials set name=?,picture=? where id=?");
            prs.setString(1, material.getName());
            prs.setString(2, material.getPicture());
            prs.setInt(3, material.getId());
            int row = prs.executeUpdate();
            return row != 0;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteMaterial(MaterialModel material) {
        try {
            var prs = db.getConnection().prepareStatement("delete from materials where id=?");
            prs.setInt(1, material.getId());
            int row = prs.executeUpdate();
            return row != 0;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}

package common.interfaces;

import models.MaterialModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMaterialsManager {
    ArrayList<MaterialModel> getMaterials() throws SQLException;
    ArrayList<MaterialModel> getMaterialsByName(String name) throws SQLException;
    ArrayList<MaterialModel> getAvailableMaterials() throws SQLException;
    ArrayList<MaterialModel> getAvailableMaterialsByName(String name) throws SQLException;
    boolean addMaterial(MaterialModel material) throws SQLException;
    boolean updateMaterial(MaterialModel material);
    boolean deleteMaterial(MaterialModel material);
}

package common.interfaces;

import models.UserModel;

import java.util.ArrayList;

public interface IUsersManager {
    ArrayList<UserModel> getUsers();
}

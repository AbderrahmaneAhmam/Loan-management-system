package common.interfaces;

import models.UserModel;

public interface ChangesListener<T> {
    void onRowAdd(T model);
}

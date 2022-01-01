package managers;

import common.interfaces.ChangesListener;
import java.util.ArrayList;

class Manager<T>{
    protected DBManager db;
    protected final ArrayList<ChangesListener<T>> events;
    public Manager(DBManager db){
        this.db=db;
        events = new ArrayList<>();
    }
    public void addChangesListener(ChangesListener<T> listener) {
        events.add(listener);
    }
}

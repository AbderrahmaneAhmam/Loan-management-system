package common.interfaces;

public interface IManager<T> {
    void addChangesListener(ChangesListener<T> listener);
}

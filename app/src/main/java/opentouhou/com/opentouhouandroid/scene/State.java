package opentouhou.com.opentouhouandroid.scene;

/*
 * Defines methods that a state in a FSM for the game should implement.
 */
public interface State<T> {
    void enter(T scene);
    void handleInput(T scene);
    State<T> update(T scene);
    void exit(T scene);
}
package e2;

public interface ActionGrid extends Grid{
    void doAction(int row, int column);

    boolean check(int row, int column);
}

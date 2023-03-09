package e2;

public interface Logics {

    boolean hasMine(int row, int column);

    boolean isWin();

    boolean hit(int row, int column);

    void flag(int row, int column);

    boolean hasFlag(int row, int column);

    boolean isSelected(int row, int column);
}

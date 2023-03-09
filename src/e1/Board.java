package e1;

public interface Board {
    int rows();
    int columns();
    boolean isValidPosition(int x, int y);
}

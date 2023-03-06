package e1;

public interface GameStrategyBuilder {
    GameStrategy randomGeneration(int row, int column);
    GameStrategy fixedGeneration(Pair<Integer,Integer> knight, Pair<Integer,Integer> pawn);
}

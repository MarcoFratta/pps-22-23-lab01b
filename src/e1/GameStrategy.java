package e1;


public interface GameStrategy {
    boolean isMovementAllowed(Pair<Integer,Integer> from, Pair<Integer,Integer> to);
    Pair<Integer,Integer> createKnight();
    Pair<Integer,Integer> createPawn();
}

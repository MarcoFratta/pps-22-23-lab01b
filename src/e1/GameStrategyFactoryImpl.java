package e1;

import java.util.Random;

public class GameStrategyFactoryImpl implements GameStrategyFactory {
    @Override
    public GameStrategy randomGeneration(final int rows, final int columns) {
        return new KnightGameStrategy() {

            private final Random random = new Random();
            private Pair<Integer,Integer> pawn;

            @Override
            public Pair<Integer, Integer> createKnight() {
                final var pos = this.getRandomPair();
                return this.pawn!=null && this.pawn.equals(pos) ? this.createKnight(): pos;
            }
            @Override
            public Pair<Integer, Integer> createPawn() {
                this.pawn = this.getRandomPair();
                return this.pawn;
            }
            private Pair<Integer, Integer> getRandomPair() {
                return new Pair<>(this.random.nextInt(rows),
                        this.random.nextInt(columns));
            }
        };
    }

    @Override
    public GameStrategy fixedGeneration(final Pair<Integer, Integer> knight, final Pair<Integer, Integer> pawn) {
        return new KnightGameStrategy() {
            @Override
            public Pair<Integer, Integer> createKnight() {
                return knight;
            }

            @Override
            public Pair<Integer, Integer> createPawn() {
                return pawn;
            }
        };
    }

    private abstract static class KnightGameStrategy implements GameStrategy{

        private static final int MAX_MOVES = 3;

        @Override
        public boolean isMovementAllowed(final Pair<Integer, Integer> from, final Pair<Integer,Integer> to) {
            final int x = to.getX()-from.getX();
            final int y = to.getY()-from.getY();
            return x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == MAX_MOVES;
        }
    }
}

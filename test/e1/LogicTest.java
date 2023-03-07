package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    public static final int SIZE = 5;
    protected Logics logics;

    @BeforeEach
    void beforeEach() {
        final GameStrategyFactory builder = new GameStrategyFactoryImpl();
        this.logics = new LogicsImpl(builder.randomGeneration(SIZE,SIZE),SIZE);
    }

    protected List<Pair<Integer, Integer>> getValidPosition(final Set<Pair<Integer, Integer>> possibleMoves) {
        return possibleMoves.stream()
                .filter(this::isValidPosition).toList();
    }

    protected boolean isValidPosition(final Pair<Integer, Integer> p) {
        return !(p.getX()<0 || p.getY()<0 || p.getX() >= SIZE || p.getY() >= SIZE);
    }

    protected Set<Pair<Integer, Integer>> getPossibleMoves(final Pair<Integer, Integer> knightPosition) {
        final Set<Pair<Integer, Integer>> list = new HashSet<>();
        for (int i = - 2; i <= 2; i +=4) {
            for(int j = -1 ; j <= 1; j+=2){
                list.add(new Pair<>(knightPosition.getX() + i,knightPosition.getY() + j));
                list.add(new Pair<>(knightPosition.getX() + j,knightPosition.getY() + i));
            }
        }
        return list;
    }

    protected int countFeatureOnAllBoard(final Predicate<Pair<Integer, Integer>> condition) {
        int x = 0;
        for(int i = 0; i< SIZE; i++){
            for(int j= 0; j < SIZE; j++){
                if(condition.test(new Pair<>(i,j))){
                    x++;
                }
            }
        }
        return x;
    }

    protected Pair<Integer, Integer> findOnAllBoard(final Predicate<Pair<Integer, Integer>> condition) {
        for(int i = 0; i< SIZE; i++){
            for(int j= 0; j < SIZE; j++){
                final var t = new Pair<>(i, j);
                if(condition.test(t)){
                    return t;
                }
            }
        }
        throw new IllegalArgumentException("Not found");
    }

    public static class StandardTest extends LogicTest{

        @Test
        void testHasPawn(){
            final int pawnNumber = this.countFeatureOnAllBoard((p) -> this.logics.hasPawn(p.getX(),p.getY()));
            assertEquals(1, pawnNumber);
        }

        @Test
        void testHasKnight(){
            final int knightNumber = this.countFeatureOnAllBoard((p) -> this.logics.hasKnight(p.getX(),p.getY()));
            assertEquals(1, knightNumber);
        }

        @Test
        void testCanMove(){
            final var knightPosition = new  Pair<>(0,0);
            final var destination = new Pair<>(knightPosition.getX() + 2, knightPosition.getY() + 1);
            this.logics = new LogicsImpl(new GameStrategyFactoryImpl()
                    .fixedGeneration(knightPosition,new Pair<>(1,0)), SIZE);
            this.logics.hit(destination.getX(),destination.getY());
            assertTrue(this.logics.hasKnight(destination.getX(),destination.getY()));
        }
        @Test
        void testCanHit(){
            final var pawnPosition = new  Pair<>(3,1);
            final var knightPosition = new Pair<>(4,3);
            this.logics = new LogicsImpl(new GameStrategyFactoryImpl()
                    .fixedGeneration(knightPosition,pawnPosition), SIZE);
            assertTrue(this.logics.hit(pawnPosition.getX(), pawnPosition.getY()));
        }
    }

    public static class EdgeCases extends LogicTest{
        @Test
        void testInvalidConstructor(){
            final int size = 4;
            final var pawnPosition = new  Pair<>(-1,-5);
            final var knightPosition = new Pair<>(-7,-3);
            assertThrows(IllegalArgumentException.class,
                    () -> this.logics = new LogicsImpl(new GameStrategyFactoryImpl()
                            .fixedGeneration(knightPosition,pawnPosition), size));
        }

        @Test
        void testCanMoveAllDirections(){
            final var knightPosition = this.findOnAllBoard(p -> this.logics.hasKnight(p.getX(),p.getY()));
            final var possibleMoves = this.getPossibleMoves(knightPosition);
            for (final var position: this.getValidPosition(possibleMoves)) {
                this.logics.hit(position.getX(), position.getY());
                assertTrue(this.logics.hasKnight(position.getX(),position.getY()));
                this.logics.hit(knightPosition.getX(),knightPosition.getY());
            }
        }
        @Test
        void testFailNotValidMove(){
            final var knightPosition = this.findOnAllBoard(p -> this.logics.hasKnight(p.getX(),p.getY()));
            final var possibleMoves = this.getPossibleMoves(knightPosition);
            this.getValidPosition(possibleMoves).forEach(possibleMoves::remove);
            for (final Pair<Integer, Integer> position: this.getValidPosition(possibleMoves)) {
                assertThrows(IndexOutOfBoundsException.class,
                        () -> this.logics.hit(position.getX(), position.getY()));
            }
        }


        @Test
        void testKnightAndPawnSamePosition(){
            final var pawnPosition = new  Pair<>(3,1);
            assertThrows(IllegalArgumentException.class, () ->
                    this.logics = new LogicsImpl(new GameStrategyFactoryImpl()
                            .fixedGeneration(pawnPosition,pawnPosition), SIZE));
        }
    }
}
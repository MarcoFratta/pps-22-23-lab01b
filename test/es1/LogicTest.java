package es1;

import e1.Logics;
import e1.LogicsImpl;
import e1.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    public static final int SIZE = 5;
    private Logics logics;


    @BeforeEach
    void beforeEach() {
        this.logics = new LogicsImpl(SIZE);
    }

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
    void testInvalidConstructor(){
        final int size = 4;
        final var pawnPosition = new  Pair<>(-1,-5);
        final var knightPosition = new Pair<>(-7,-3);
        assertThrows(IllegalArgumentException.class,
                () -> this.logics = new LogicsImpl(pawnPosition,knightPosition, size));
    }

    @Test
    void testCanMove(){
        final var knightPosition = new  Pair<>(0,0);
        final var destination = new Pair<>(knightPosition.getX() + 2, knightPosition.getY() + 1);
        this.logics = new LogicsImpl(new Pair<>(0,0),knightPosition, SIZE);
        this.logics.hit(destination.getX(),destination.getY());
        assertTrue(this.logics.hasKnight(destination.getX(),destination.getY()));

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
    void testCanHit(){
        final var pawnPosition = new  Pair<>(3,1);
        final var knightPosition = new Pair<>(4,3);
        this.logics = new LogicsImpl(pawnPosition,knightPosition, SIZE);
        assertTrue(this.logics.hit(pawnPosition.getX(), pawnPosition.getY()));
    }


    private List<Pair<Integer, Integer>> getValidPosition(final Set<Pair<Integer, Integer>> possibleMoves) {
        return possibleMoves.stream()
                .filter(this::isValidPosition).toList();
    }

    private boolean isValidPosition(final Pair<Integer, Integer> p) {
        return !(p.getX()<0 || p.getY()<0 || p.getX() >= SIZE || p.getY() >= SIZE);
    }
    private Set<Pair<Integer, Integer>> getPossibleMoves(final Pair<Integer, Integer> knightPosition) {
        final Set<Pair<Integer, Integer>> list = new HashSet<>();
        for (int i = - 2; i <= 2; i +=4) {
            for(int j = -1 ; j <= 1; j+=2){
                list.add(new Pair<>(knightPosition.getX() + i,knightPosition.getY() + j));
                list.add(new Pair<>(knightPosition.getX() + j,knightPosition.getY() + i));
            }
        }
        return list;
    }
    private boolean isValidKnightMovement(final Pair<Integer,Integer> knightPosition, final Pair<Integer,Integer> nextPosition){
        final int x = nextPosition.getX()-knightPosition.getX();
        final int y = nextPosition.getY()-knightPosition.getY();
        return (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3);
    }
    private int countFeatureOnAllBoard(final Predicate<Pair<Integer, Integer>> condition) {
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
    private Pair<Integer, Integer> findOnAllBoard(final Predicate<Pair<Integer, Integer>> condition) {
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
}

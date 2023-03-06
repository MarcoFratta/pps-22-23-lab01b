package es1;

import e1.Logics;
import e1.LogicsImpl;
import e1.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
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
    void testCanMove(){
        final var knightPosition = this.findOnAllBoard(p -> this.logics.hasPawn(p.getX(),p.getY()));
        final var possibleMoves = this.getPossibleMoves(knightPosition);
        for (final Pair<Integer, Integer> position: possibleMoves.stream()
                .filter(this::isValidPosition).toList()) {
            this.logics.hit(position.getX(), position.getY());
            assertTrue(this.logics.hasKnight(position.getX(),position.getY()));
        }
    }
    @Test
    void testFailNotValidMove(){
        final var knightPosition = this.findOnAllBoard(p -> this.logics.hasPawn(p.getX(),p.getY()));
        final var possibleMoves = this.getPossibleMoves(knightPosition);
        for (final Pair<Integer, Integer> position: possibleMoves.stream()
                .filter(p-> !this.isValidPosition(p)).toList()) {
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




    private boolean isValidPosition(final Pair<Integer, Integer> p) {
        return !(p.getX()<0 || p.getY()<0 || p.getX() >= SIZE || p.getY() >= SIZE);
    }
    private Set<Pair<Integer, Integer>> getPossibleMoves(final Pair<Integer, Integer> knightPosition) {
        final Set<Pair<Integer, Integer>> list = new HashSet<>();
        for (int i = knightPosition.getX() - 2; i <= knightPosition.getX() + 2; i ++) {
            for(int j = knightPosition.getY() - 2; j <= knightPosition.getY() + 2; j++){
                final var x = new Pair<>(knightPosition.getX() + i,knightPosition.getY() + j);
                if(this.isValidKnightMovement(knightPosition, x)){
                    list.add(x);
                }
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

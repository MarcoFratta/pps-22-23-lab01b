package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicTest {


    public static final int SIZE = 5;
    private static final int MINES_NUMBERS = 5;
    private Logics logics;

    @BeforeEach
    public void setUp(){
        this.logics = new LogicsImpl(SIZE, MINES_NUMBERS);
    }


    @Test
    void testCanCreateMines(){
        assertEquals(MINES_NUMBERS, this.countMines());
    }

    private int countMines() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                 if(this.logics.hasMine(i,j)){
                   count ++;
                }
            }
        }
        return count;
    }
}

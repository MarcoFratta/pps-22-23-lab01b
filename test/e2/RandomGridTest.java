package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomGridTest extends MinedGridTest{


    private static final int MINES = 5;

    @BeforeEach
    void beforeEach(){
        super.beforeEach();
        super.actionGrid = new RandomGrid(this.actionGrid, MINES);
    }

    @Test
    void testRandomInit(){
        assertEquals(MINES, this.countChecked(this.actionGrid));
    }

    @Test
    void testFailTooManyMines(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, 1 + SIZE * SIZE));
    }

    @Test
    void testFailNegativeMines(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, -1));
    }


    private int countChecked(final ActionGrid grid) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(grid.check(i,j)){
                    count ++;
                }
            }
        }
        return count;
    }

}

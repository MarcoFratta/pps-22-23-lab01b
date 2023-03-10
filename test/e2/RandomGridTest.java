package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomGridTest extends SelectableGridTest {


    private static final int SELECTIONS = 5;

    @BeforeEach
    void beforeEach(){
        super.beforeEach();
        super.actionGrid = new RandomGrid(this.actionGrid, SELECTIONS);
    }

    @Test
    void testRandomInit(){
        assertEquals(SELECTIONS, Utils.countChecked(this.actionGrid));
    }

    @Test
    void testFailTooManySelections(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, 1 + SIZE * SIZE));
    }

    @Test
    void testFailNegativeSelections(){
        assertThrows(IllegalArgumentException.class,
                () ->  super.actionGrid = new RandomGrid(this.actionGrid, -1));
    }

    @Override
    void testStreamIsFiltered() {
        assertEquals(SELECTIONS, this.actionGrid.stream().count());
    }
}

package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectableGridTest extends GridTest{

    protected ActionGrid actionGrid;
    @BeforeEach
    void beforeEach(){
        super.beforeEach();
        this.actionGrid = new SelectableGrid(this.grid);
    }

    @Test
    void testCanAddMine(){
        final int x = 3;
        final int y = 4;
        this.actionGrid.doAction(x,y);
        assertTrue(this.actionGrid.check(x,y));
    }


    @Test
    void testFailOnInvalidPosition(){
        final int x = -1;
        final int y = SIZE;
        assertThrows(IllegalArgumentException.class,
                () -> this.actionGrid.doAction(x,y));
        assertThrows(IllegalArgumentException.class,
                () -> this.actionGrid.check(x,y));
    }

    @Test
    void testStreamIsFiltered(){
        final int position = 0;
            this.actionGrid.doAction(position,position);
            assertEquals(1,this.actionGrid.stream().count());
        }

}

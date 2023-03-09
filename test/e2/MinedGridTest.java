package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinedGridTest extends GridTest{

    private static final int MINES = 5;
    protected ActionGrid actionGrid;
    @BeforeEach
    void beforeEach(){
        super.beforeEach();
        this.actionGrid = new MinedGrid(this.grid, MINES);
    }

    @Test
    void testCanAddMine(){
        final int x = 3;
        final int y = 4;
        this.actionGrid.doAction(x,y);
        assertTrue(this.actionGrid.check(x,y));
    }

    @Test
    void testCanRemoveMine(){
        final int x = 2;
        final int y = 1;
        this.actionGrid.doAction(x,y);
        this.actionGrid.undoAction(x,y);
        assertFalse(this.actionGrid.check(x,y));
    }

    @Test
    void testFailOnInvalidPosition(){
        final int x = -1;
        final int y = SIZE;
        assertThrows(IllegalArgumentException.class,
                () -> this.actionGrid.doAction(x,y));
        assertThrows(IllegalArgumentException.class,
                () -> this.actionGrid.undoAction(x,y));
        assertThrows(IllegalArgumentException.class,
                () -> this.actionGrid.check(x,y));
    }
}

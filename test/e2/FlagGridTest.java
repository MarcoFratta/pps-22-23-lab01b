package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlagGridTest extends GridTest{

    private ActionGrid actionGrid;
    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        this.actionGrid = new FlagGrid(this.grid);
    }

    @Test
    void testCanFlag(){
        final int y = 0;
        this.actionGrid.doAction(y,y);
        assertTrue(this.actionGrid.check(y,y));
    }

    @Test
    void canRemoveFlag(){
        final int pos = 0;
        this.actionGrid.doAction(pos,pos);
        this.actionGrid.doAction(pos,pos);
        assertFalse(this.actionGrid.check(pos,pos));
    }
}

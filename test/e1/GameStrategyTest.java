package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStrategyTest {

    public static final int SIZE = 5;
    private GameStrategy strategy;

    @BeforeEach
    void beforeEach(){
        final GameStrategyFactory builder = new GameStrategyFactoryImpl();
        this.strategy =builder.randomGeneration(SIZE, SIZE);
    }


    @Test
    void testCreateKnight(){
        final var knight = this.strategy.createKnight();
        assertTrue(this.isInsideBoard(knight.getX()));
        assertTrue(this.isInsideBoard(knight.getY()));
    }

    private boolean isInsideBoard(final Integer x) {
        return x >= 0 && x < SIZE;
    }

    @Test
    void testCreatePawn(){
        final var pawn = this.strategy.createPawn();
        assertTrue(this.isInsideBoard(pawn.getX()));
        assertTrue(this.isInsideBoard(pawn.getY()));
    }

    @Test
    void testValidMoveCheck(){
        final var knight = new Pair<>(0,0);
        final var validMovement = new Pair<>(knight.getX() + 2, knight.getY() -1);
        assertTrue(this.strategy.isMovementAllowed(knight, validMovement));
    }

    @Test
    void testInvalidMoveCheck(){
        final var knight = new Pair<>(0,0);
        final var validMovement = new Pair<>(knight.getX() + 1, knight.getY() -1);
        assertFalse(this.strategy.isMovementAllowed(knight, validMovement));
    }
}

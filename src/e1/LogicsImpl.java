package e1;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Board board;
	private final GameStrategy strategy;


	public LogicsImpl(final GameStrategy strategy, final int size) {
		this.board = new BoardImpl(size, size);
		this.strategy = strategy;
		this.pawn = strategy.createPawn();
		this.knight = strategy.createKnight();
    	if (this.positionIsNotValid(this.pawn)
				||this.positionIsNotValid(this.knight)
				|| this.pawn.equals(this.knight)) {
			throw new IllegalArgumentException();
		}

	}

	public LogicsImpl(final int size) {
		this(new GameStrategyBuilderImpl().randomGeneration(size,size), size);
	}

	private boolean positionIsNotValid(final Integer x, final Integer y) {
		return !this.board.isValidPosition(x, y);
	}
	private boolean positionIsNotValid(final Pair<Integer, Integer> p){
    	return this.positionIsNotValid(p.getX(),p.getY());
	}

	@Override
	public boolean hit(final int row, final int col) {
		if (this.positionIsNotValid(row, col)) {
			throw new IndexOutOfBoundsException();
		}
		final var nextPosition = new Pair<>(row,col);
		if(this.strategy.isMovementAllowed(this.knight,nextPosition)){
			this.knight = nextPosition;
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(final int row, final int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(final int row, final int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}

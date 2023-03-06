package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(final int size){
    	this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();	
    }

	public LogicsImpl(final Pair<Integer, Integer> pawn, final Pair<Integer, Integer> knight, final int size) {
		this.size = size;
    	if (this.isInvalidPosition(pawn.getX(), pawn.getY()) ||
				this.isInvalidPosition(knight.getX(), knight.getY())) {
			throw new IllegalArgumentException();
		}
		this.pawn = pawn;
		this.knight = knight;

	}

	private boolean isInvalidPosition(final int x, final int y){
    	return x < 0 || y < 0 || x >= this.size || y >= this.size;
	}

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	final Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(this.size),this.random.nextInt(this.size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? this.randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(final int row, final int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		final int x = row-this.knight.getX();
		final int y = col-this.knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight = new Pair<>(row,col);
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

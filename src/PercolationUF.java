import java.util.Arrays;

public class PercolationUF implements IPercolate {

	protected boolean[][] myGrid;
	protected int myOpenCount;
	
	IUnionFind myFinder = new QuickUWPC();
	
	protected int VTOP;
	protected int VBOTTOM;
	
	
	/**
	 * Initialize a grid so that all cells are open false.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 *            
	 * @param finder
	 * 				is IUnionFind object Instantiated n*n + 2 isolated components in [0,n*n+1]
	 * 				by calling finder.initialize appropriately and then storing 
	 * 				this object in the appropriate instance variable.  

	 */
	public PercolationUF(IUnionFind finder, int n) {
		myGrid = new boolean[n][n];
		myOpenCount = 0;
		for (boolean[] row : myGrid)
			Arrays.fill(row, false);
		
		myFinder = finder;
		myFinder.initialize(n*n + 2);
		VTOP = n*n;	
		VBOTTOM = n*n + 1;
	}
	
	@Override
	public void open(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col])
			return;
		myOpenCount += 1;
		myGrid[row][col] = true;
	
		if(isTop(row))
			myFinder.union(getPosition(row, col), VTOP);
		
		if(isBottom(row))
			myFinder.union(getPosition(row, col), VBOTTOM);
		
		unionAdjacent(row, col);

	}

	@Override
	public boolean isOpen(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	@Override
	public boolean isFull(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myFinder.connected(getPosition(row, col), this.VTOP);
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(this.VTOP, this.VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	
	/**
	 * Determine if (row,col) is valid for given grid
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	protected boolean isTop(int row)
	{
		return row == 0;
	}
	
	protected boolean isBottom(int row)
	{
		return row == myGrid.length-1;
	}
	
	protected void unionAdjacent (int row, int col)
	{
				
		if(inBounds(row-1, col) && myGrid[row-1][col])
			myFinder.union(getPosition(row, col), getPosition(row-1, col));

		if(inBounds(row, col-1) &&  myGrid[row][col-1])
			myFinder.union(getPosition(row, col), getPosition(row, col-1));
			

		if(inBounds(row, col+1) && myGrid[row][col+1])
			myFinder.union(getPosition(row, col), getPosition(row, col+1));
		

		if(inBounds(row+1, col) && myGrid[row+1][col])
			myFinder.union(getPosition(row, col), getPosition(row+1, col));
	}

	protected int getPosition(int row, int col) {
		
		return (row*myGrid.length + col);
	}
	
}

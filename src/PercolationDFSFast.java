
public class PercolationDFSFast extends PercolationDFS{
	
	public PercolationDFSFast (int n) {
		super(n);
	}
	
	
	protected boolean isTop(int row)
	{
		return row == 0;
	}
	

	
	protected boolean isAdjacentFull (int row, int col)
	{
				
		if(inBounds(row-1, col) && myGrid[row-1][col] == FULL)
			return true;
		
		if(inBounds(row - 1, col) && myGrid[row-1][col] == FULL)
			return true;

		if(inBounds(row, col-1) &&  myGrid[row][col-1] == FULL)
			return true;

		if(inBounds(row, col+1) && myGrid[row][col+1] == FULL)
			return true;

		if(inBounds(row+1, col) && myGrid[row+1][col] == FULL)
			return true;

		return false;
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		
		 boolean markFull = false;
		
		 if(isTop(row))
			markFull = true;
		
		 if(isAdjacentFull (row, col))
			 markFull = true;
		
		if (markFull)
			dfs(row, col);
	
	}


}

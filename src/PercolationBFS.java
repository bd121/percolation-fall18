import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
	
	public PercolationBFS (int n) {
		super(n);
	}
	
	
	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	@Override
	protected void dfs(int row, int col) {
		// out of bounds? 
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
			
		myGrid[row][col] = FULL;
		
		Queue<Integer> q = new LinkedList<>();  
		q.add(getPosition(row, col));
		
		
		while (q.size() != 0){

			int pos = q.remove();
			row = getRow(pos);
			col = getCol(pos);
			
			if(inBounds(row-1, col) && myGrid[row-1][col] == OPEN) {
				myGrid[row-1][col] = FULL;
				q.add(getPosition(row-1, col));
			}
			
			if(inBounds(row, col-1) && myGrid[row][col-1] == OPEN) {
				myGrid[row][col-1] = FULL;
				q.add(getPosition(row, col-1));
			}
		
			if(inBounds(row, col+1) && myGrid[row][col+1] == OPEN) {
				myGrid[row][col+1] = FULL;
				q.add(getPosition(row, col+1));
			}

			if(inBounds(row+1, col) && myGrid[row+1][col] == OPEN) {
				myGrid[row+1][col] = FULL;
				q.add(getPosition(row+1, col));
			}
			
			
		}
		
	}
	
	protected int getPosition(int row, int col) {
		
		return (row*myGrid.length + col);
	}
	
	protected int getRow(int pos) {
		return (pos/myGrid.length);
	}
	
	protected int getCol(int pos) {
		return (pos % myGrid.length);
	}
	
}


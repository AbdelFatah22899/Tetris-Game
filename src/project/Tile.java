package project;
import java.awt.Color;

// Abstraction
public abstract class Tile
{

  	private Color baseColor;
	
	private Color lightColor;
	
	private Color darkColor;
	
	private int spawnCol;
	
	private int spawnRow;
	
	private int dimension;
	
	private int rows;
	
	private int cols;
	
	private boolean[][] tiles;
	
	Tile(Color color, int dimension, int cols, int rows, boolean[][] tiles) 
        {
		this.baseColor = color;
		this.lightColor = color.brighter();
		this.darkColor = color.darker();
		this.dimension = dimension;
		this.tiles = tiles;
		this.cols = cols;
		this.rows = rows;
		
		this.spawnCol = 8 ;
		this.spawnRow = getTopInset(0);
	}
	

	public Color getBaseColor() 
        {
		return baseColor;
	}
	
	
	public Color getLightColor() 
        {
		return lightColor;
	}
	
	
	public Color getDarkColor() 
        {
		return darkColor;
	}
	
	public int getDimension() 
        {
		return dimension;
	}
	
	public int getSpawnColumn() 
        {
		return spawnCol;
	}
	
	public int getSpawnRow() 
        {
		return spawnRow;
	}
	

	public int getRows() 
        {
		return rows;
	}
	

	public int getCols() 
        {
		return cols;
	}
	
	
	public boolean isTile(int x, int y, int rotation) 
        {
		return tiles[rotation][y * dimension + x];
	}
	

	public int getLeftInset(int rotation) 
        {
		for(int x = 0; x < dimension; x++) 
                {
			for(int y = 0; y < dimension; y++) 
                        {
				if(isTile(x, y, rotation)) 
                                {
					return x;
				}
			}
		}
		return -1;
	}
	

	public int getRightInset(int rotation) 
        {
		for(int x = dimension - 1; x >= 0; x--) 
                {
			for(int y = 0; y < dimension; y++) 
                        {
				if(isTile(x, y, rotation)) 
                                {
					return dimension - x;
				}
			}
		}
		return -1;
	}
	

	public int getTopInset(int rotation) 
        {
		for(int y = 0; y < dimension; y++) 
                {
			for(int x = 0; x < dimension; x++) 
                        {
				if(isTile(x, y, rotation)) 
                                {
					return y;
				}
			}
		}
		return -1;
	}
	

	public int getBottomInset(int rotation) 
        {
		for(int y = dimension - 1; y >= 0; y--) 
                {
			for(int x = 0; x < dimension; x++) 
                        {
				if(isTile(x, y, rotation)) 
                                {
					return dimension - y;
				}
			}
		}
		return -1;
	}
	
}

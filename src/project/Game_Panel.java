package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game_Panel extends JPanel 
{

	protected int SMALL_INSET = 20;
	
	protected int LARGE_INSET = 40;
	
	protected int STATS_INSET = 175;
	
	protected int CONTROLS_INSET = 300;
	
	protected int TEXT_STRIDE = 25;
	
	protected Font SMALL_FONT = new Font("", Font.BOLD, 20);
	
	protected Font LARGE_FONT = new Font("", Font.BOLD, 25);
	
	protected Color DRAW_COLOR = new Color(128, 192, 128);
	
	public static final int COLOR_MIN = 35;
	
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	
	private static final int BORDER_WIDTH = 5;
	
	public static final int COL_COUNT = 18;
		
	private static final int VISIBLE_ROW_COUNT = 28;
	
	private static final int HIDDEN_ROW_COUNT = 2;
	
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	
	public static final int TILE_SIZE = 24;
	
	public static final int SHADE_WIDTH = 4;
	
	private static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;
	
	private static final int CENTER_Y = VISIBLE_ROW_COUNT * TILE_SIZE / 2;
		
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	int offset;
	
	private Tetris_game tetris;
	
	private Tile[][] tiles;
		
	public Game_Panel(Tetris_game tetris) 
        {
		this.tetris = tetris;
		this.tiles = new Tile[ROW_COUNT][COL_COUNT];
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
	}
	
	public void clear() 
        {
		for(int i = 0; i < ROW_COUNT; i++) 
                {
			for(int j = 0; j < COL_COUNT; j++) 
                        {
				tiles[i][j] = null;
			}
		}
	}
	
	public boolean isValidAndEmpty(Tile type, int x, int y, int rotation) 
        {
		if(x < -type.getLeftInset(rotation) || x + type.getDimension() - type.getRightInset(rotation) >= COL_COUNT) 
                {
			return false;
		}
		
		if(y < -type.getTopInset(rotation) || y + type.getDimension() - type.getBottomInset(rotation) >= ROW_COUNT) 
                {
			return false;
		}
		
		for(int col = 0; col < type.getDimension(); col++) 
                {
			for(int row = 0; row < type.getDimension(); row++) 
                        {
				if(type.isTile(col, row, rotation) && isOccupied(x + col, y + row)) 
                                {
					return false;
				}
			}
		}
		return true;
	}
	
	public void addPiece(Tile type, int x, int y, int rotation) 
        {
	
            for(int col = 0; col < type.getDimension(); col++) 
                {
			for(int row = 0; row < type.getDimension(); row++) 
                        {
				if(type.isTile(col, row, rotation)) 
                                {
					setTile(col + x, row + y, type);
				}
			}
		}
	}
	
	public int checkLines() 
        {
		int completedLines = 0;
		
		for(int row = 0; row < ROW_COUNT; row++) 
                {
			if(checkLine(row)) 
                        {
				completedLines++;
			}
		}
		return completedLines;
	}
			
	private boolean checkLine(int line) 
        {
		for(int col = 0; col < COL_COUNT; col++) 
                {
			if(!isOccupied(col, line)) 
                        {
				return false;
			}
		}
                
		for(int row = line - 1; row >= 0; row--) 
                {
			for(int col = 0; col < COL_COUNT; col++) 
                        {
				setTile(col, row + 1, getTile(col, row));
			}
		}
		return true;
	}
	
	
	private boolean isOccupied(int x, int y) 
        {
		return tiles[y][x] != null;
	}
	
	private void setTile(int  x, int y, Tile type) 
        {
		tiles[y][x] = type;
	}

        Background_image background;
		
	private Tile getTile(int x, int y) 
        {
		return tiles[y][x];
	}
	
	@Override
	public void paintComponent(Graphics g) 
        {
            
		super.paintComponent(g);
		if(tetris.isNewGame()){
                    background=new Background_image(new ImageIcon(getClass().getResource("tetrieslogo2.jpg")).getImage());
                    setBackground(Color.WHITE);
                    background.paintComponent(g,0,50);
                }
                else if(tetris.isGameOver())
                {
                    background=new Background_image(new ImageIcon(getClass().getResource("gameover.jpg")).getImage());
                    setBackground(Color.black);
                    background.paintComponent(g,20,0);
                }
                else{
                    background = new Background_image(new ImageIcon(getClass().getResource("olaf.jpg")).getImage());
                    background.paintComponent(g,-100,0);
                }
		g.translate(BORDER_WIDTH, BORDER_WIDTH);
		
		if(tetris.isPaused()) 
                {
			g.setFont(new Font("", Font.BOLD, 50));
			g.setColor(Color.BLUE);
			String msg = "PAUSED";
			g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, CENTER_Y-20);
                        

		g.setFont(LARGE_FONT);
                g.setColor(Color.BLACK);
		g.drawString("Controls", SMALL_INSET, offset = CONTROLS_INSET+150);
		g.setFont(SMALL_FONT);
		g.setColor(Color.red);
		g.drawString("Left Arrow - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
                g.setColor(new Color(255,140,0));
		g.drawString("Right Arrow - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
                g.setColor(Color.GREEN);
		g.drawString("R - Rotate ", LARGE_INSET, offset += TEXT_STRIDE);
                g.setColor(Color.BLUE);
		g.drawString("Down Arrow - Drop", LARGE_INSET, offset += TEXT_STRIDE);
                g.setColor(new Color(0,191,255));
                g.drawString("S - Switch", LARGE_INSET, offset += TEXT_STRIDE);
                g.setColor(new Color(75,0,130));
		g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);

		}
                else if(tetris.isNewGame() || tetris.isGameOver()) 
                {
			g.setFont(LARGE_FONT);
			g.setColor(Color.BLUE);
			
			g.setFont(SMALL_FONT);
                        String msg = "Press Enter to Play";
                            
                        if(tetris.isNewGame())
                        {
                            g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 380);
                        }
                        else if(tetris.isGameOver())
                        {
                            msg +=" Again";
                            g.setColor(new Color(255,215,0));
                            g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 220);
                            
                        }
                        
                        g.setFont(LARGE_FONT);
		        g.drawString("Controls", SMALL_INSET, offset = CONTROLS_INSET+150);
		        g.setFont(SMALL_FONT);
		        g.setColor(Color.red);
                        g.drawString("Left Arrow - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
                        g.setColor(new Color(255,140,0));
                        g.drawString("Right Arrow - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
                        g.setColor(Color.GREEN);
                        g.drawString("R - Rotate Clockwise", LARGE_INSET, offset += TEXT_STRIDE);
                        g.setColor(Color.BLUE);
                        g.drawString("Down Arrow - Drop", LARGE_INSET, offset += TEXT_STRIDE);
                        g.setColor(new Color(0,191,255));
                        g.drawString("S - Switch", LARGE_INSET, offset += TEXT_STRIDE);
                        g.setColor(new Color(75,0,130));
                        g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);
		} 
                else 
                {
			
			for(int x = 0; x < COL_COUNT; x++) 
                        {
				for(int y = HIDDEN_ROW_COUNT; y < ROW_COUNT; y++) 
                                {
					Tile tile = getTile(x, y);
					if(tile != null) 
                                        {
						drawTile(tile, x * TILE_SIZE, (y - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
					}
				}
			}
			
			Tile type = tetris.getPieceType();
			int pieceCol = tetris.getPieceCol();
			int pieceRow = tetris.getPieceRow();
			int rotation = tetris.getPieceRotation();
			
			for(int col = 0; col < type.getDimension(); col++) 
                        {
				for(int row = 0; row < type.getDimension(); row++) 
                                {
					if(pieceRow + row >= 2 && type.isTile(col, row, rotation)) 
                                        {
						drawTile(type, (pieceCol + col) * TILE_SIZE, (pieceRow + row - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
					}
				}
			}
			
			
			g.setColor(Color.DARK_GRAY);
			for(int x = 0; x < COL_COUNT; x++) 
                        {
				for(int y = 0; y < VISIBLE_ROW_COUNT; y++) 
                                {
					g.drawLine(0, y * TILE_SIZE, COL_COUNT * TILE_SIZE, y * TILE_SIZE);
					g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
				}
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
	}
        
	//Oveloading
	private void drawTile(Tile type, int x, int y, Graphics g) 
        {
		drawTile(type.getBaseColor(), type.getLightColor(), type.getDarkColor(), x, y, g);
	}
	
        //Oveloading
	private void drawTile(Color base, Color light, Color dark, int x, int y, Graphics g) 
        {
		
		g.setColor(base);
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		
		g.setColor(dark);
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
		
		g.setColor(light);
		for(int i = 0; i < SHADE_WIDTH; i++) 
                {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}

}

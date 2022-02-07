package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

//Inheritance
public class SecondaryPanel extends JPanel 
{

	protected int TILE_SIZE = Game_Panel.TILE_SIZE /2;
	
	protected int SHADE_WIDTH = Game_Panel.SHADE_WIDTH /2;
	
	protected int TILE_COUNT = 5;
	
	protected int SQUARE_CENTER_X = 150;
	
	protected int SQUARE_CENTER_Y = 65;
	
	protected int SQUARE_SIZE = (TILE_SIZE * TILE_COUNT / 2);
	
	protected int SMALL_INSET = 20;
	
	protected int LARGE_INSET = 40;
	
	protected int STATS_INSET = 175;
	
	protected int CONTROLS_INSET = 300;
	
	protected int TEXT_STRIDE = 25;
	
	protected Font SMALL_FONT = new Font("", Font.BOLD, 15);
	
	protected Font LARGE_FONT = new Font("", Font.BOLD, 17);
	
	protected Color DRAW_COLOR = new Color(128, 192, 128);
	
	protected Tetris_game tetris;
	
        Background_image background = new Background_image(new ImageIcon(getClass().getResource("source.gif")).getImage());
        
	public SecondaryPanel(Tetris_game tetris) 
        {
            
		this.tetris = tetris;
		
		setPreferredSize(new Dimension(200, Game_Panel.PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) 
        {
		super.paintComponent(g);
                background.paintComponent(g,0,0);

		g.setColor(DRAW_COLOR);
		
		int offset;
		
		g.setFont(LARGE_FONT);
		g.drawString("Stats", SMALL_INSET, offset = STATS_INSET+220);
		g.setFont(SMALL_FONT);
		g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
		
		
		g.setFont(LARGE_FONT);
                g.drawString("Next Piece1:", SMALL_INSET, 70);
                g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, SQUARE_CENTER_Y - SQUARE_SIZE, SQUARE_SIZE * 2, SQUARE_SIZE * 2);
                g.setFont(LARGE_FONT);
                g.drawString("Next Piece2:", SMALL_INSET, 135);
                g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, 100, SQUARE_SIZE * 2, SQUARE_SIZE * 2);
                g.setFont(LARGE_FONT);
                g.drawString("Next Piece3:", SMALL_INSET, 200);
                g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, 165, SQUARE_SIZE * 2, SQUARE_SIZE * 2);

                if(tetris.game_started == true)
                {
                    Tile [] type = new Tile[3]; 
	        type[0] = tetris.getNextPieceType1();
                type[1] = tetris.getNextPieceType2();
                type[2] = tetris.getNextPieceType3();
                
		if(!tetris.isGameOver()) 
                {
                    for(int i=0;i<type.length;i++)
                    {
                        
                        int cols = type[i].getCols();
			int rows = type[i].getRows();
			int dimension = type[i].getDimension();
		
			int startX = (SQUARE_CENTER_X - (cols * TILE_SIZE / 2));
			int startY = (SQUARE_CENTER_Y - (rows * TILE_SIZE / 2));
		
			int top = type[i].getTopInset(0);
			int left = type[i].getLeftInset(0);
		
			for(int row = 0; row < dimension; row++) 
                        {
				for(int col = 0; col < dimension; col++) 
                                {
					if(type[i].isTile(col, row, 0)) 
                                        {
						drawTile(type[i], startX + ((col - left) * TILE_SIZE), (startY+70*i) + ((row - top) * TILE_SIZE), g);
					}
				}
			}
		}
	
            }
                    
        }
	            
                
                }
                
	
	protected void drawTile(Tile type, int x, int y, Graphics g) 
        {
		g.setColor(type.getBaseColor());
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		
		g.setColor(type.getDarkColor());
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
		
		g.setColor(type.getLightColor());
		for(int i = 0; i < SHADE_WIDTH; i++) 
                {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}
	
}

package project;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;

public class Tetris_game extends JFrame 
{

        Sound s2=new Sound("D:\\University\\second year\\second term\\OOP\\project\\Tetris-sound\\Tetris-master\\src\\gameover1.wav");

        
	protected boolean game_started;
    
	private static final long FRAME_rate = 1000L / 50L;
		
	private Game_Panel board;
	
	private SecondaryPanel side_right;

	private boolean isPaused ;
	
	private boolean isNewGame;
	
	private boolean isGameOver;
	
	private int level;
	
	private int score;
	
	private Random random;
	
	private TIMER Timer;
				
	private Tile currentType;
	private Tile tempType;
	
	private Tile nextType1;
        private Tile nextType2;
        private Tile nextType3;
		
	private int currentCol;
	
	private int currentRow;
	
	private int currentRotation;

	private float gameSpeed;
		
	private Tetris_game() throws IOException
        {
		
		super("Tetris");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.board = new Game_Panel(this);
		this.side_right = new SecondaryPanel(this);

		add(board, BorderLayout.CENTER);
		add(side_right, BorderLayout.EAST);

		addKeyListener(new KeyAdapter() 
                {
                    
			@Override
			public void keyPressed(KeyEvent e) 
                        {
								
				switch(e.getKeyCode()) 
                                {
				
			
				case KeyEvent.VK_DOWN:
					if(!isPaused && game_started == true ) 
                                        {
						Timer.setCyclesPerSecond(100.0f);
					}
					break;
					
				
				case KeyEvent.VK_LEFT:
					if(!isPaused && game_started == true && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) 
                                        {
						currentCol--;
					}
					break;
					
				case KeyEvent.VK_RIGHT:
					if(!isPaused && game_started == true && board.isValidAndEmpty(currentType, currentCol + 1, currentRow, currentRotation)) 
                                        {
						currentCol++;
					}
					break;
				
				case KeyEvent.VK_R:
					if(!isPaused && game_started == true) 
                                        {
                                            if(currentRotation == 3)
                                            {
                                                rotatePiece(0);
                                            }
                                            else
                                            {
                                                rotatePiece(currentRotation + 1);
                                            }
						
					}
					break;
					
				case KeyEvent.VK_P:
					if(!isGameOver && !isNewGame) 
                                        {
						isPaused = !isPaused;
						Timer.setPaused(isPaused);
					}
					break;
                                        
				case KeyEvent.VK_S:
					if(!isNewGame && game_started == true) 
                                        {
                                            tempType = currentType;
                                            currentType = nextType1;
                                            nextType1 = tempType;
					}
					break;
				
				case KeyEvent.VK_ENTER:
					if(isGameOver || isNewGame) 
                                        {
                                            game_started = true;
                                            resetGame();
					}
					break;
				
				}
			}
			//Override (Polymorphism)
			@Override
			public void keyReleased(KeyEvent e) 
                        {
				
                            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                            {
                                if( game_started == true)
                                    {
                                        Timer.setCyclesPerSecond(gameSpeed);
					Timer.reset();
                                    }
					
                            }
				
				
			}
			
		}
                        
                );
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void startGame() 
        {
		this.random = new Random();
		this.isNewGame = true;
		this.gameSpeed = 1.0f;
		
		this.Timer = new TIMER(gameSpeed);
		Timer.setPaused(true);
		
		while(true) 
                {
			long start = System.nanoTime();
			
			Timer.update();
			
			if(Timer.hasElapsedCycle()) 
                        {
				updateGame();
			}
		
			renderGame();
			
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_rate) 
                        {
				try 
                                {
					Thread.sleep(FRAME_rate - delta);
				} 
                                
                                catch(Exception e) 
                                {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void updateGame() 
        {
		if(board.isValidAndEmpty(currentType, currentCol, currentRow + 1, currentRotation)) 
                {
			currentRow++;
		} 
                else 
                {
			board.addPiece(currentType, currentCol, currentRow, currentRotation);
			int cleared = board.checkLines();
			if(cleared > 0) 
                        {
        		score += Math.pow(2,cleared)*100;
        		gameSpeed += 1f;
			Timer.setCyclesPerSecond(gameSpeed);
			Timer.reset();
                        level = (int) (Math.pow(score/100,0.5));
			}
                        
			
			
			spawnPiece();
		}		
	}
	
	private void renderGame() 
        {
               board.repaint();
               side_right.repaint();       
	}
	private void resetGame() 
        {
                Tile [] pieces = new Tile[7];
                Tile o = new Type_i();
                pieces[0] = o ;
                Tile p = new Type_j();
                pieces[1] = p;
                Tile k = new Type_l();
                pieces[2] = k;
                Tile u = new Type_o();
                pieces[3] = u;
                Tile t = new Type_s();
                pieces[4] = t;
                Tile f = new Type_t();
                pieces[5] = f;
                Tile e = new Type_z();
                pieces[6] = e;
              
            
                
		this.level = 1;
		this.score = 0;
		this.gameSpeed = 1.0f;
		this.nextType1 = pieces[ThreadLocalRandom.current().nextInt(pieces.length)];
                this.nextType2 = pieces[ThreadLocalRandom.current().nextInt(pieces.length)];
                this.nextType3 = pieces[ThreadLocalRandom.current().nextInt(pieces.length)];
		this.isNewGame = false;
		this.isGameOver = false;		
		board.clear();
		Timer.reset();
		Timer.setCyclesPerSecond(gameSpeed);
		spawnPiece();
	}
		
	private void spawnPiece() 
        {
                Tile [] pieces = new Tile[7];
                Tile o = new Type_i();
                pieces[0] = o ;
                Tile p = new Type_j();
                pieces[1] = p;
                Tile k = new Type_l();
                pieces[2] = k;
                Tile u = new Type_o();
                pieces[3] = u;
                Tile t = new Type_s();
                pieces[4] = t;
                Tile f = new Type_t();
                pieces[5] = f;
                Tile e = new Type_z();
                pieces[6] = e;
		this.currentType = nextType1;
                this.nextType1 = this.nextType2;
                this.nextType2 = this.nextType3;
		this.currentCol = currentType.getSpawnColumn();
		this.currentRow = currentType.getSpawnRow();
		this.currentRotation = 0;
                
		this.nextType3 = pieces[ThreadLocalRandom.current().nextInt(pieces.length)];
		
		if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) 
                {
			this.isGameOver = true;
			Timer.setPaused(true);
		}		
	}

	private void rotatePiece(int newRotation) 
        {
		int newColumn = currentCol;
		int newRow = currentRow;
		
		int left = currentType.getLeftInset(newRotation);
		int right = currentType.getRightInset(newRotation);
		int top = currentType.getTopInset(newRotation);
		int bottom = currentType.getBottomInset(newRotation);
		
		if(currentCol < -left) 
                {
			newColumn -= currentCol - left;
		} 
                else if(currentCol + currentType.getDimension() - right >= Game_Panel.COL_COUNT) 
                {
			newColumn -= (currentCol + currentType.getDimension() - right) - Game_Panel.COL_COUNT + 1;
		}
		
		if(currentRow < -top) 
                {
			newRow -= currentRow - top;
		} 
                else if(currentRow + currentType.getDimension() - bottom >= Game_Panel.ROW_COUNT) 
                {
			newRow -= (currentRow + currentType.getDimension() - bottom) - Game_Panel.ROW_COUNT + 1;
		}
		
		if(board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) 
                {
			currentRotation = newRotation;
			currentRow = newRow;
			currentCol = newColumn;
		}
	}
	
	public boolean isPaused() 
        {
            return isPaused;
	}
	
	public boolean isGameOver() 
        {
            if(isGameOver==true)
            {
                s2.play_sound();
            }
            return isGameOver;
	}
	
	public boolean isNewGame() 
        {
		return isNewGame;
	}
        
        
	//Encapsulation
	public int getScore() 
        {
		return score;
	}
	
	public int getLevel() 
        {
		return level;
	}
	
	public Tile getPieceType() 
        {
		return currentType;
	}
	
	public Tile getNextPieceType1() 
        {
		return nextType1;
	}
        public Tile getNextPieceType2() 
        {
		return nextType2;
	}
        public Tile getNextPieceType3() 
        {
		return nextType3;
	}
	
	public int getPieceCol() 
        {
		return currentCol;
	}
	
	public int getPieceRow() 
        {
		return currentRow;
	}
	
	public int getPieceRotation() 
        {
		return currentRotation;
	}
        
        
        

	public static void main(String[] args) throws IOException 
        {
		Tetris_game tetris = new Tetris_game();
		tetris.startGame();
	}

}

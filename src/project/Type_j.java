package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_j extends Tile
{
    
    /**
	 * Piece TypeJ.
	 */
        	static boolean[][] t2 ={
            //first rotation = 0
	
		{
			true,	false,	false,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	true,
			false,	true,	false,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	true,
			false,	false,	true,
		},
		{
			false,	true,	false,
			false,	true,	false,
			true,	true,	false,
		}
	};

//        static Tile TypeJ = new Tile(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX), 3, 3, 2, t2 ); 
//    
        Type_j()
    {
    super(new Color(Game_Panel.COLOR_MIN, Game_Panel.COLOR_MIN, Game_Panel.COLOR_MAX), 3, 3, 2, t2 );
    }
    
}

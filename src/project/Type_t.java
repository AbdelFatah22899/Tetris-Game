package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_t extends Tile
{
    	/**
	 * Piece TypeT.
	 */
                static boolean[][] t6 = {
            //first rotation = 0
		{
			false,	true,	false,
			true,	true,	true,
			false,	false,	false,
		},
            
		{
			false,	true,	false,
			false,	true,	true,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	true,
			false,	true,	false,
		},
		{
			false,	true,	false,
			true,	true,	false,
			false,	true,	false,
		}
	};

        Type_t()
    {
    super(new Color(128, Game_Panel.COLOR_MIN, 128), 3, 3, 2, t6 );
    }
    
}

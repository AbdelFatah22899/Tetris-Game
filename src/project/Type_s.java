package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_s extends Tile
{
    	/**
	 * Piece TypeS.
	 */
        
                static boolean[][] t5 = {
            //first rotation = 0
		{
			false,	true,	true,
			true,	true,	false,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	true,
			false,	false,	true,
		},
		{
			false,	false,	false,
			false,	true,	true,
			true,	true,	false,
		},
		{
			true,	false,	false,
			true,	true,	false,
			false,	true,	false,
		}
	};

        Type_s()
    {
    super(new Color(Game_Panel.COLOR_MIN, Game_Panel.COLOR_MAX, Game_Panel.COLOR_MIN), 3, 3, 2, t5 );
    }
}

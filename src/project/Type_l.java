package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_l extends Tile
{
    /**
	 * Piece TypeL.
	 */
                static boolean[][] t3 = {
            //first rotation = 0
	
		{
			false,	false,	true,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	false,
			false,	true,	true,
		},
		{
			false,	false,	false,
			true,	true,	true,
			true,	false,	false,
		},
		{
			true,	true,	false,
			false,	true,	false,
			false,	true,	false,
		}
	};
	
       Type_l()
    {
    super(new Color(Game_Panel.COLOR_MAX, 127, Game_Panel.COLOR_MIN), 3, 3, 2, t3 );
    }
    
}

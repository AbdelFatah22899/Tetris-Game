package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_o extends Tile
{
    /**
	 * Piece TypeO.
	 */
                static boolean[][] t4 = {
            //first rotation = 0
	
		{
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		},
		{	
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		}
	};
	
        Type_o()
    {
    super(new Color(Game_Panel.COLOR_MAX, Game_Panel.COLOR_MAX, Game_Panel.COLOR_MIN), 2, 2, 2, t4 );
    }
}

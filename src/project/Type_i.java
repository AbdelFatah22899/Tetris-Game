package project;

import java.awt.Color;

public class Type_i extends Tile
{
          /**
	 * Piece TypeI.
	 */

    static boolean[][]t1 = { {
			false,	false,	false,	false,
			true,	true,	true,	true,
			false,	false,	false,	false,
			false,	false,	false,	false,
		},
		{
			false,	false,	true,	false,
			false,	false,	true,	false,
			false,	false,	true,	false,
			false,	false,	true,	false,
		},
		{
			false,	false,	false,	false,
			false,	false,	false,	false,
			true,	true,	true,	true,
			false,	false,	false,	false,
		},
		{
			false,	true,	false,	false,
			false,	true,	false,	false,
			false,	true,	false,	false,
			false,	true,	false,	false,
		}
	};

    Type_i()
    {
    super(new Color(Game_Panel.COLOR_MIN, Game_Panel.COLOR_MAX, Game_Panel.COLOR_MAX), 4, 4, 1, t1 );
    }
}
	
	

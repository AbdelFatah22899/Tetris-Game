package project;

import java.awt.Color;
import static project.Type_i.t1;

public class Type_z extends Tile
{
    	/**
	 * Piece TypeZ.
	 */
                static boolean[][] t7 = {
            //first rotation = 0
		{
			true,	true,	false,
			false,	true,	true,
			false,	false,	false,
		},
            //second rotation = 1
		{
			false,	false,	true,
			false,	true,	true,
			false,	true,	false,
		},
                //third rotation = 2
		{
			false,	false,	false,
			true,	true,	false,
			false,	true,	true,
		},
                //fourth rotation = 3
		{
			false,	true,	false,
			true,	true,	false,
			true,	false,	false,
		}
	};

        Type_z()
    {
    super(new Color(Game_Panel.COLOR_MAX, Game_Panel.COLOR_MIN, Game_Panel.COLOR_MIN), 3, 3, 2, t7 );
    }
    
}

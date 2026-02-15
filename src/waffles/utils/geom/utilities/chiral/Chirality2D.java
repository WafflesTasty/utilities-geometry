package waffles.utils.geom.utilities.chiral;

import waffles.utils.geom.spatial.maps.data.spin.Spin2D;
import waffles.utils.lang.utilities.enums.Sign;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Chirality2D} implements a two-dimensional {@code Chirality}.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Chirality
 */
public class Chirality2D extends Chirality
{	
	private Spin2D spin;
	
	/**
	 * Creates a new {@code Chirality2D}.
	 * 
	 * @param d  an xy dial
	 * 
	 * 
	 * @see Dial
	 */
	public Chirality2D(Dial d)
	{
		super(d);
	}

	
	@Override
	public Spin2D Spin()
	{
		if(spin == null)
		{
			Sign sgn = Dials()[0].Sign();

			float a = Floats.PI / 4;
			float s = sgn.Value();
			
			spin = new Spin2D(a * s);
		}

		return spin;
	}
}
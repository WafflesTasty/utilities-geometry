package waffles.utils.geom.utilities.chiral;

import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.spatial.maps.data.spin.Spin3D;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Chirality3D} implements a three-dimensional {@code Chirality}.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Chirality
 */
public class Chirality3D extends Chirality
{
	private Spin3D spin;
	
	/**
	 * Creates a new {@code Chirality3D}.
	 * 
	 * @param d1  an xy dial
	 * @param d2  an xz dial
	 * 
	 * 
	 * @see Dial
	 */
	public Chirality3D(Dial d1, Dial d2)
	{
		super(d1, d2);
	}

	
	@Override
	public Spin3D Spin()
	{
		if(spin == null)
		{
			float a = Floats.PI / 4;

			float s1 = Dials()[0].Sign().Value();
			float s2 = Dials()[1].Sign().Value();
			
			Vector3 z = Vector3.Z_AXIS.times(s1);
			Vector3 x = Vector3.X_AXIS.times(s2);
			
			Spin3D xy = new Spin3D(z, a);
			Spin3D yz = new Spin3D(x, a);
			
			spin = yz.compose(xy);			
		}

		return spin;
	}
}
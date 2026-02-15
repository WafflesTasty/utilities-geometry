package waffles.utils.geom.utilities.chiral;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.geom.spatial.maps.data.spin.SpinND;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code ChiralityND} implements an n-dimensional {@code Chirality}.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Chirality
 */
public class ChiralityND extends Chirality
{
	private SpinND spin;

	/**
	 * Creates a new {@code ChiralityND}.
	 * 
	 * @param d  a dial set
	 * 
	 * 
	 * @see Dial
	 */
	public ChiralityND(Dial... d)
	{
		super(d);
	}

	
	@Override
	public SpinND Spin()
	{
		if(spin == null)
		{
			int d = Dials().length;
			float a = Floats.PI / 4;

			float sin = Floats.sin(a);
			float cos = Floats.cos(a);
			
			Matrix m = Matrices.identity(d);
			m.setOperator(Identity.Type());
			for(int i = 0; i < d; i++)
			{
				Matrix n = Matrices.create(d, d);
				float sgn = Dials()[i].Sign().Value();
				
				n.set( 		 cos, i + 0, i + 0);
				n.set(+sgn * sin, i + 1, i + 0);
				n.set(-sgn * sin, i + 0, i + 1);
				n.set( 		 cos, i + 1, i + 1);
				
				for(int j = 0; j < d; j++)
				{
					if(j < i || i + 1 < j)
					{
						n.set(1f, j, j);
					}
				}
				
				m = n.times(m);
			}
			
			spin = new SpinND(m);
		}
		
		return spin;
	}
}
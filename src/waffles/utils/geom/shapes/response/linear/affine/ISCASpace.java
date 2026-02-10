package waffles.utils.geom.shapes.response.linear.affine;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.linear.ISCLSpace;
import waffles.utils.geom.shapes.response.linear.ISCLSpace.Hints;

/**
 * An {@code ISCASpace} computes an intersection {@code Response} between affine spaces.
 *
 * @author Waffles
 * @since 11 Dec 2025
 * @version 1.1
 *
 * 
 * @see ISCLSpace
 */
public class ISCASpace extends ISCLSpace
{
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCASpace(Hints h)
	{
		super(h);
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see ASpace
	 */
	public ISCASpace(ASpace s, ASpace t)
	{
		this(new Hints()
		{
			@Override
			public ASpace S()
			{
				return s;
			}
			
			@Override
			public ASpace T()
			{
				return t;
			}
		});
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			Point y = Y();
			Matrix v = Direction();
			
			return ASpace.create(y, v);
		}

		int n = Dimension();
		return new Void(n);
	}
}

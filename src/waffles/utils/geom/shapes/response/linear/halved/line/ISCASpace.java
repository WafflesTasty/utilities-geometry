package waffles.utils.geom.shapes.response.linear.halved.line;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.linear.ISCLSpace;

/**
 * An {@code ISCASpace} computes an intersection {@code Response} between a halfline and an affine space.
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
	 * @param s  a source line
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 * @see HLine
	 */
	public ISCASpace(HLine s, ASpace t)
	{
		super(s, t);
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			Point y = Y();
			Matrix v = Direction();
			
			return HLine.create(y, v);
		}

		int dim = Dimension();
		return new Void(dim);
	}
	
	@Override
	public Vector L()
	{
		Vector l = super.L();
		if(l.get(0) < 0f)
		{
			l.set(0f, 0);
		}

		return l;
	}
}
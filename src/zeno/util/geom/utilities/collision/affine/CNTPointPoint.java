package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Containment;

/**
 * The {@code CNTPointPoint} defines containment between two {@code Point} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Containment
 */
public class CNTPointPoint implements Containment
{	
	@Override
	public boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Point p1 = (Point) c1;
		Point p2 = (Point) c2;
		
		
		Vector v1 = p1.VMatrix();
		Vector v2 = p2.VMatrix();

		return v1.equals(v2, 2 * (ulps + 1));
	}

	@Override
	public boolean contains(ICollidable c1, ICollidable c2)
	{
		Point p1 = (Point) c1;
		Point p2 = (Point) c2;
		
		
		return p1.equals(p2, 2);
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Point.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Point.TYPE;
	}
}
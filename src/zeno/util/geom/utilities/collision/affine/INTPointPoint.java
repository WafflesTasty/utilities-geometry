package zeno.util.geom.utilities.collision.affine;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;

/**
 * The {@code INTPointPoint} defines intersection between two {@code Point} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTPointPoint implements Intersection
{	
	@Override
	public ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Point p1 = (Point) c1;
		Point p2 = (Point) c2;
		
		
		if(!p1.equals(p2, 2))
		{
			int dim = p1.VMatrix().Rows();
			return ASpaces.trivial(dim);
		}
		
		return p1;
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
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
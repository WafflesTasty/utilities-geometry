package zeno.util.geom.collidables.collisions.affine;

import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.HSpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;

/**
 * The {@code CLSHSpace} class defines collision for an {@code Affine} {@link HSpace}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSHSpace implements ICollision
{
	private HSpace s;
	
	/**
	 * Creates a new {@code CLSHSpace}.
	 * 
	 * @param space  a source space
	 * 
	 * 
	 * @see HSpace
	 */
	public CLSHSpace(HSpace space)
	{
		this.s = space;
	}

		
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof HSpace)
		{
			HSpace t = (HSpace) c;
			
			
			Point p = s.Origin();
			Point q = t.Origin();
			
			VSpace n1 = s.Normal();
			VSpace n2 = t.Normal();
			
			return n1.equals(n2, ulps)
				&& t.contains(p)
				&& s.contains(q);
		}
		
		return null;
	}

	@Override
	public Boolean contains(ICollidable c)
	{		
		if(c instanceof Point)
		{
			Point p = (Point) c;
			
			Vector pq = Geometries.subtract(p, s.Origin());
			return pq.dot(p.asVector()) >= 0;			
		}
		
		return null;
	}
}
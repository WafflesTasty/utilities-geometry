package zeno.util.geom.collidables.collisions.affine;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSPoint} class defines collision for a {@link Point}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSPoint implements ICollision
{
	private Point p;
	
	/**
	 * Creates a new {@code CLSPoint}.
	 * 
	 * @param p  a source point
	 * 
	 * 
	 * @see Point
	 */
	public CLSPoint(Point p)
	{
		this.p = p;
	}
	
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		if(c.contains(p))
		{
			return p;
		}
		
		return Geometries.VOID;
	}
					
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Point)
		{
			Vector vp = p.asVector();
			Vector vc = ((Point) c).asVector();	
			float norm = vp.minus(vc).normSqr();
			
			return Floats.isZero(norm, (4 + vp.Size()) / 2 * ulps);
		}

		return null;
	}

	@Override
	public Boolean intersects(ICollidable c)
	{
		return c.contains(p);
	}
	
	@Override
	public Boolean contains(ICollidable c)
	{
		return equals(c, 2);
	}
}
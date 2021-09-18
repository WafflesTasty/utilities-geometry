package zeno.util.geom.collidables.collisions;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
import zeno.util.geom.collidables.geometry.generic.IHull;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSVoid} class defines collision for an empty object.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSVoid extends CLSHull
{
	/**
	 * The {@code RSPVoid} class defines an empty collision response.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPVoid implements Response
	{
		@Override
		public boolean isEmpty()
		{
			return true;
		}
		
		@Override
		public ICollidable Shape()
		{
			return Geometries.VOID;
		}

		@Override
		public Vector Penetration()
		{
			return null;
		}
		
		@Override
		public Vector Distance()
		{
			int dim = Source().Dimension();
			return Vectors.create(dim);
		}
	}
	
	
	/**
	 * Creates a new {@code CLSVoid}.
	 */
	public CLSVoid()
	{
		super(null);
	}

	
	@Override
	public Boolean contains(ICollidable c)
	{
		return false;
	}

	@Override
	public Boolean inhabits(ICollidable c)
	{
		return true;
	}
	
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		return c.equals(Geometries.VOID);
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		return new RSPVoid();
	}
	
	@Override
	public Response contain(Point p)
	{
		return new RSPVoid();
	}
	
	@Override
	protected IHull Source()
	{
		return Geometries.VOID;
	}
}
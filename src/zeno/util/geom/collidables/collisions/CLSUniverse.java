package zeno.util.geom.collidables.collisions;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSUniverse} class defines collision for a universal object.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSUniverse extends CLSGeometry
{
	/**
	 * The {@code RSPUniverse} class defines a universal collision response.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPUniverse implements Response
	{
		private ICollidable c;
		
		/**
		 * Creates a new {@code ICollidable}.
		 * 
		 * @param c  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public RSPUniverse(ICollidable c)
		{
			this.c = c;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return false;
		}
		
		@Override
		public ICollidable Shape()
		{
			return c;
		}

		@Override
		public Vector Penetration()
		{
			int dim = Source().Dimension();
			return Vectors.create(dim);
		}
		
		@Override
		public Vector Distance()
		{
			return null;
		}
	}
	
	
	/**
	 * Creates a new {@code CLSVoid}.
	 */
	public CLSUniverse()
	{
		super(null);
	}

	
	@Override
	public Boolean contains(ICollidable c)
	{
		return true;
	}

	@Override
	public Boolean inhabits(ICollidable c)
	{
		return false;
	}
	
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		return c.equals(Geometries.UNIVERSE);
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		return new RSPUniverse(c);
	}
	
	@Override
	public Response contain(Point p)
	{
		return new RSPUniverse(p);
	}
	
	@Override
	protected IGeometry Source()
	{
		return Geometries.UNIVERSE;
	}
}
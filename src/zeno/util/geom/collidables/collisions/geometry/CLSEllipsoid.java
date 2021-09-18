package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.Affine;
import zeno.util.geom.AffineMap;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.ICollision.Response;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.convex.CLSConvex;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.Geometries;
import zeno.util.geom.utilities.Transforms;
import zeno.util.tools.Floats;

/**
 * The {@code CLSEllipsoid} class defines collision for an {@code IEllipsoid}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSEllipsoid extends CLSConvex
{	
	/**
	 * The {@code RSPAffine} class defines collision response with an affine space.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Response
	 */
	public class RSPAffine implements Response
	{
		private Affine space;
		private ICollidable shape;
		private Response response;
		
		/**
		 * Creates a new {@code RSPAffine}.
		 * 
		 * @param space  an affine space
		 */
		public RSPAffine(Affine space)
		{
			this.space = space;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Response().isEmpty();
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				IEllipsoid e = Source();
				AffineMap map = Transforms.elliptic(e);
				response = Response();
				
				shape = response.Shape();
				if(shape instanceof ISegment)
					shape = map.map((ISegment) shape);
				if(shape instanceof Point)
					shape = map.map((Point) shape);
			}
			
			return shape;
		}

		@Override
		public Vector Penetration()
		{
			return null;
		}
		
		@Override
		public Vector Distance()
		{
			return null;
		}
		
		
		private Response Response()
		{
			if(response == null)
			{
				IEllipsoid e = Source();
				AffineMap map = Transforms.elliptic(e);
				ISphere sphere = Geometries.sphere(e.Dimension());

				// Perform intersection in unit sphere space.
				response = sphere.intersect(map.unmap(space));
			}
			
			return response;
		}
	}
	
	/**
	 * The {@code RSPPoint} class defines collision response for a point.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPPoint implements Response
	{
		private Point p;
		private Boolean isEmpty;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public RSPPoint(Point p)
		{
			this.p = p;
		}
		
		
		@Override
		public boolean isEmpty()
		{			
			if(isEmpty == null)
			{
				IEllipsoid e = Source();
				float m = p.Mass();
				
				
				float val, sum = 0;
				for(int i = 0; i < e.Dimension(); i++)
				{
					val = p.get(i) - m * e.Center().get(i);
					
					if(!Floats.isZero(val, 1))
					{
						val /= e.Size().get(i);
						
						sum += val * val;
						if(4 * sum > m * m)
						{
							isEmpty = true;
							return isEmpty;
						}
					}
				}
				
				isEmpty = false;
			}
			
			return isEmpty;
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;
			return p;
		}
		
		@Override
		public Vector Penetration()
		{
			return null;
		}

		@Override
		public Vector Distance()
		{
			return null;
		}
	}
	
	
	/**
	 * Creates a new {@code CLSEllipsoid}.
	 * 
	 * @param s  a source ellipsoid
	 * 
	 * 
	 * @see IEllipsoid
	 */
	public CLSEllipsoid(IEllipsoid s)
	{
		super(s);
	}
	

	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			return contains((ISphere) c);
		}
		
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return contains((IEllipsoid) c);
		}
		
		return super.contains(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return intersects((ICuboid) c);
		}
				
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return intersects((IEllipsoid) c);
		}
		
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			return c.intersects(Source());
		}
		
		return super.intersects(c);
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate affine spaces.
		if(c instanceof Line)
			return intersect((Affine) c);
		if(c instanceof ISegment)
			return intersect((Affine) c);

		return super.intersect(c);
	}
	
	@Override
	public Response contain(Point p)
	{
		return new RSPPoint(p);
	}
	
	
	private boolean intersects(IEllipsoid f)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipsoid to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		Point p = (Point) map.unmap(new Point(f.Center(), 1f));
		Point s = (Point) map.unmap(new Point(f.Size(), 0f));
		Vector q = p.asVector(); Vector t = s.asVector();
		
		IEllipsoid g = Geometries.ellipsoid(q, t);
		return sphere.intersects(g);
	}
		
	private boolean contains(IEllipsoid f)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipsoid to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform containment in unit sphere space.
		Point p = (Point) map.unmap(new Point(f.Center(), 1f));
		Point s = (Point) map.unmap(new Point(f.Size(), 0f));
		Vector q = p.asVector(); Vector t = s.asVector();
		
		IEllipsoid g = Geometries.ellipsoid(q, t);
		return sphere.contains(g);
	}
	
	private boolean contains(ISphere s)
	{
		throw new Geometries.IntersectingError(Source(), s);
	}

	@Override
	protected IEllipsoid Source()
	{
		return (IEllipsoid) super.Source();
	}
}
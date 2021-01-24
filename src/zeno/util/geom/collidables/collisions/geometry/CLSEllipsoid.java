package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.AffineMap;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.CLSGeometry;
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
 * @see CLSGeometry
 */
public class CLSEllipsoid extends CLSGeometry
{	
	private CLSConvex convex;
	
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
		super(s); convex = new CLSConvex(s);
	}
	
	
	@Override
	protected boolean contains(Point x)
	{
		IEllipsoid e = Source();
		float m = x.Mass();
		
		
		float val, sum = 0;
		for(int i = 0; i < e.Dimension(); i++)
		{
			val = x.get(i) - m * e.Center().get(i);
			
			if(!Floats.isZero(val, 1))
			{
				val /= e.Size().get(i);
				
				sum += val * val;
				if(4 * sum > m * m)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	protected IEllipsoid Source()
	{
		return (IEllipsoid) super.Source();
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate base collision cases.
		Boolean cnt = super.contains(c);
		if(cnt != null)
		{
			return cnt;
		}
		
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
		
		return convex.contains(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		// Eliminate base collision cases.
		Boolean isect = super.intersects(c);
		if(isect != null)
		{
			return isect;
		}

		// Eliminate affine lines.
		if(c instanceof Line)
		{
			return intersects((Line) c);
		}

		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersects((ISegment) c);
		}
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return intersects((ICuboid) c);
		}
		
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			return c.intersects(Source());
		}
		
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return intersects((IEllipsoid) c);
		}
		
		return convex.intersects(c);
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		// Eliminate base collision cases.
		ICollidable isect = super.intersect(c);
		if(isect != null)
		{
			return isect;
		}

		// Eliminate affine lines.
		if(c instanceof Line)
		{
			return intersect((Line) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersect((ISegment) c);
		}
		
		return convex.intersect(c);
	}
	
	
	private ICollidable intersect(ISegment t)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipse to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		ICollidable c = sphere.intersect(map.unmap(t));
		
		// If no intersection occurs...
		if(c.equals(Geometries.VOID))
		{
			// Return the empty intersection.
			return c;
		}
		
		// If the intersection is a point...
		if(c instanceof Point)
		{
			// Map it back to elliptic space.
			return map.map((Point) c);
		}
		
		// If the intersection is a line...
		if(c instanceof ISegment)
		{
			// Map it back to elliptic space.
			return map.map((ISegment) c);
		}
		
		return null;
	}
			
	private ICollidable intersect(Line l)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipse to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		ICollidable c = sphere.intersect(map.unmap(l));
		
		// If no intersection occurs...
		if(c.equals(Geometries.VOID))
		{
			// Return the empty intersection.
			return c;
		}
		
		// If the intersection is a point...
		if(c instanceof Point)
		{
			// Map it back to elliptic space.
			return map.map((Point) c);
		}
		
		// If the intersection is a line...
		if(c instanceof ISegment)
		{
			// Map it back to elliptic space.
			return map.map((ISegment) c);
		}
		
		return null;
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
	
	private boolean intersects(ISegment t)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipse to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		return sphere.intersects(map.unmap(t));
	}
	
	private boolean intersects(ICuboid c)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipse to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		Point p = (Point) map.unmap(new Point(c.Center(), 1f));
		Point s = (Point) map.unmap(new Point(c.Size(), 0f));
		Vector q = p.asVector(); Vector t = s.asVector();
		
		ICuboid d = Geometries.cuboid(q, t);
		return sphere.intersects(d);
	}
	
	private boolean intersects(Line l)
	{
		IEllipsoid e = Source();
		
		
		// Map the ellipse to the unit sphere space.
		AffineMap map = Transforms.elliptic(Source());
		ISphere sphere = Geometries.sphere(e.Dimension());
		// Perform intersection in unit sphere space.
		return sphere.intersects(map.unmap(l));
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
}
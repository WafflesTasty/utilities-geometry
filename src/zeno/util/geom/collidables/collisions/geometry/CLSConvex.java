package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.IConvex;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSConvex} class defines collision for an {@code IConvex}.
 *
 * @author Zeno
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSConvex extends CLSGeometry
{
	private static final int ULPS = 3;
	
	
	/**
	 * Creates a new {@code CLSConvex}.
	 * 
	 * @param src  a source convex
	 * 
	 * 
	 * @see IConvex
	 */
	public CLSConvex(IConvex src)
	{
		super(src);
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
		
		// Eliminate convex geometry.
		if(c instanceof IConvex)
		{
			return intersects((IConvex) c);
		}
		
		// Eliminate shapeable geometry.
		if(c instanceof IShapeable)
		{
			return intersects((IShapeable) c);
		}
		
		return null;
	}
	
	@Override
	protected boolean contains(Point p)
	{
		Vector q = p.asVector();
		Vector x = Source().Center();
		
		while(!Floats.isZero(x.minus(q).normSqr(), ULPS))
		{
			Vector y = Source().Extremum(q.minus(x));
			
			Vector u = x.minus(y); Vector v = x.minus(q);
			if(Floats.isZero(u.dot(v), Source().Dimension()))
			{
				return false;
			}
			
			Vector w = y.minus(q);
			if(v.dot(w) > 0)
			{
				return false;
			}
			
			
			Vector d = w.minus(v);
			d = d.times(v.dot(v.minus(w)));
			d = d.times(1f / w.minus(v).normSqr());
			
			x = x.plus(d);
		}
		
		return true;
	}
	
	@Override
	protected IConvex Source()
	{
		return (IConvex) super.Source();
	}
	
	
	private Boolean intersects(IShapeable s)
	{
		if(s.Shape() instanceof IConvex)
		{
			return intersects(Geometries.convex(s));
		}
		
		return null;
	}
	
	private boolean intersects(IConvex c)
	{
		return Geometries.diff(c, Source()).contains(new Point(0f));
	}
}
package zeno.util.geom.collidables.collisions;

import zeno.util.geom.Affine;
import zeno.util.geom.ICollidable;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.collidables.geometry.generic.IConvex;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSShaped} class defines collision for a shaped object.
 *
 * @author Zeno
 * @since Jul 25, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSShaped implements ICollision
{
	private IGeometrical src;
	
	/**
	 * Creates a new {@code CLSShaped}.
	 * 
	 * @param s  a geometrical source
	 * 
	 * 
	 * @see IGeometrical
	 */
	public CLSShaped(IGeometrical s)
	{
		src = s;
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		ITransformation tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			return shape.contains(tform.unmap((ISegment) c));
		}
		
		if(c instanceof Affine)
		{		
			if(!c.equals(Geometries.VOID))
			{
				return shape.contains(tform.unmap((Affine) c));
			}
			
			return true;
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		ITransformation tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			return shape.intersects(tform.unmap((ISegment) c));
		}
		
		if(c instanceof Affine)
		{
			if(!c.equals(Geometries.VOID))
			{
				return shape.intersects(tform.unmap((Affine) c));
			}
			
			return false;
		}
		
		if(c instanceof IConvex)
		{
			return c.intersects(src);
		}
		
		if(c instanceof IShapeable)
		{
			IConvex cvx = Geometries.convex((IShapeable) c);
			return cvx.intersects(src);
		}
		
		return null;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		ITransformation tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			ICollidable s = shape.intersect(tform.unmap((ISegment) c));
			if(s instanceof Affine)
			{
				return tform.map((Affine) s);
			}
			
			if(s.equals(Geometries.VOID))
			{
				return s;
			}
		}
		
		if(c instanceof Affine)
		{
			if(c.equals(Geometries.VOID))
			{
				return Geometries.VOID;
			}
			
			ICollidable s = shape.intersect(tform.unmap((Affine) c));
			if(s instanceof Affine)
			{
				return tform.map((Affine) s);
			}
			
			if(s.equals(Geometries.VOID))
			{
				return s;
			}
		}
		
		return null;
	}
}
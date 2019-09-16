package zeno.util.geom.collidables.collisions;

import zeno.util.geom.Affine;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSShaped} class defines collision for an {@link IShapeable}.
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
	private IShapeable src;
	
	/**
	 * Creates a new {@code CLSShaped}.
	 * 
	 * @param s  a shapeable source
	 * 
	 * 
	 * @see IShapeable
	 */
	public CLSShaped(IShapeable s)
	{
		src = s;
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		AffineMap tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			return shape.contains(tform.unmap((ISegment) c));
		}
		
		if(c instanceof Affine)
		{			
			return shape.contains(tform.unmap((Affine) c));
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		AffineMap tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			return shape.intersects(tform.unmap((ISegment) c));
		}
		
		if(c instanceof Affine)
		{
			return shape.intersects(tform.unmap((Affine) c));
		}
		
		return null;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		AffineMap tform = src.Transform();
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
package waffles.utils.geom.shapes.bounds;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code BNDGeometry} defines dynamic {@code Bounds} for a {@code Geometry}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Bounds
 */
public interface BNDGeometry extends Bounds
{	
	/**
	 * Returns the map of the {@code Bounds}.
	 * 
	 * @return  a linear map
	 * 
	 * 
	 * @see LinearMap
	 */
	public default LinearMap Map()
	{
		return null;
	}
	
	/**
	 * Returns the geometry of the {@code Bounds}.
	 * 
	 * @return  a source geometry
	 * 
	 * 
	 * @see Geometry
	 */
	public abstract Geometry Geometry();
	
	
	@Override
	public default Point Origin()
	{
		Point o = Geometry().Origin();
		if(Map() != null)
		{
			o = (Point) Map().map(o);
		}
		
		return o;
	}
}
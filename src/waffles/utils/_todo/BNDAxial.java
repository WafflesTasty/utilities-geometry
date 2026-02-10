package waffles.utils.geom.shapes.bounds.convex.axial;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.BNDGeometry;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.shapes.points.Arrow;

/**
 * A {@code BNDAxial} defines dynamic {@code Bounds} for an {@code AxialSet}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see BNDGeometry
 */
public class BNDAxial implements BNDGeometry
{
	private AxialSet src;
	private LinearMap map;
	
	/**
	 * Creates a new {@code BNDAxial}.
	 * 
	 * @param s  an axial set
	 * 
	 * 
	 * @see AxialSet
	 */
	public BNDAxial(AxialSet s)
	{
		src = s;
	}
	
	/**
	 * Creates a new {@code BNDAxial}.
	 *
	 * @param s  an axial set
	 * @param m  a linear map
	 *
	 *
	 * @see LinearMap
	 * @see AxialSet
	 */
	public BNDAxial(AxialSet s, LinearMap m)
	{
		src = s;
		map = m;
	}
	
	
	@Override
	public LinearMap Map()
	{
		return map;
	}
	
	@Override
	public AxialSet Geometry()
	{
		return src;
	}
	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Arrow Scale()
	{
		Arrow s = Geometry().Scale();
		if(Map() != null)
		{
			s = (Arrow) Map().map(s);
		}
		
		return s;
	}
}
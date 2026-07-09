package waffles.utils.geom.spatial.maps;

import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.tform.LazyConstant;

/**
 * An {@code ConstantMap} defines a constant valued map.
 * Its inverse is undefined and always returns null.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class ConstantMap implements GlobalMap
{
	private Point cns;
	private LazyConstant map;
	
	/**
	 * Creates a new {@code ConstantMap}.
	 * 
	 * @param c  a point constant
	 * 
	 * 
	 * @see Point
	 */
	public ConstantMap(Point c)
	{
		map = new LazyConstant(c);
	}
	
	/**
	 * Returns a constant {@code Point}.
	 * 
	 * @return  a point constant
	 * 
	 * 
	 * @see Point
	 */
	public Point Constant()
	{
		return cns;
	}
	
		
	@Override
	public Affine unmap(Affine a)
	{
		return null;
	}
	
	@Override
	public Affine map(Affine a)
	{
		return cns;
	}
	
		
	@Override
	public LazyMatrix UTW()
	{
		return map;
	}
	
	@Override
	public LazyMatrix WTU()
	{
		return null;
	}
}
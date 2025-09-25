package waffles.utils.geom.bounds.axial.cuboid;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code BNDCuboid} defines {@code Bounds} for a transformed {@code HyperCuboid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 * 
 *
 * @see Bounds
 */
public class BNDCuboid implements Bounds
{
	private HyperCuboid src;
	private GlobalMap map;
	
	/**
	 * Creates a new {@code BNDCuboid}.
	 * 
	 * @param c  a cuboid
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see HyperCuboid
	 */
	public BNDCuboid(HyperCuboid c, GlobalMap m)
	{
		src = c;
		map = m;
	}
	
	
	@Override
	public float Radius()
	{
		int d = Dimension();
		Vector s = src.Scale();
		
		Matrix a = map.Matrix(d + 1);
		a = a.resize(d, d).times(s);
		return a.norm() / 2;
	}
	
	@Override
	public Vector Center()
	{
		Vector o = src.Origin();
		Point p = new Point(o, 1f);
		p = (Point) map.map(p);
		return p.Generator();
	}
	
	@Override
	public Vector Size()
	{
		int d = Dimension();
		Vector s = src.Scale();
		
		Matrix a = map.Matrix(d + 1);
		a = a.resize(d, d).absolute();
		return a.times(s);
	}
}
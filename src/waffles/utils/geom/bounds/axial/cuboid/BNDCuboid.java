package waffles.utils.geom.bounds.axial.cuboid;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
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
		int dim = Dimension();
		Vector s = src.Scale();
		
		Matrix a = map.Matrix(dim+1);
		a = Matrices.resize(a, dim, dim);
		return a.times(s).norm() / 2;
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
		int dim = Dimension();
		Vector s = src.Scale();
		
		Matrix a = map.Matrix(dim+1);
		a = Matrices.resize(a, dim, dim);
		return a.absolute().times(s);
	}
}
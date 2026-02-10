package waffles.utils.geom.shapes.bounds.convex.axial.cuboid;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.BNDAxial;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;

/**
 * A {@code BNDCuboid} defines dynamic {@code Bounds} for a {@code CuboidSet}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDAxial
 */
public class BNDCuboid extends BNDAxial
{
	/**
	 * Creates a new {@code BNDCuboid}.
	 *
	 * @param s  an axial set
	 * @param m  a linear map
	 *
	 *
	 * @see HyperCuboid
	 * @see LinearMap
	 */
	public BNDCuboid(HyperCuboid s, LinearMap m)
	{
		super(s, m);
	}

	/**
	 * Creates a new {@code BNDCuboid}.
	 *
	 * @param s  an axial set
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public BNDCuboid(HyperCuboid s)
	{
		super(s);
	}

	
	@Override
	public float Diameter()
	{
		return Scale().norm();
	}
	
	@Override
	public HyperCuboid Geometry()
	{
		return (HyperCuboid) super.Geometry();
	}

	@Override
	public Arrow Scale()
	{
		HyperCuboid src = Geometry();
		LinearMap map = Map();
		Arrow s = src.Scale();

		if(map == null)
		{
			return s;
		}
		
		int d = Dimension();
		Vector v = s.Vector();
		Matrix a = map.Matrix(d + 1);
		a = a.resize(d, d).absolute();
		v = a.times(v);
		
		return new Arrow(v);
	}
}
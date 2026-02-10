package waffles.utils.geom.shapes.bounds.convex.axial.spheroid;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.banded.Diagonal;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.convex.axial.BNDAxial;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDSpheroid} defines dynamic {@code Bounds} for a {@code HyperSpheroid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDAxial
 */
public class BNDSpheroid extends BNDAxial
{
	/**
	 * Creates a new {@code BNDSpheroid}.
	 *
	 * @param s  a spheroid
	 * @param m  a linear map
	 *
	 *
	 * @see HyperSpheroid
	 * @see LinearMap
	 */
	public BNDSpheroid(HyperSpheroid s, LinearMap m)
	{
		super(s, m);
	}
	
	/**
	 * Creates a new {@code BNDSpheroid}.
	 *
	 * @param s  a spheroid
	 *
	 *
	 * @see HyperSpheroid
	 */
	public BNDSpheroid(HyperSpheroid s)
	{
		super(s);
	}


	@Override
	public float Diameter()
	{
		float d = 0f;
		Point s = Scale();
		for(int k = 0; k < s.Dimension(); k++)
		{
			float v = s.aff(k);
			if(d < v)
			{
				d = v;
			}
		}
		
		return d;
	}

	@Override
	public Arrow Scale()
	{
		AxialSet src = Geometry();
		LinearMap map = Map();
		Arrow s = src.Scale();
		if(map == null)
		{
			return s;
		}
		
		Point p = s.times(0.5f);
		Vector d = p.Vector();
		Matrix e = Matrices.diagonal(d);
		e.setOperator(Diagonal.Type());


		int dim = Dimension();
		Matrix a = map.Matrix(dim+1);

		a = a.resize(dim, dim);
		a = a.times(a.transpose());
		a = e.times(a).times(e);


		Vector v = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			float val = Floats.sqrt(a.get(i, i));
			v.set(2 * val, i);
		}

		return new Arrow(v);
	}
}
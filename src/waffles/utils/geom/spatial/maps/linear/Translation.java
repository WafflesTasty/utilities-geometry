package waffles.utils.geom.spatial.maps.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.utilities.tform.linear.trx.Translator;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Translation} defines a linear map which
 * translates vectors in a single direction.
 * 
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see Positioned
 * @see LinearMap
 */
public class Translation implements LinearMap, Positioned
{
	/**
	 * Returns a default translation {@code Vector}.
	 * 
	 * @param dim  a space dimension
	 * @return  a default vector
	 * 
	 * 
	 * @see Vector
	 */
	public static Point Default(int dim)
	{
		return new Point(dim);
	}


	private Positioned src;
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param dim  a default dimension
	 */
	public Translation(int dim)
	{
		this(Default(dim));
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Translation(Vector o)
	{
		this(new Point(o, 1f));
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param s  a positioned source
	 * 
	 * 
	 * @see Positioned
	 */
	public Translation(Positioned s)
	{
		src = s;
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Translation(Point o)
	{
		this(() -> o);
	}
	

	@Override
	public Matrix Inverse(int dim)
	{
		Vector o = Origin().Vector();
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			if(d < Integers.min(dim-1, o.Size()))
			{
				m.set(-o.get(d), d, dim-1);
			}
		}
		
		return m;
	}

	@Override
	public Matrix Matrix(int dim)
	{
		Vector o = Origin().Vector();
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			if(d < Integers.min(dim-1, o.Size()))
			{
				m.set(o.get(d), d, dim-1);
			}
		}
		
		return m;
	}
	
	@Override
	public Point Origin()
	{
		return src.Origin();
	}
}
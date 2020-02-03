package zeno.util.geom.utilities;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.transforms.StandardMap;
import zeno.util.tools.Floats;

/**
 * The {@code Transforms} class defines static-access transformation operations.
 *
 * @author Zeno
 * @since Sep 10, 2019
 * @version 1.0
 * 
 *
 * @see ITransformation
 */
public final class Transforms
{
	/**
	 * Creates an absolute valued {@code ITransformation}.
	 * 
	 * @param t  a transformation to use
	 * @return  an absolute transformation
	 */
	public static ITransformation abs(ITransformation t)
	{
		return new ITransformation()
		{
			@Override
			public Matrix Matrix(int dim)
			{
				Matrix m1 = t.Matrix(dim);
				Matrix m2 = Matrices.create(dim + 1, dim + 1);
				for(int c = 0; c <= dim; c++)
				{
					for(int r = 0; r <= dim; r++)
					{
						m2.set(Floats.abs(m1.get(r, c)), r, c);
					}
				}
				
				return m2;
			}
			
			@Override
			public Matrix Inverse(int dim)
			{
				Matrix m1 = t.Inverse(dim);
				Matrix m2 = Matrices.create(dim + 1, dim + 1);
				for(int c = 0; c <= dim; c++)
				{
					for(int r = 0; r <= dim; r++)
					{
						m2.set(Floats.abs(m1.get(r, c)), r, c);
					}
				}
				
				return m2;
			}
		};
	}

	/**
	 * Creates an affine map of an {@code IEllipsoid}.
	 * This maps the unit sphere to the ellipsoid parameter.
	 * 
	 * @param e  an ellipsoid to map
	 * @return  an affine map
	 * 
	 * 
	 * @see IEllipsoid
	 * @see StandardMap
	 */
	public static StandardMap elliptic(IEllipsoid e)
	{
		StandardMap map = new StandardMap(e.Dimension());
		map.setSize(e.Size().times(0.5f));
		map.setOrigin(e.Center());
		return map;
	}


	private Transforms()
	{
		// NOT APPLICABLE
	}
}
package waffles.utils.geom.spatial.maps.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.banded.Diagonal;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Scaled;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Dilation} defines a linear map which scales vectors
 * in every direction. Its corresponding matrices are diagonal.
 *
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 *
 *
 * @see LinearMap
 * @see Scaled
 */
public class Dilation implements LinearMap, Scaled
{
	/**
	 * Returns a default dilation {@code Arrow}.
	 *
	 * @param dim  a space dimension
	 * @return  a default arrow
	 *
	 *
	 * @see Arrow
	 */
	public static Arrow Default(int dim)
	{
		return Arrow.create(2f, dim);
	}


	private Scaled src;
	
	/**
	 * Creates a new {@code Dilation}.
	 * The point is multiplied by two,
	 * since the map scales in both
	 * directions of the axes.
	 *
	 * @param s  a default size
	 *
	 *
	 * @see Arrow
	 */
	public Dilation(Arrow s)
	{
		src = () -> s.times(2f);
	}

	/**
	 * Creates a new {@code Dilation}.
	 * The vector is multiplied by two,
	 * since the map scales in both
	 * directions of the axes.
	 *
	 * @param s  a default size
	 *
	 *
	 * @see Vector
	 */
	public Dilation(Vector s)
	{
//		src = () -> new Point(s.times(4f), 0f);
		this(new Arrow(s));
	}

	/**
	 * Creates a new {@code Dilation}.
	 *
	 * @param s  a scaled source
	 *
	 *
	 * @see Scaled
	 */
	public Dilation(Scaled s)
	{
		src = s;
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 *
	 * @param dim  a space dimension
	 */
	public Dilation(int dim)
	{
		this(Default(dim));
	}


	@Override
	public Matrix Inverse(int dim)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		Point scale = Scale().times(0.5f);
		Matrix m = Matrices.identity(dim);
		m.setOperator(Diagonal.Type());
		int sDim = Scale().Dimension();

		for(int d = 0; d < dim; d++)
		{
			if(d < sDim)
			{
				float s = scale.aff(d);
				if(!Floats.isZero(s, 1))
				{
					m.set(1f / s, d, d);
				}
			}
		}

		return m;
	}

	@Override
	public Matrix Matrix(int dim)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		Point scale = Scale().times(0.5f);
		Matrix m = Matrices.identity(dim);
		m.setOperator(Diagonal.Type());
		int sDim = Scale().Dimension();

		for(int d = 0; d < dim; d++)
		{
			if(d < sDim)
			{
				float s = scale.aff(d);
				if(!Floats.isZero(s, 1))
				{
					m.set(s, d, d);
				}
			}
		}

		return m;
	}

	@Override
	public Arrow Scale()
	{
		return src.Scale();
	}
}
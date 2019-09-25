package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.transforms.affine.Dilation;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.tools.patterns.DirtyValue;

/**
 * The {@code AffineMap} defines a generalized affine map.
 * The implementation is provided as a homogeneous transformation.
 * It combines a translation, dilation and rotation to create a basic
 * geometric transformation for n-dimensional objects.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see ITransformation
 * @see DirtyValue
 * @see Integer
 */
public class AffineMap extends DirtyValue<Integer> implements ITransformation
{
	private Dilation dilation;
	private Translation translation;
	private Rotation rotation;
	
	private Matrix mat, inv;
	
	/**
	 * Creates a new {@code AffineMap}.
	 * 
	 * @param dim  a space dimension
	 */
	public AffineMap(int dim)
	{		
		dilation = new Dilation(dim);
		translation = new Translation(dim);
		rotation = new Rotation(dim);
	}

	@Override
	protected void update(Integer dim)
	{
		inv = translation.Inverse(dim);
		inv = rotation.Inverse(dim).times(inv);
		inv = dilation.Inverse(dim).times(inv);

		mat = dilation.Matrix(dim);
		mat = rotation.Matrix(dim).times(mat);
		mat = translation.Matrix(dim).times(mat);
	}
	
	
	/**
	 * Changes the origin of the {@code AffineMap}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Vector
	 */
	public void setOrigin(Vector o)
	{
		translation = new Translation(o);
		setChanged();
	}
	
	/**
	 * Changes the basis of the {@code AffineMap}.
	 * 
	 * @param r  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public void setBasis(Matrix r)
	{
		rotation = new Rotation(r);
		setChanged();
	}
	
	/**
	 * Changes the size of the {@code AffineMap}.
	 * 
	 * @param s  a size point
	 * 
	 * 
	 * @see Vector
	 */
	public void setSize(Vector s)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		dilation = new Dilation(s.times(0.5f));
		setChanged();
	}
	
	
	/**
	 * Returns the origin of the {@code AffineMap}.
	 * 
	 * @return  an affine origin
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Origin()
	{
		return translation.Origin().asVector();
	}
	
	/**
	 * Returns the basis of the {@code AffineMap}.
	 * 
	 * @return  an affine basis
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Basis()
	{
		return rotation.Basis();
	}
	
	/**
	 * Returns the scale of the {@code AffineMap}.
	 * 
	 * @return  an affine size
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Size()
	{
		return dilation.Size().asVector();
	}

	
	@Override
	public Matrix Inverse(int dim)
	{
		checkCache(dim);
		return inv;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		checkCache(dim);
		return mat;
	}
}
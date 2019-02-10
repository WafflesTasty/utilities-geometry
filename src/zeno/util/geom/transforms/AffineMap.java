package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.transforms.affine.Dilation;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.tools.patterns.DirtyValue;
import zeno.util.tools.patterns.properties.Copyable;

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
 * @see Copyable
 */
public class AffineMap extends DirtyValue implements Copyable<AffineMap>, ITransformation.Composite
{
	private Dilation dilation;
	private Translation translation;
	private Rotation rotation;
	
	private Matrix mat, inv;
	private int dim;
	
	/**
	 * Creates a new {@code AffineMap}.
	 * 
	 * @param dim  a target dimension
	 */
	public AffineMap(int dim)
	{
		this.dim = dim;
		
		translation = new Translation(dim);
		dilation = new Dilation(dim);
		rotation = new Rotation(dim);
	}
	
	
	/**
	 * Changes the origin of the {@code AffineMap}.
	 * 
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public void setOrigin(Vector o)
	{
		translation = new Translation(o, dim);
		setChanged();
	}
	
	/**
	 * Changes the rotation of the {@code AffineMap}.
	 * 
	 * @param r  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public void setBasis(Matrix r)
	{
		rotation = new Rotation(r, dim);
		setChanged();
	}
	
	/**
	 * Changes the size of the {@code AffineMap}.
	 * 
	 * @param v  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public void setSize(Vector v)
	{
		dilation = new Dilation(v, dim);
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
		return translation.Origin();
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
		return dilation.Size();
	}

	
	@Override
	public ITransformation[] Functions()
	{
		return new ITransformation[]
		{
			dilation,
			rotation,
			translation
		};
	}


	@Override
	protected void update()
	{
		inv = Inverse(DimOut());
		mat = Matrix(DimIn());
	}
	
	@Override
	public Matrix Inverse()
	{
		checkCache();
		return inv;
	}
	
	@Override
	public Matrix Matrix()
	{
		checkCache();
		return mat;
	}


	
	@Override
	public AffineMap instance()
	{
		return new AffineMap(dim);
	}
	
	@Override
	public AffineMap copy()
	{
		AffineMap copy = Copyable.super.copy();
		
		copy.setOrigin(translation.Origin());
		copy.setBasis(rotation.Basis());
		copy.setSize(dilation.Size());
		
		return copy;
	}

	@Override
	public int DimOut()
	{
		return dim;
	}
	
	@Override
	public int DimIn()
	{
		return dim;
	}
}
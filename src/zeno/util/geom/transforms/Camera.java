package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.transforms.affine.Dilation;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.geom.transforms.projective.Projection;
import zeno.util.tools.patterns.DirtyValue;
import zeno.util.tools.patterns.properties.Copyable;

/**
 * The {@code Camera} class defines a generalized pinhole camera.
 * The implementation is provided as a homogeneous transformation. It projects
 * an m-dimensional vector space onto an oriented n-dimensional subspace,
 * then scales it down to normalized co�rdinates. These co�rdinates
 * are assumed to fall inside the unit square of size 2.
 * 
 * @author Zeno
 * @since Dec 29, 2018
 * @version 1.0 
 * 
 * 
 * @see ITransformation
 * @see DirtyValue
 * @see Copyable
 */
public class Camera extends DirtyValue implements Copyable<Camera>, ITransformation.Composite
{
	private int iDim, oDim;
	private Rotation rotation;
	private Projection projection;
	private Translation translation;
	private Dilation dilation;
	
	private Matrix mat, inv;

	/**
	 * Creates a new {@code Camera}.
	 * 
	 * @param iDim  a source space dimension
	 * @param oDim  a target space dimension
	 */
	public Camera(int iDim, int oDim)
	{	
		rotation = new Rotation(iDim);
		projection = new Projection(oDim, iDim);
		translation = new Translation(iDim);
		dilation = new Dilation(iDim);
		
		this.iDim = iDim;
		this.oDim = oDim;
	}


	/**
	 * Changes the origin of the {@code Camera}.
	 * 
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public void setOrigin(Vector o)
	{
		translation = new Translation(o, iDim);
		setChanged();
	}
	
	/**
	 * Changes the rotation of the {@code Camera}.
	 * 
	 * @param r  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public void setBasis(Matrix r)
	{
		rotation = new Rotation(r, iDim);
		setChanged();
	}
	
	/**
	 * Changes the oculus of the {@code Camera}.
	 * 
	 * @param o  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public void setOculus(Vector o)
	{
		projection = new Projection(o, iDim, oDim);
		setChanged();
	}
	
	/**
	 * Changes the size of the {@code Camera}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public void setSize(Vector s)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		dilation = new Dilation(s.times(0.5f), iDim);
		setChanged();
	}
	

	/**
	 * Returns the origin of the {@code Camera}.
	 * 
	 * @return  a camera origin
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Origin()
	{
		return translation.Origin();
	}
		
	/**
	 * Returns the oculus of the {@code Camera}.
	 * 
	 * @return  a camera oculus
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Oculus()
	{
		return projection.Oculus();
	}
	
	/**
	 * Returns the basis of the {@code Camera}.
	 * 
	 * @return  a camera basis
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Basis()
	{
		return rotation.Basis();
	}
	
	/**
	 * Returns the scale of the {@code Camera}.
	 * 
	 * @return  a camera size
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Size()
	{
		return dilation.Size();
	}


	@Override
	protected void update()
	{
		inv = Inverse(DimOut());
		mat = Matrix(DimIn());
	}

	@Override
	public ITransformation[] Functions()
	{
		return new ITransformation[]
		{
			ITransformation.inverse(translation),
			ITransformation.inverse(rotation),
			ITransformation.inverse(projection),
			ITransformation.inverse(dilation)
		};
	}
	
	@Override
	public Camera instance()
	{
		return new Camera(iDim, oDim);
	}
	
	@Override
	public Camera copy()
	{
		Camera copy = Copyable.super.copy();
		
		copy.setOrigin(translation.Origin());
		copy.setOculus(projection.Oculus());
		copy.setBasis(rotation.Basis());
		copy.setSize(dilation.Size());
		
		return copy;
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
	public int DimOut()
	{
		return oDim;
	}
	
	@Override
	public int DimIn()
	{
		return iDim;
	}


}
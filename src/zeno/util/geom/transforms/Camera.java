package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.transforms.affine.Dilation;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.geom.transforms.projective.Projection;
import zeno.util.tools.patterns.DirtyValue;

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
 * @see Integer
 */
public class Camera extends DirtyValue<Integer> implements ITransformation
{
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
		super(iDim);
		
		rotation = new Rotation(iDim);
		projection = new Projection(oDim, iDim);
		translation = new Translation(iDim);
		dilation = new Dilation(iDim);
	}

	@Override
	protected void update(Integer dim)
	{
		inv = dilation.Matrix(dim);
		inv = projection.Inverse(dim).times(inv);
		inv = rotation.Matrix(dim).times(inv);
		inv = translation.Matrix(dim).times(inv);
		
		mat = translation.Inverse(dim);
		mat = rotation.Inverse(dim).times(mat);
		mat = projection.Matrix(dim).times(mat);
		mat = dilation.Inverse(dim).times(mat);
	}
	
	
	/**
	 * Changes the origin of the {@code Camera}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public void setOrigin(Point o)
	{
		translation = new Translation(o);
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
		rotation = new Rotation(r);
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
		projection = new Projection(o);
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
	public void setSize(Point s)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		dilation = new Dilation(s.times(0.5f));
		setChanged();
	}
	

	/**
	 * Returns the origin of the {@code Camera}.
	 * 
	 * @return  a camera origin
	 * 
	 * 
	 * @see Point
	 */
	public Vector Origin()
	{
		return translation.Origin().asVector();
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
	 * @see Point
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
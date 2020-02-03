package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.AffineMap;
import zeno.util.geom.transforms.affine.Dilation;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.geom.utilities.spin.Spin;
import zeno.util.tools.patterns.DirtyValue;

/**
 * The {@code StandardMap} defines a standard affine map.
 * </br> The implementation is provided as a homogeneous transformation.
 * </br> It combines a translation, dilation and rotation to create a basic
 * geometric transformation for n-dimensional objects.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see DirtyValue
 * @see AffineMap
 * @see Integer
 */
public class StandardMap extends DirtyValue<Integer> implements AffineMap
{
	private Dilation dilation;
	private Translation translation;
	private Rotation rotation;
	
	private Matrix mat, inv;
	
	/**
	 * Creates a new {@code StandardMap}.
	 * 
	 * @param dim  a space dimension
	 */
	public StandardMap(int dim)
	{		
		super(dim);
		
		dilation = new Dilation(dim);
		translation = new Translation(dim);
		rotation = new Rotation(dim);
	}

	
	@Override
	public void update(Integer dim)
	{
		inv = translation.Inverse(dim);
		inv = rotation.Inverse(dim).times(inv);
		inv = dilation.Inverse(dim).times(inv);

		mat = dilation.Matrix(dim);
		mat = rotation.Matrix(dim).times(mat);
		mat = translation.Matrix(dim).times(mat);
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
	
	
	@Override
	public void setOrigin(Vector o)
	{
		translation = new Translation(o);
		setChanged();
	}
		
	@Override
	public void setSize(Vector s)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		dilation = new Dilation(s.times(0.5f));
		setChanged();
	}
	
	@Override
	public void setSpin(Spin s)
	{
		rotation = new Rotation(s);
		setChanged();
	}
	
	
	@Override
	public Vector Origin()
	{
		return translation.Origin().asVector();
	}
	
	@Override
	public Vector Size()
	{
		return dilation.Size().asVector();
	}

	@Override
	public Spin Spin()
	{
		return rotation.Spin();
	}
}
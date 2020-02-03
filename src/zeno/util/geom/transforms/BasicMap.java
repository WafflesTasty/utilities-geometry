package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.AffineMap;
import zeno.util.geom.transforms.affine.Rotation;
import zeno.util.geom.transforms.affine.Translation;
import zeno.util.geom.utilities.spin.Spin;
import zeno.util.tools.patterns.DirtyValue;

/**
 * The {@code BasicMap} class defines a basic affine map.
 * </br> The implementation is provided as a homogeneous transformation.
 * </br> It combines a translation and rotation to create a basic
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
public class BasicMap extends DirtyValue<Integer> implements AffineMap
{
	private Rotation rotation;
	private Translation translation;
		
	private Matrix mat, inv;
	
	/**
	 * Creates a new {@code StandardMap}.
	 * 
	 * @param dim  a space dimension
	 */
	public BasicMap(int dim)
	{		
		super(dim);
		
		rotation = new Rotation(dim);
		translation = new Translation(dim);
	}

	
	@Override
	public void update(Integer dim)
	{
		inv = translation.Inverse(dim);
		inv = rotation.Inverse(dim).times(inv);

		mat = rotation.Matrix(dim);
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
		// NOT APPLICABLE
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
		return Vectors.create(2f, Origin().Size());
	}

	@Override
	public Spin Spin()
	{
		return rotation.Spin();
	}
}
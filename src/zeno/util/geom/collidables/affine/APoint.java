package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.Affine;
import zeno.util.tools.Floats;

/**
 * The {@code APoint} class defines a zero-dimensional affine space.
 * It both extends the {@code ASpace} class and implements the {@code Affine}
 * interface to allow it to be used in both applications.
 * 
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see ASpace
 * @see Affine
 */
public class APoint extends ASpace implements Affine
{		
	private Vector vmat;
	
	/**
	 * Creates a new {@code APoint}.
	 * 
	 * @param v  a vector point
	 * 
	 * 
	 * @see Vector
	 */
	public APoint(Vector v)
	{
		super(null, VSpaces.trivial(v.Size()));
		vmat = v;
	}
	
	/**
	 * Creates a new {@code APoint}.
	 * 
	 * @param vals  a set of vector values
	 */
	public APoint(float... vals)
	{
		this(Vectors.create(vals));
	}
		
	/**
	 * Subtracts a point from the {@code APoint}.
	 * 
	 * @param p  a point to subtract
	 * @return  a result vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector minus(APoint p)
	{
		Vector v1 = VMatrix();
		Vector v2 = p.VMatrix();
		return v1.minus(v2);
	}
			
		
	@Override
	public ASpace intersect(ASpace s)
	{
		if(!s.contains(this))
		{
			return ASpaces.trivial(Origin().Size());
		}
		
		return this;
	}
	
	@Override
	public boolean intersects(ASpace s)
	{
		return s.contains(this);
	}

	@Override
	public boolean contains(ASpace s)
	{
		int size = vmat.Size();
		Vector pq = Origin().minus(s.Origin());
		return Floats.isZero(pq.norm(), 2 * size - 1)
			&& s.Dimension() == 0;
	}
	
	@Override
	public boolean contains(APoint p)
	{
		return equals(p);
	}
	
	
	@Override
	public Matrix HMatrix()
	{
		return ASpaces.homogenize(VMatrix());
	}
	
	@Override
	public Vector VMatrix()
	{
		return vmat;
	}
	
	@Override
	public int Dimension()
	{
		return 0;
	}
	
	@Override
	public int Size()
	{
		return 1;
	}

	
	@Override
	public APoint Origin()
	{
		return this;
	}
	
	@Override
	public APoint[] Points()
	{
		return new APoint[]{this};
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.trivial(Origin().Size());
	}
	
	@Override
	public Affine Span()
	{
		return this;
	}
}
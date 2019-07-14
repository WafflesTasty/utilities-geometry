package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.APoint;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;

/**
 * The {@code FullASpace} class defines an entire affine space.
 * Its dimension equals the amount of coördinates being used.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public class FullASpace extends ASpace
{
	private int coords;
	
	/**
	 * Creates a new {@code FullASpace}.
	 * 
	 * @param size  a coördinate size
	 */
	public FullASpace(int size)
	{
		super(null, VSpaces.full(size));
		coords = size;
	}

	
	@Override
	public APoint Origin()
	{
		return new APoint(Vectors.create(coords));
	}
	
	@Override
	public VSpace Direction()
	{
		return VSpaces.full(coords);
	}
	
	@Override
	public Affine.Set Span()
	{
		return ASpaces.vset(Matrices.identity(coords));
	}
	
	@Override
	public int Dimension()
	{
		return coords;
	}
		
		
	@Override
	public Affine intersect(Affine s)
	{
		return s;
	}
		
	@Override
	public boolean contains(Vector v)
	{
		return true;
	}
			
	@Override
	public boolean equals(ASpace s, int ulps)
	{
		return s.Dimension() == coords;
	}

	@Override
	public boolean intersects(ASpace s)
	{
		return true;
	}
	
	@Override
	public boolean contains(Affine s)
	{
		return true;
	}
}
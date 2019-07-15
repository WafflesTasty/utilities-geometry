package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.points.Point;

/**
 * The {@code FullASpace} class defines an entire affine space.
 * Its dimension equals the amount of coördinates being used.
 *
 * @author Zeno
 * @since Apr 9, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class FullASpace implements Affine.Space
{
	private Point origin;
	private VSpace direction;
	
	/**
	 * Creates a new {@code FullASpace}.
	 * 
	 * @param size  a coördinate size
	 */
	public FullASpace(int size)
	{
		origin = new Point(Vectors.create(size));
		direction = VSpaces.full(size);
	}
		
				
	@Override
	public boolean contains(Point p)
	{
		return true;
	}
	
	@Override
	public boolean contains(Affine s)
	{
		return true;
	}
	
	@Override
	public boolean equals(Affine a, int ulps)
	{
		if(a instanceof Affine.Space)
		{
			Affine.Space s = (Affine.Space) a;
			return s.Dimension() == Dimension();
		}
		
		return false;
	}

	@Override
	public boolean intersects(Affine s)
	{
		return true;
	}
	
	@Override
	public Affine intersect(Affine s)
	{
		return s;
	}
	
		
	@Override
	public VSpace Direction()
	{
		return direction;
	}
	
	@Override
	public Affine.Set Span()
	{
		return ASpaces.vset(direction.Span());
	}

	@Override
	public Point Origin()
	{
		return origin;
	}
}
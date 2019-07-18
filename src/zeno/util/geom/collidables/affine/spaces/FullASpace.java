package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;

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
	public boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Affine.Space)
		{
			Affine.Space s = (Affine.Space) c;
			Matrix m = s.Direction().Complement();
			return s.Dimension() == Dimension()
				&& m.Columns() == 0;
		}
		
		return false;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		return c;
	}
	
	@Override
	public boolean intersects(ICollidable c)
	{
		return true;
	}
	
	@Override
	public boolean contains(ICollidable c)
	{
		return true;
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
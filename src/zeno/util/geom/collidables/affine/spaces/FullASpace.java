package zeno.util.geom.collidables.affine.spaces;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.affine.CLSAFull;

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
	 */
	public FullASpace()
	{
		origin = new Point(Vectors.create(0));
		direction = VSpaces.full(0);
	}
	
	
	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public VSpace Direction()
	{
		return direction;
	}
	
	@Override
	public ICollision Collisions()
	{
		return new CLSAFull();
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
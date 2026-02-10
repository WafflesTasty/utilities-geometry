package waffles.utils.geom.shapes.collision.fixed;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.response.fixed.RSPVoid;

/**
 * A {@code CLSVoid} defines {@code Collision} for {@code Void}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see Collision
 */
public class CLSVoid extends Collision
{
	/**
	 * Creates a new {@code CLSVoid}.
	 *
	 * @param s  a source void
	 *
	 *
	 * @see Void
	 */
	public CLSVoid(Void s)
	{
		super(s);
	}


	@Override
	public Response contain(Collidable c)
	{
		return new RSPVoid(Source());
	}

	@Override
	public Response intersect(Collidable c)
	{
		return new RSPVoid(Source());
	}

	@Override
	public Response inhabit(Collidable c)
	{
		return new RSPVoid(Source());
	}

	@Override
	public Void Source()
	{
		return (Void) super.Source();
	}
}
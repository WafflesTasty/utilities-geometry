package waffles.utils.geom.collide.response.fixed;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;

/**
 * An {@code RSPVoid} computes a collision {@code Response} for {@code Void} geometry.
 *
 * @author Waffles
 * @since 13 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class RSPVoid implements Response
{
	private Void src;

	/**
	 * Creates a new {@code RSPVoid}.
	 *
	 * @param s  a void source
	 * 
	 * 
	 * @see Void
	 */
	public RSPVoid(Void s)
	{
		src = s;
	}

	
	@Override
	public Void Source()
	{
		return src;
	}

	@Override
	public Collidable Shape()
	{
		return Source();
	}

	@Override
	public int cost()
	{
		return 0;
	}
}
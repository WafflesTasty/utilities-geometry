package waffles.utils.geom.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.Alignable;
import waffles.utils.geom.collision.general.align.ISCAlign;
import waffles.utils.geom.collision.general.align.ISCGeneral;

/**
 * The {@code CLSAlignable} class defines collision responses for {@code Alignable} objects.
 *
 * @author Waffles
 * @since Jul 25, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometrical
 */
public class CLSAlignable extends CLSGeometrical
{
	/**
	 * Creates a new {@code CLSAlignable}.
	 * 
	 * @param s  an alignable source
	 * 
	 * 
	 * @see Alignable
	 */
	public CLSAlignable(Alignable s)
	{
		super(s);
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		Alignable s = Source();
		
		// Eliminate alignable objects.
		if(c instanceof Alignable)
		{
			Alignable t = (Alignable) c;
			return new ISCAlign(s, t);
		}
		
		// Simplify alignable responses.
		return new ISCGeneral(s, c);
	}

	@Override
	public Alignable Source()
	{
		return (Alignable) super.Source();
	}
}
package waffles.utils.geom.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.geometric.AxisAligned;
import waffles.utils.geom.response.align.ISCAlign;
import waffles.utils.geom.response.align.ISCGeneral;

/**
 * The {@code CLSAlignable} class defines collision responses for {@code AxisAligned} objects.
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
	 * @see AxisAligned
	 */
	public CLSAlignable(AxisAligned s)
	{
		super(s);
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		AxisAligned s = Source();
		
		// Eliminate alignable objects.
		if(c instanceof AxisAligned)
		{
			AxisAligned t = (AxisAligned) c;
			return new ISCAlign(s, t);
		}
		
		// Simplify alignable responses.
		return new ISCGeneral(s, c);
	}

	@Override
	public AxisAligned Source()
	{
		return (AxisAligned) super.Source();
	}
}
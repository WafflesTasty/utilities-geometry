package waffles.utils.geom.spaces.arboreal.nodal.tform;

import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.geom.spaces.arboreal.nodal.TransformNodal;

/**
 * An {@code OffsetTransform} defines a {@code GlobalMap} for a {@code TransformNodal}.
 * It is defined as a composition of a {@link #Base()} and {@link #Offset()} map,
 * with the latter being propagated from each parent to its children.
 * 
 * @author Waffles
 * @since May 16, 2026
 * @version 1.1
 *
 * 
 * @see OffsetMap
 */
public abstract class OffsetTransform extends OffsetMap
{
	/**
	 * Creates a new {@code OffsetTransform}.
	 * 
	 * @param n  a parent nodal
	 * 
	 * 
	 * @see TransformNodal
	 */
	public OffsetTransform(TransformNodal n)
	{
		super(n);
	}

	/**
	 * Returns an offset of the {@code OffsetTransform}.
	 * 
	 * @return  an offset map
	 * 
	 * 
	 * @see OffsetMap
	 */
	public abstract OffsetMap Offset();


	@Override
	public void setChanged()
	{
		for(Nodal n : Nodal().Arch().Children())
		{
			TransformNodal c = (TransformNodal) n;
			c.Transform().setChanged();
		}

		Offset().setChanged();
		super.setChanged();
	}
}
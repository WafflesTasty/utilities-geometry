package waffles.utils.geom.spaces.axial.or.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.spaces.axial.or.OrtoBoreal;
import waffles.utils.geom.spaces.axial.or.OrtoNodal;
import waffles.utils.geom.spaces.axial.or.OrtoNode;

/**
 * A {@code QRYCuboid} queries a cuboid in an {@code OrtoTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <N>  a nodal type
 * @see OrtoNodal
 * @see Iterator
 */
public class QRYCuboid<N extends OrtoNodal> implements Iterator<N>
{
	private OrtoNodal curr;
	private HyperCuboid cbd;

	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param t  an orto tree
	 * @param c  a cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 * @see OrtoBoreal
	 */
	public QRYCuboid(OrtoBoreal<N> t, HyperCuboid c)
	{
		curr = t.Root();
		cbd = c;		
	}


	@Override
	public boolean hasNext()
	{
		return curr != null;
	}
	
	@Override
	public N next()
	{
		N next = (N) curr;
		int idx = next.index(cbd);
		OrtoNode n = next.Arch();
		curr = n.Child(idx);
		return next;
	}
}
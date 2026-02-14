package waffles.utils.geom.spaces.axial.or;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.axial.or.queries.QRYCuboid;
import waffles.utils.geom.spaces.axial.or.queries.QRYPoint;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;

/**
 * An {@code OrtoBoreal} defines an orthogonal {@code Arboreal} structure.
 * It provides a framework for any {@code OrtoNodal} tree.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
 * @see OrtoNodal
 * @see Arboreal
 * @see Bounded
 * @see Space
 */
public interface OrtoBoreal<N extends OrtoNodal> extends Arboreal, Bounded, Space<N>
{
	@Override
	public abstract N Root();
	
		
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
	
	@Override
	public default Iterable<N> query(HyperCuboid c)
	{
		return () -> new QRYCuboid<>(this, c);
	}

	@Override
	public default Iterable<N> query(Point p)
	{
		return () -> new QRYPoint<>(this, p);
	}
	
	@Override
	public default Bounds Bounds()
	{
		return Root().Bounds();
	}
}
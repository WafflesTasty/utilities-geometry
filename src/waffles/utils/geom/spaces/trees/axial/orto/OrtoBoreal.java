package waffles.utils.geom.spaces.trees.axial.orto;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.SpatialBoreal;
import waffles.utils.geom.spaces.trees.axial.orto.queries.QRYCuboid;
import waffles.utils.geom.spaces.trees.axial.orto.queries.QRYPoint;

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
 * @see SpatialBoreal
 * @see OrtoNodal
 */
public interface OrtoBoreal<N extends OrtoNodal> extends SpatialBoreal<N>
{
	/**
	 * An {@code OrtoBoreal.Query} defines queries for an {@code OrtoBoreal} tree.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a node type
	 * @see SpatialBoreal
	 * @see OrtoNodal
	 */
	@FunctionalInterface
	public static interface Query<N extends OrtoNodal> extends SpatialBoreal.Query<N>
	{
		@Override
		public abstract OrtoBoreal<N> Tree();
		
		@Override
		public default Iterator<N> in(HyperCuboid c)
		{
			return new QRYCuboid<>(Tree(), c);
		}

		@Override
		public default Iterator<N> at(Point p)
		{
			return new QRYPoint<>(Tree(), p);
		}
	}
	
					
	@Override
	public default Query<N> Query()
	{
		return () -> this;
	}
}
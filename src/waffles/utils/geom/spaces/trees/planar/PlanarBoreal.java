package waffles.utils.geom.spaces.trees.planar;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.SpatialBoreal;
import waffles.utils.geom.spaces.trees.planar.queries.QRYCuboid;
import waffles.utils.geom.spaces.trees.planar.queries.QRYPoint;
import waffles.utils.sets.arboreal.binary.BiArboreal;

/**
 * A {@code PlanarBoreal} defines a planar splitting {@code Arboreal} structure.
 * It provides a framework for a {@code PlanarNodal} spatial tree.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 * 
 * @param <N>  a nodal type
 * @see SpatialBoreal
 * @see PlanarNodal
 * @see BiArboreal
 */
public interface PlanarBoreal<N extends PlanarNodal> extends BiArboreal.Mutable, SpatialBoreal<N>
{
	/**
	 * A {@code PlanarBoreal.Query} defines queries for a {@code PlanarBoreal} tree.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a node type
	 * @see PlanarBoreal
	 * @see PlanarNodal
	 */
	@FunctionalInterface
	public static interface Query<N extends PlanarNodal> extends SpatialBoreal.Query<N>
	{
		@Override
		public abstract PlanarBoreal<N> Tree();
		
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
package waffles.utils.geom.spaces.arboreal.planar;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.ArborealSpace;
import waffles.utils.geom.spaces.arboreal.planar.queries.QRYCuboid;
import waffles.utils.geom.spaces.arboreal.planar.queries.QRYPoint;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.binary.BiArboreal;

/**
 * A {@code PlanarBoreal} defines a planar splitting {@code Arboreal} structure.
 * It provides a framework for a {@code PlanarNodal} tree space.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see ArborealSpace
 * @see BiArboreal
 * @see Arboreal
 */
public interface PlanarBoreal<O> extends ArborealSpace<O>, BiArboreal, Arboreal.Mutable
{
	/**
	 * A {@code PlanarBoreal.Query} defines queries for a {@code PlanarBoreal} tree.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see ArborealSpace
	 * @see BiArboreal
	 */
	public static interface Query<O> extends ArborealSpace.Query<O>, BiArboreal.Query<O>
	{		
		/**
		 * Iterates over nodes at a cuboid in a {@code PlanarBoreal}.
		 * 
		 * @param c  a target cuboid
		 * @return   a node iterator
		 * 
		 * 
		 * @see HyperCuboid
		 * @see Iterator
		 */
		public default <N extends PlanarNodal> Iterator<N> Nodes(HyperCuboid c)
		{
			return new QRYCuboid<>((N) Tree().Root(), c);
		}
		
		/**
		 * Iterates over nodes at a point in a {@code PlanarBoreal}.
		 * 
		 * @param p  a target point
		 * @return   a node iterator
		 * 
		 * 
		 * @see Iterator
		 * @see Point
		 */
		public default <N extends PlanarNodal> Iterator<N> Nodes(Point p)
		{
			return new QRYPoint<>((N) Tree().Root(), p);
		}
		
		
		@Override
		public abstract PlanarBoreal<O> Tree();
	}

	
	@Override
	public abstract PlanarNodal Root();

	@Override
	public abstract Query<O> Query();
}
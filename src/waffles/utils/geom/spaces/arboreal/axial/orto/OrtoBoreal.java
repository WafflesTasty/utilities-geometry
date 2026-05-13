package waffles.utils.geom.spaces.arboreal.axial.orto;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.ArborealSpace;
import waffles.utils.geom.spaces.arboreal.axial.orto.queries.QRYCuboid;
import waffles.utils.geom.spaces.arboreal.axial.orto.queries.QRYPoint;
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
 * @param <O>  an object type
 * @see ArborealSpace
 * @see Arboreal
 */
public interface OrtoBoreal<O> extends ArborealSpace<O>, Arboreal.Mutable
{
	/**
	 * An {@code OrtoBoreal.Query} defines queries for an {@code OrtoBoreal}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see ArborealSpace
	 */
	public static interface Query<O> extends ArborealSpace.Query<O>
	{
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
		public default <N extends OrtoNodal> Iterator<N> Nodes(Point p)
		{
			return new QRYPoint<>((N) Tree().Root(), p);
		}
		
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
		public default <N extends OrtoNodal> Iterator<N> Nodes(HyperCuboid c)
		{
			return new QRYCuboid<>((N) Tree().Root(), c);
		}
		
		
		@Override
		public abstract OrtoBoreal<O> Tree();
	}
	
					
	@Override
	public abstract Query<O> Query();
	
	@Override
	public abstract OrtoNodal Root();
}
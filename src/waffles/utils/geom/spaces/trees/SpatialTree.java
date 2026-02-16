package waffles.utils.geom.spaces.trees;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.trees.queries.QRYNodes;
import waffles.utils.sets.arboreal.Arboreal;

/**
 * A {@code SpatialTree} defines a {@code Manifold} around a {@code SpatialBoreal}.
 *
 * @author Waffles
 * @since 16 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <N>  a node type
 * @see Arboreal
 * @see Manifold
 */
public interface SpatialTree<O, N extends SpatialNodal> extends Arboreal, Manifold<O>
{
	/**
	 * A {@code TreeSpace.Query} defines queries for a {@code TreeSpace}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <N>  a node type
	 * @see Manifold
	 */
	public interface Query<O, N extends SpatialNodal> extends Manifold.Query<O>
	{		
		/**
		 * Iterates objects in a {@code Nodal}.
		 * 
		 * @param node  a node
		 * @return  an object iterator
		 * 
		 * 
		 * @see Iterator
		 */
		public abstract Iterator<O> in(N node);
		
		/**
		 * Returns the space of the {@code Query}.
		 * 
		 * @return  a spatial tree
		 * 
		 * 
		 * @see SpatialTree
		 */
		public abstract SpatialTree<O, N> Space();
		
		
		@Override
		public default Iterator<O> in(HyperCuboid c)
		{
			SpatialBoreal<N> t = Space().Tree();
			
			N n = (N) t.Root();
			if(!n.intersects(c))
			{
				return in(n);
			}

			return new QRYNodes<>(this, t.query(c));
		}

		@Override
		public default Iterator<O> at(Point p)
		{
			SpatialBoreal<N> t = Space().Tree();
			
			N n = (N) t.Root();
			if(!n.contains(p))
			{
				return in(n);
			}
			
			return new QRYNodes<>(this, t.query(p));
		}
		
		@Override
		public default Iterator<O> All()
		{
			SpatialBoreal.Query<N> q = Space().Tree().Query();
			return new QRYNodes<>(this, () -> q.All());
		}
	}
	
	
	/**
	 * Returns the boreal of the {@code SpatialTree}.
	 * 
	 * @return  a spatial boreal
	 * 
	 * 
	 * @see SpatialBoreal
	 */
	public abstract SpatialBoreal<N> Tree();
	
	@Override
	public abstract Query<O, N> Query();
}
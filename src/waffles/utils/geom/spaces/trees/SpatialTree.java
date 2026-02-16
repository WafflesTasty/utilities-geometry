package waffles.utils.geom.spaces.trees;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.trees.queries.QRYNodes;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;

/**
 * A {@code SpatialTree} defines a {@code Space} around a {@code SpatialBoreal}.
 *
 * @author Waffles
 * @since 16 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <N>  a node type
 * @see Arboreal
 * @see Bounded
 * @see Space
 */
public interface SpatialTree<O, N extends SpatialNodal> extends Arboreal, Bounded, Space<O>
{
	/**
	 * A {@code SpatialTree.Query} defines queries for a {@code SpatialTree}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <N>  a node type
	 * @see SpatialNodal
	 * @see Space
	 */
	public interface Query<O, N extends SpatialNodal> extends Space.Query<O>
	{	
		/**
		 * Returns a node {@code Iterator}.
		 * 
		 * @param n  a spatial node
		 * @return  an object iterator
		 * 
		 * 
		 * @see Iterator
		 */
		public abstract Iterator<O> in(N n);
		
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
			return new QRYNodes<>(this, Space().Tree());
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

	
	@Override
	public default Bounds Bounds()
	{
		return Tree().Bounds();
	}
	
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
	
	@Override
	public default N Root()
	{
		return Tree().Root();
	}
}
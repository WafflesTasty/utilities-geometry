package waffles.utils.geom.spaces.binary.kd;

import waffles.utils.algebra.elements.interval.Cut;
import waffles.utils.algebra.elements.interval.Cuts;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.axial.cuboid.HyperCube;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.sets.trees.binary.BiNode;

/**
 * A {@code KDNode} defines a single node in a {@code KDTree}.
 *
 * @author Waffles
 * @since 17 Jan 2022
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see Geometrical
 * @see BiNode
 */
public abstract class KDNode<O> extends BiNode implements Geometrical
{
	/**
	 * A {@code Plane} splits a {@code KDNode} into subnodes.
	 *
	 * @author Waffles
	 * @since 25 Nov 2021
	 * @version 1.1
	 */
	public static class Plane
	{
		private int dim;
		private Cut cut;
		
		/**
		 * Creates a new {@code Plane}.
		 * 
		 * @param d  a cut dimension
		 * @param v  a cut value
		 */
		public Plane(int d, float v)
		{
			cut = Cuts.Below(v);
			dim = d;
		}
		
		/**
		 * Returns the dimension of the {@code Plane}.
		 * 
		 * @return  a cut dimension
		 */
		public int Dimension()
		{
			return dim;
		}
		
		/**
		 * Returns the cut of the {@code Plane}.
		 * 
		 * @return  a cut
		 */
		public Cut Cut()
		{
			return cut;
		}
	}

	
	private Plane plane;
	
	/**
	 * Creates a new {@code KDNode}.
	 * 
	 * @param tree  a parent tree
	 * 
	 * 
	 * @see KDTree
	 */
	public KDNode(KDTree<O> tree)
	{
		super(tree);
	}

	/**
	 * Returns objects in the {@code KDNode}.
	 * 
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<O> Objects();
	
	/**
	 * Changes the plane of the {@code KDNode}.
	 * 
	 * @param dim  a cut dimension
	 * @param val  a cut value
	 */
	public void setPlane(int dim, float val)
	{
		setPlane(new Plane(dim, val));
	}
	
	/**
	 * Changes the plane of the {@code KDNode}.
	 * 
	 * @param p  a splitting plane
	 * 
	 * 
	 * @see Plane
	 */
	public void setPlane(Plane p)
	{
		plane = p;
	}
		
	/**
	 * Returns the plane of the {@code KDNode}.
	 * 
	 * @return  a splitting plane
	 * 
	 * 
	 * @see Plane
	 */
	public Plane Plane()
	{
		return plane;
	}
		
	
	@Override
	public KDTree<O> Set()
	{
		return (KDTree<O>) super.Set();
	}
	
	@Override
	public GlobalMap Transform()
	{
		return new KDTransform(this);
	}
	
	@Override
	public HyperCube Shape()
	{
		return Geometries.Cube(Dimension());
	}
	
			
	@Override
	public KDNode<O> Parent()
	{
		return (KDNode<O>) super.Parent();
	}
	
	@Override
	public KDNode<O> LChild()
	{
		return (KDNode<O>) super.LChild();
	}
	
	@Override
	public KDNode<O> RChild()
	{
		return (KDNode<O>) super.RChild();
	}
}
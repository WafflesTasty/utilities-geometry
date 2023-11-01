package waffles.utils.geom.spaces.binary.kd;

import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.axial.cuboid.ICube;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.sets.trees.binary.BiNode;

/**
 * A {@code KDNode} defines a single node in a {@code KDTree}.
 *
 * @author Waffles
 * @since 17 Jan 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Geometrical
 * @see BiNode
 */
public abstract class KDNode<O> extends BiNode implements Geometrical
{
	/**
	 * A {@code Cut} defines a plane that splits a {@code KDNode}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2021
	 * @version 1.1
	 */
	public static class Cut
	{
		private int dim;
		private float val;
		
		/**
		 * Creates a new {@code Cut}.
		 * 
		 * @param dim  a cut dimension
		 * @param val  a cut value
		 */
		public Cut(int dim, float val)
		{
			this.dim = dim;
			this.val = val;
		}
		
		/**
		 * Returns the dimension of the {@code Cut}.
		 * 
		 * @return  a cut dimension
		 */
		public int Dimension()
		{
			return dim;
		}
		
		/**
		 * Returns the value of the {@code Cut}.
		 * 
		 * @return  a cut value
		 */
		public float Value()
		{
			return val;
		}
	}

	
	private Cut cut;
	
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
	 * Returns the objects in the {@code KDNode}.
	 * 
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<O> Objects();
	
	/**
	 * Changes the cut of the {@code KDNode}.
	 * 
	 * @param dim  a cut dimension
	 * @param val  a cut value
	 */
	public void setCut(int dim, float val)
	{
		setCut(new Cut(dim, val));
	}
	
	/**
	 * Changes the cut of the {@code KDNode}.
	 * 
	 * @param cut  a target cut
	 * 
	 * 
	 * @see Cut
	 */
	public void setCut(Cut cut)
	{
		this.cut = cut;
	}
		
	/**
	 * Returns the cut of the {@code KDNode}.
	 * 
	 * @return  a plane cut
	 * 
	 * 
	 * @see Cut
	 */
	public Cut Cut()
	{
		return cut;
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
	public ICube Shape()
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
package waffles.utils.geom.spaces.index.beps;

import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.spaces.index.IndexSpace;
import waffles.utils.sets.trees.Rooted;

/**
 * A {@code BEPSpace} defines a binary enum partition space backed by a {@code BEPSTree}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <E>  an enum type
 * @see IndexSpace
 * @see BEPSNode
 * @see Rooted
 * @see Enum
 */
public class BEPSpace<E extends Enum<E>> implements Rooted, IndexSpace<BEPSNode<E>>
{		
	private float tSize;
	private BEPSTree<E> tree;
	
	/**
	 * Creates a new {@code BEPSpace}.
	 * 
	 * @param dims  an index dimension
	 */
	public BEPSpace(int... dims)
	{
		tSize = 2f;
		tree = createTree(dims);
		tree.setRoot(createRoot(dims));
	}
	
	/**
	 * Changes the tile size of the {@code BEPSpace}.
	 * 
	 * @param s  a tile size
	 */
	public void setTileSize(float s)
	{
		tSize = s;
	}
	
	/**
	 * Creates a partition tree for the {@code BEPSpace}.
	 * 
	 * @param dims  an index dimension
	 * @return  a spatial tree
	 * 
	 * 
	 * @see BEPSTree
	 */
	public BEPSTree<E> createTree(int... dims)
	{
		return new BEPSTree<>(this, dims);
	}
	
	/**
	 * Queries an enum value in the {@code BEPSpace}.
	 * 
	 * @param val  an enum value
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BEPSNode
	 */
	public Iterable<BEPSNode<E>> query(E val)
	{
		return tree.nodes(val);
	}
	
	/**
	 * Returns the tree of the {@code BEPSpace}.
	 * 
	 * @return  a beps tree
	 * 
	 * 
	 * @see BEPSTree
	 */
 	public BEPSTree<E> Tree()
	{
		return tree;
	}
	
	
	/**
	 * Changes a set of values in the {@code BEPSpace}.
	 * 
	 * @param val   an enum value
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void put(E val, int[] min, int[] max)
	{
		tree.put(val, min, max);
	}

	/**
	 * Removes a set of values from the {@code BEPSpace}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		tree.remove(min, max);
	}
	
	/**
	 * Changes a value in the {@code BEPSpace}.
	 * 
	 * @param val   an enum value
	 * @param crds  an index coordinate
	 */
	public void put(E val, int... crds)
	{
		tree.put(val, crds);
	}
	
	/**
	 * Removes a value from the {@code BEPSpace}.
	 * 
	 * @param crds  an index coordinate
	 */
	public void remove(int... crds)
	{
		tree.remove(crds);
	}
	

	@Override
	public BEPSNode<E> Root()
	{
		return Tree().Root();
	}
	
	@Override
	public BEPSNode<E> get(int... coords)
	{
		return Tree().nodeAt(coords);
	}

	@Override
	public BEPSNode<E> createNode(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BEPSNode<>(Tree(), min, max);
	}
	
	@Override
	public Iterable<BEPSNode<E>> query(ICuboid c)
	{
		int[] min = indexOf(c.Bounds().Minimum());
		int[] max = indexOf(c.Bounds().Maximum());
		
		return () -> new BEPSIterator<>(Tree(), min, max);
	}
		
	BEPSNode<E> createRoot(int... dim)
	{
		int[] min = new int[dim.length];
		int[] max = new int[dim.length];
		for(int i = 0; i < dim.length; i++)
		{
			max[i] = dim[i] - 1;
		}

		return createNode(min, max);
	}
	
	
	@Override
	public Bounds Bounds()
	{
		return Tree().Root().Bounds();
	}
	
	@Override
	public int[] Dimensions()
	{
		return Tree().Dimensions();
	}
	
	@Override
	public float TileSize()
	{
		return tSize;
	}
}
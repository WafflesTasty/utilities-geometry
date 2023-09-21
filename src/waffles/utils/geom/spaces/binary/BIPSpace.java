package waffles.utils.geom.spaces.binary;

import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.spaces.binary.bips.BIPSNode;
import waffles.utils.geom.spaces.binary.bips.BIPSTree;
import waffles.utils.geom.spaces.index.IndexSpace;
import waffles.utils.sets.trees.Rooted;

/**
 * A {@code BIPSpace} defines a binary enum partition space backed by a {@code BIPSTree}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <N>  a node type
 * @see IndexSpace
 * @see BIPSNode
 * @see Rooted
 */
public class BIPSpace<N extends BIPSNode> implements Rooted, IndexSpace<N>
{		
	private float tSize;
	private BIPSTree<N> tree;
	
	/**
	 * Creates a new {@code BIPSpace}.
	 * 
	 * @param dims  an index dimension
	 */
	public BIPSpace(int... dims)
	{
		tSize = 2f;
		tree = createTree(dims);
		tree.setRoot(createRoot(dims));
	}
	
	/**
	 * Changes the tile size of the {@code BIPSpace}.
	 * 
	 * @param s  a tile size
	 */
	public void setTileSize(float s)
	{
		tSize = s;
	}
	
	/**
	 * Creates a tree for the {@code BIPSpace}.
	 * 
	 * @param dims  an index dimension
	 * @return  a spatial tree
	 * 
	 * 
	 * @see BIPSTree
	 */
	public BIPSTree<N> createTree(int... dims)
	{
		return new BIPSTree<>(this, dims);
	}

	/**
	 * Returns the tree of the {@code BIPSpace}.
	 * 
	 * @return  a beps tree
	 * 
	 * 
	 * @see BIPSTree
	 */
 	public BIPSTree<N> Tree()
	{
		return tree;
	}


	@Override
	public N Root()
	{
		return (N) Tree().Root();
	}
	
	@Override
	public N get(int... coords)
	{
		return (N) Tree().nodeAt(coords);
	}

	@Override
	public BIPSNode createNode(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BIPSNode(Tree(), min, max);
	}
	
	@Override
	public Iterable<N> query(ICuboid c)
	{
		int[] min = indexOf(c.Bounds().Minimum());
		int[] max = indexOf(c.Bounds().Maximum());
		
		return tree.nodes(min, max);
	}
		
	BIPSNode createRoot(int... dim)
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
		return Root().Bounds();
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
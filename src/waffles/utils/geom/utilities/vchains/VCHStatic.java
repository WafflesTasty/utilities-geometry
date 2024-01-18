package waffles.utils.geom.utilities.vchains;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.sets.indexed.delegate.List;

/**
 * A {@code VCHStatic} generates vertex chains from a fixed {@code List}.
 *
 * @author Waffles
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHStatic implements VChain
{	
	private List<Integer> index;
	private List<Vector> vertex, normal;
	
	/**
	 * Creates a new {@code VCHStatic}.
	 * 
	 * @param i  an index list
	 * @param v  a vertex list
	 * @param n  a normal list
	 * 
	 * 
	 * @see Vector
	 * @see List
	 */
	public VCHStatic(List<Integer> i, List<Vector> v, List<Vector> n)
	{
		index = i;
		vertex = v;
		normal = n;
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		return index;
	}
	
	@Override
	public Iterable<Vector> Vertices()
	{
		return vertex;
	}
	
	@Override
	public Iterable<Vector> Normals()
	{
		return normal;
	}
	
	
	@Override
	public int VertexCount()
	{
		return vertex.Count();
	}
	
	@Override
	public int IndexCount()
	{
		return index.Count();
	}
}
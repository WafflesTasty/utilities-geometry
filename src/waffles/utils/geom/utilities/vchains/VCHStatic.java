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
	private List<Vector> vertex;
	private List<Vector> normal;
	
	
	/**
	 * Changes the indices of the {@code VCHStatic}.
	 * 
	 * @param list  an index list
	 * 
	 * 
	 * @see Integer
	 * @see List
	 */
	public void setIndex(List<Integer> list)
	{
		index = list;
	}
	
	/**
	 * Changes the vertices of the {@code VCHStatic}.
	 * 
	 * @param list  a vertex list
	 * 
	 * 
	 * @see Vector
	 * @see List
	 */
	public void setVertex(List<Vector> list)
	{
		vertex = list;
	}
	
	/**
	 * Changes the normals of the {@code VCHStatic}.
	 * 
	 * @param list  a normal list
	 * 
	 * 
	 * @see Vector
	 * @see List
	 */
	public void setNormal(List<Vector> list)
	{
		normal = list;
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
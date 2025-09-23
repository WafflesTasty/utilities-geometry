package waffles.utils.geom.utilities.vchains;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code VCHStatic} generates vertex chains from a fixed {@code JavaList}.
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
	private JavaList<Integer> index;
	private JavaList<Vector> vertex;
	private JavaList<Vector> normal;
	
	
	/**
	 * Changes the indices of the {@code VCHStatic}.
	 * 
	 * @param list  an index list
	 * 
	 * 
	 * @see Integer
	 * @see JavaList
	 */
	public void setIndex(JavaList<Integer> list)
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
	 * @see JavaList
	 */
	public void setVertex(JavaList<Vector> list)
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
	 * @see JavaList
	 */
	public void setNormal(JavaList<Vector> list)
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
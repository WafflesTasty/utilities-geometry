package waffles.utils.geom.spatial.maps.spatial;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.structs.Locus;
import waffles.utils.geom.spatial.maps.spatial.matrix.UnitToWorld;
import waffles.utils.geom.spatial.maps.spatial.matrix.WorldToUnit;

/**
 * A {@code StandardMap} implements a {@code SpatialMap} in the usual way, as
 * a sequence of linear maps in the order {@code Dilation} -> {@code Rotation}
 * -> {@code Translation}. If no {@code Spatial} is provided at construction,
 * an internal {@code Locus} is constructed which contains the spatial data.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see SpatialMap
 */
public class StandardMap implements SpatialMap
{	
	private Spatial src;
	private UnitToWorld utw;
	private WorldToUnit wtu;
	
	/**
	 * Creates a new {@code StandardMap}.
	 * 
	 * @param s  a spatial source
	 * 
	 * 
	 * @see Spatial
	 */
	public StandardMap(Spatial s)
	{
		src = s;
		
		utw = new UnitToWorld(src);
		wtu = new WorldToUnit(src);
	}
	
	/**
	 * Creates a new {@code StandardMap}.
	 * 
	 * @param dim  a spatial dimension
	 */
	public StandardMap(int dim)
	{		
		this(new Locus(dim));
	}
	

	@Override
	public LazyMatrix UTW()
	{
		return utw;
	}
	
	@Override
	public LazyMatrix WTU()
	{
		return wtu;
	}
	
	@Override
	public Spatial Source()
	{
		return src;
	}
}
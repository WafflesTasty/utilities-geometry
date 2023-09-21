package waffles.utils.geom.maps.affine;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.AffineMap;
import waffles.utils.geom.maps.affine.standard.UnitToWorld;
import waffles.utils.geom.maps.affine.standard.WorldToUnit;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.structs.Locus;

/**
 * A {@code StandardMap} implements an {@code AffineMap} in the usual way,
 * as a sequence of linear maps in the order {@code Dilation} -> {@code Rotation}
 * -> {@code Translation}. If no {@code Spatial} is provided at construction,
 * an internal {@code Locus} is constructed which contains the spatial data.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AffineMap
 */
public class StandardMap implements AffineMap
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
	
	/**
	 * Creates a new {@code StandardMap}.
	 */
	public StandardMap()
	{		
		this(new Locus());
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
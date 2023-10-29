package waffles.utils.geom.maps.affine;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.AlignedMap;
import waffles.utils.geom.maps.affine.basic.UnitToWorld;
import waffles.utils.geom.maps.affine.basic.WorldToUnit;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.data.structs.Locus;

/**
 * A {@code BasicMap} implements an {@code AffineMap} without rotations,
 * as a sequence of linear maps in the order {@code Dilation} -> {@code Translation}.
 * If no {@code Spatial} is provided at construction, an internal {@code Locus}
 * is constructed which contains the spatial data.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AlignedMap
 */
public class BasicMap implements AlignedMap
{	
	private Axial src;
	private UnitToWorld utw;
	private WorldToUnit wtu;
	
	/**
	 * Creates a new {@code BasicMap}.
	 * 
	 * @param s  an axial source
	 * 
	 * 
	 * @see Axial
	 */
	public BasicMap(Axial s)
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
	public BasicMap(int dim)
	{		
		this(new Locus(dim));
	}
	
	/**
	 * Creates a new {@code StandardMap}.
	 */
	public BasicMap()
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
	public Axial Source()
	{
		return src;
	}
}
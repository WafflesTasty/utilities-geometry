package waffles.utils.geom.spatial.maps.axial;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.data.structs.Axis;
import waffles.utils.geom.spatial.maps.axial.matrix.UnitToWorld;
import waffles.utils.geom.spatial.maps.axial.matrix.WorldToUnit;

/**
 * A {@code BasicMap} implements an {@code AxialMap} as a sequence of linear
 * maps in the order {@code Dilation} -> {@code Translation}. If no {@code Spatial}
 * is provided at construction, an internal {@code Locus} is constructed
 * which contains the spatial data.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AxialMap
 */
public class BasicMap implements AxialMap
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
	 * Creates a new {@code BasicMap}.
	 * 
	 * @param dim  a spatial dimension
	 */
	public BasicMap(int dim)
	{		
		this(new Axis(dim));
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
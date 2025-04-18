package waffles.utils.geom.utilities;

import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.spatial.maps.axial.AxialMap;
import waffles.utils.geom.spatial.maps.fixed.IdentityMap;
import waffles.utils.geom.spatial.maps.fixed.compose.AxialCompose;
import waffles.utils.geom.spatial.maps.fixed.compose.GlobalCompose;
import waffles.utils.geom.spatial.maps.fixed.inverse.AxialInverse;
import waffles.utils.geom.spatial.maps.fixed.inverse.GlobalInverse;
import waffles.utils.geom.spatial.maps.spatial.SpatialMap;
import waffles.utils.geom.spatial.maps.spatial.StandardMap;

/**
 * The {@code Transforms} class provides static utility methods to generate {@code GlobalMap} objects.
 * The maps generated by this class are not guaranteed to maintain their lazy matrices.
 *
 * @author Waffles
 * @since 14 Sep 2023
 * @version 1.1
 */
public final class Transforms
{
	/**
	 * Creates a map from the unit sphere to an {@code HyperSpheroid}.
	 * 
	 * @param e  an ellipsoid
	 * @return   an affine map
	 * 
	 * 
	 * @see StandardMap
	 * @see HyperSpheroid
	 */
	public static StandardMap fromUSphere(HyperSpheroid e)
	{
		StandardMap map = new StandardMap(e.Dimension());
		map.setScale(e.Scale().times(0.5f));
		map.setOrigin(e.Origin());
		return map;
	}
	
	/**
	 * Creates a composition of {@code GlobalMaps}.
	 * 
	 * @param maps  a global map set
	 * @return  a composed global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public static GlobalMap compose(GlobalMap... maps)
	{
		return new GlobalCompose(maps);
	}
	
	/**
	 * Creates a composition of {@code SpatialMap}.
	 * 
	 * @param maps  a global map set
	 * @return  a composed global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public static GlobalMap compose(SpatialMap... maps)
	{
		return new GlobalCompose(maps);
	}
	
	/**
	 * Creates a composition of {@code AxialMaps}.
	 * 
	 * @param maps  a global map set
	 * @return  a composed global map
	 * 
	 * 
	 * @see AxialMap
	 */
	public static AxialMap compose(AxialMap... maps)
	{
		return new AxialCompose(maps);
	}
	
	/**
	 * Creates an inverse of a {@code GlobalMap}.
	 * 
	 * @param map  a global map
	 * @return  an inverse global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public static GlobalMap inverse(GlobalMap map)
	{
		return new GlobalInverse(map);
	}
	
	/**
	 * Creates an inverse of a {@code SpatialMap}.
	 * 
	 * @param map  a spatial map
	 * @return  an inverse spatial map
	 * 
	 * 
	 * @see AxialMap
	 */
	public static GlobalMap inverse(SpatialMap map)
	{
		return new GlobalInverse(map);
	}
	
	/**
	 * Creates an inverse of an {@code AxialMap}.
	 * 
	 * @param map  an axial map
	 * @return  an inverse axial map
	 * 
	 * 
	 * @see AxialMap
	 */
	public static AxialMap inverse(AxialMap map)
	{
		return new AxialInverse(map);
	}
	
	/**
	 * Creates an identity {@code GlobalMap}.
	 * 
	 * @return  an identity map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public static GlobalMap identity()
	{
		return new IdentityMap();
	}


	private Transforms()
	{
		// NOT APPLICABLE
	}
}
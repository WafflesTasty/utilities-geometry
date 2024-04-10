package waffles.utils.geom.spatial.maps.fixed.compose;

import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.axial.AxialMap;

/**
 * An {@code AxialCompose} defines a sequence of {@code AxialMaps}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.1
 * 
 * 
 * @see AxialMap
 */
public class AxialCompose implements AxialMap
{
	private AxialMap[] maps;
	
	/**
	 * Creates a new {@code AxialCompose}.
	 * 
	 * @param maps  a map set
	 * 
	 * 
	 * @see AxialMap
	 */
	public AxialCompose(AxialMap... maps)
	{
		this.maps = maps;
	}

	
	@Override
	public Axial Source()
	{
		int cnt = maps.length;
		if(cnt == 0)
			return null;
		if(cnt == 1)
			return maps[0].Source();
		
		
		AxialMap map = maps[0];
		for(int i = 1; i < cnt; i++)
		{
			map = new BiAxialCompose(map, maps[i]);
		}
		
		return map.Source();
	}
}
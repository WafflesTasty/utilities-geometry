package waffles.utils.geom.spaces.index.tiled.set;

import waffles.utils.geom.spaces.index.tiled.Tiled;
import waffles.utils.sets.utilities.arboreal.Collector;
import waffles.utils.sets.utilities.indexed.coords.Coordinated;

/**
 * An {@code Indexer} is a {@code Collector} for a {@code Tiled}.
 *
 * @author Waffles
 * @since Sep 8, 2026
 * @version 1.1
 *
 * 
 * @see Coordinated
 * @see Collector
 */
public class Indexer extends Collector implements Coordinated
{
	private int[] crds;
	
	/**
	 * Creates a new {@code Indexer}.
	 * 
	 * @param t  a source tile
	 * @param c  a tile coordinate
	 * 
	 * 
	 * @see Tiled
	 */
	public Indexer(Tiled t, int... c)
	{
		super(t);
		crds = c;
	}
	
	/**
	 * Changes {@code Indexer} coordinates.
	 * 
	 * @param c  a tile coordinate
	 */
	public void setCoords(int... c)
	{
		crds = c;
	}

	
	@Override
	public int[] Coords()
	{
		return crds;
	}
}
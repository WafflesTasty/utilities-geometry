package waffles.utils.geom.spaces.index.beps;

import waffles.utils.geom.spaces.index.tiled.Tiled;
import waffles.utils.sets.utilities.arboreal.Collector;
import waffles.utils.tools.patterns.properties.values.Valuable;

/**
 * A {@code BEPSTile} is a single tile in a {@code BEPSpace}.
 *
 * @author Waffles
 * @since Aug 15, 2026
 * @version 1.1
 *
 * 
 * @param <E>  an enum type
 * @see Valuable
 * @see Tiled
 */
public interface BEPSTile<E extends Enum<E>> extends Tiled, Valuable<E>
{
	/**
	 * A {@code BEPSTile.Base} implements a basic {@code BEPSTile}.
	 *
	 * @author Waffles
	 * @since Aug 15, 2026
	 * @version 1.1
	 *
	 * 
	 * @param <E>  an enum type
	 * @see Collector
	 * @see BEPSTile
	 */
	public static class Base<E extends Enum<E>> extends Collector implements BEPSTile<E>
	{
		private int[] crds;

		/**
		 * Creates a new {@code BEPSTile}.
		 * 
		 * @param s  a parent space
		 * @param c  a tile index
		 * 
		 * 
		 * @see BEPSpace
		 */
		public Base(BEPSpace<?> s, int... c)
		{
			super(s);
			crds = c;
		}

		
		@Override
		public int[] Coords()
		{
			return crds;
		}
	}
	
	
	@Override
	public default BEPSpace<E> Parent()
	{
		return (BEPSpace<E>) Tiled.super.Parent();
	}
	
	@Override
	public default E Value()
	{
		return Parent().get(Coords());
	}
}
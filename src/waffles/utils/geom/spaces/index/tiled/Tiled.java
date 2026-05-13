package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.chiral.arrows.Cardinal;
import waffles.utils.sets.utilities.arboreal.Collectible;
import waffles.utils.sets.utilities.indexed.coords.Coordinated;

/**
 * A {@code Tiled} object can be contained in a {@code TiledSpace}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Coordinated
 * @see Collectible
 * @see HyperCuboid
 */
public interface Tiled extends Coordinated, Collectible, HyperCuboid
{
	/**
	 * Returns the parent {@code TiledSpace}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see TiledSpace
	 */
	public default TiledSpace<?> Parent()
	{
		return Arch().Set();
	}

	/**
	 * Returns a neighbor of the {@code Tiled}.
	 * 
	 * @param c  a cardinal arrow
	 * @return   a neighbor tile
	 *
	 *
	 * @see Cardinal
	 */
	public default Tiled Neighbor(Cardinal c)
	{
		int[] crds = new int[Order()];
		for(int k = 0; k < Order(); k++)
		{
			int ck = (int) c.aff(k);
			int tk = Coords()[k];
			crds[k] = ck + tk;
		}
		
		TiledSpace<?> p = Parent();
		if(p.defines(crds))
		{
			return p.get(crds);
		}

		return null;
	}
	

	@Override
	public default int Dimension()
	{
		return Parent().Dimension();
	}
	
	@Override
	public default Point Origin()
	{
		Arrow s = Scale();
		int ord = Order();
		
		int[] crd = Coords();
		Vector o = Vectors.create(ord);
		for(int k = 0; k < ord; k++)
		{
			float v = 2 * crd[k] + + 1;
			o.set(s.aff(k) * v / 2, k);
		}
		
		return new Point(o, 1f);
	}
	
	@Override
	public default Arrow Scale()
	{
		return Parent().TileSize();
	}
	
	@Override
	public default int Order()
	{
		return Dimension();
	}
}
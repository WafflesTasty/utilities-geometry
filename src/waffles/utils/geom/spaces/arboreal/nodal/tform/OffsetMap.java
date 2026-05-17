package waffles.utils.geom.spaces.arboreal.nodal.tform;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.arboreal.nodal.TransformNodal;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * An {@code OffsetMap} defines a relative offset map for a {@code TransformNodal}.
 * Each child element chains the offset maps of its parents in succession.
 *
 * @author Waffles
 * @since May 16, 2026
 * @version 1.1
 *
 * 
 * @see GlobalMap
 */
public abstract class OffsetMap implements GlobalMap
{
	/**
	 * A {@code UnitToWorld} defines a {@code LazyMatrix} for an {@code OffsetMap}.
	 *
	 * @author Waffles
	 * @since 10 Sep 2023
	 * @version 1.0
	 *
	 *
	 * @see LazyMatrix
	 */
	public class UnitToWorld extends LazyMatrix
	{
		@Override
		public Matrix compute(Integer dim)
		{
			Matrix m = Base().Matrix(dim);
			if(!Nodal().Arch().isRoot())
			{
				TransformNodal p = Nodal().Parent();
				OffsetTransform t = p.Transform();
				OffsetMap offs = t.Offset();
				
				Matrix n = offs.Matrix(dim);
				return n.times(m);
			}
			
			return m;
		}
	}

	/**
	 * A {@code WorldToUnit} defines a {@code LazyMatrix} for an {@code OffsetMap}.
	 *
	 * @author Waffles
	 * @since 10 Sep 2023
	 * @version 1.0
	 *
	 *
	 * @see LazyMatrix
	 */
	public class WorldToUnit extends LazyMatrix
	{
		@Override
		public Matrix compute(Integer dim)
		{
			Matrix m = Base().Inverse(dim);
			if(!Nodal().Arch().isRoot())
			{
				TransformNodal p = Nodal().Parent();
				OffsetTransform t = p.Transform();
				OffsetMap offs = t.Offset();
				
				Matrix n = offs.Inverse(dim);
				return m.times(n);
			}
			
			return m;
		}
	}
	
	
	private UnitToWorld utw;
	private WorldToUnit wtu;

	private TransformNodal nodal;
	
	/**
	 * Creates a new {@code OffsetMap}.
	 * 
	 * @param n  a parent nodal
	 * 
	 * 
	 * @see TransformNodal
	 */
	public OffsetMap(TransformNodal n)
	{		
		utw = new UnitToWorld();
		wtu = new WorldToUnit();
		nodal = n;
	}
	
	/**
	 * Returns an {@code OffsetMap} base.
	 * 
	 * @return  a base map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public abstract GlobalMap Base();

	/**
	 * Returns an {@code OffsetMap} nodal.
	 * 
	 * @return  a parent nodal
	 * 
	 * 
	 * @see TransformNodal
	 */
	public TransformNodal Nodal()
	{
		return nodal;
	}
	
	
	@Override
	public void setChanged()
	{
		Base().setChanged();
		
		UTW().setChanged();
		WTU().setChanged();
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
}
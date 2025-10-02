package waffles.utils.geom.spatial.maps.global.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.data.structs.Axis;
import waffles.utils.geom.spatial.maps.global.AxialMap;
import waffles.utils.geom.spatial.maps.linear.Dilation;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * An {@code AxisMap} implements an {@code AxialMap} as a sequence of linear
 * maps in the order {@code Dilation} -> {@code Translation}. If no {@code Axial}
 * is provided at construction, an internal {@code Axis} is constructed
 * which contains the spatial data.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AxialMap
 */
public class AxisMap implements AxialMap.Mutable
{	
	/**
	 * A {@code UnitToWorld} defines a {@code LazyMatrix}
	 * for an {@code AxisMap} which transforms an {@code Axial}
	 * from unit space to world space.
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
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());

			m = dilate(m, dim).destroy();
			m = translate(m, dim);

			return m;
		}
		
		Matrix translate(Matrix m, int dim)
		{
			Translation t = new Translation(Source());
			Matrix n = t.Matrix(dim).destroy();
			return n.times(m);
		}

		Matrix dilate(Matrix m, int dim)
		{
			Dilation d = new Dilation(Source());
			Matrix n = d.Matrix(dim).destroy();
			return n.times(m);
		}
	}
	
	/**
	 * A {@code WorldToUnit} defines a {@code LazyMatrix}
	 * for an {@code AxisMap} which transforms an {@code Axial}
	 * from world space to unit space.
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
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());

			m = translate(m, dim).destroy();
			m = dilate(m, dim);

			return m;
		}
		
		Matrix translate(Matrix m, int dim)
		{
			Translation t = new Translation(Source());
			Matrix mat = t.Inverse(dim).destroy();
			return mat.times(m);
		}

		Matrix dilate(Matrix m, int dim)
		{
			Dilation d = new Dilation(Source());
			Matrix mat = d.Inverse(dim).destroy();
			return mat.times(m);
		}
	}
	
	
	private Axial src;
	private UnitToWorld utw;
	private WorldToUnit wtu;
	
	/**
	 * Creates a new {@code AxisMap}.
	 * 
	 * @param s  an axial source
	 * 
	 * 
	 * @see Axial
	 */
	public AxisMap(Axial s)
	{		
		utw = new UnitToWorld();
		wtu = new WorldToUnit();
		
		src = s;
	}
	
	/**
	 * Creates a new {@code AxisMap}.
	 * 
	 * @param dim  a spatial dimension
	 */
	public AxisMap(int dim)
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
package waffles.utils.geom.spatial.maps.global.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.data.Radial;
import waffles.utils.geom.spatial.maps.data.structs.Radix;
import waffles.utils.geom.spatial.maps.global.RadialMap;
import waffles.utils.geom.spatial.maps.linear.Rotation;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * A {@code RadixMap} implements a {@code RadialMap} as a sequence of linear
 * maps in the order {@code Rotation} -> {@code Translation}. If no {@code Radial}
 * is provided at construction, an internal {@code Radix} is constructed
 * which contains the spatial data.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 *
 *
 * @see RadialMap
 */
public class RadixMap implements RadialMap.Mutable
{
	/**
	 * A {@code UnitToWorld} defines a {@code LazyMatrix}
	 * for an {@code AxisMap} which transforms an {@code Radial}
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

			m = rotate(m, dim).destroy();
			m = translate(m, dim);

			return m;
		}

		Matrix translate(Matrix m, int dim)
		{
			Translation t = new Translation(Source());
			Matrix n = t.Matrix(dim).destroy();
			return n.times(m);
		}

		Matrix rotate(Matrix m, int dim)
		{
			Rotation r = new Rotation(Source());
			Matrix n = r.Matrix(dim).destroy();
			return n.times(m);
		}
	}

	/**
	 * A {@code WorldToUnit} defines a {@code LazyMatrix}
	 * for an {@code AxisMap} which transforms an {@code Radial}
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
			m = rotate(m, dim);

			return m;
		}

		Matrix translate(Matrix m, int dim)
		{
			Translation t = new Translation(Source());
			Matrix mat = t.Inverse(dim).destroy();
			return mat.times(m);
		}

		Matrix rotate(Matrix m, int dim)
		{
			Rotation r = new Rotation(Source());
			Matrix mat = r.Inverse(dim).destroy();
			return mat.times(m);
		}
	}


	private Radial src;
	private UnitToWorld utw;
	private WorldToUnit wtu;

	/**
	 * Creates a new {@code RadixMap}.
	 *
	 * @param s  a radial source
	 *
	 *
	 * @see Radial
	 */
	public RadixMap(Radial s)
	{
		utw = new UnitToWorld();
		wtu = new WorldToUnit();

		src = s;
	}

	/**
	 * Creates a new {@code RadixMap}.
	 *
	 * @param dim  a spatial dimension
	 */
	public RadixMap(int dim)
	{
		this(new Radix(dim));
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
	public Radial Source()
	{
		return src;
	}
}
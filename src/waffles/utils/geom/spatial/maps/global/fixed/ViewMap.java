package waffles.utils.geom.spatial.maps.global.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.data.Watcher;
import waffles.utils.geom.spatial.maps.data.structs.Eye;
import waffles.utils.geom.spatial.maps.global.WatcherMap;
import waffles.utils.geom.spatial.maps.linear.Dilation;
import waffles.utils.geom.spatial.maps.linear.Projection;
import waffles.utils.geom.spatial.maps.linear.Rotation;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * A {@code ViewMap} implements a {@code WatcherMap} as a generalized pinhole camera.
 * It projects an m-dimensional vector space onto an oriented n-dimensional subspace, then
 * scales it down to normalized coordinates. These are assumed to fall inside the unit square
 * of size 2. The map triggers a sequence of linear maps in the order {@code Dilation} ->
 * {@code Projection} -> {@code Rotation} -> {@code Translation}. If no {@code Watcher}
 * is provided at construction, an internal {@code Eye} is constructed
 * which contains the spatial data.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 *
 *
 * @see WatcherMap
 */
public class ViewMap implements WatcherMap.Mutable
{
	/**
	 * A {@code CamToWorld} defines a {@code LazyMatrix}
	 * for a {@code ViewMap} which transforms a {@code Watcher}
	 * from camera space to world space.
	 *
	 * @author Waffles
	 * @since 10 Sep 2023
	 * @version 1.0
	 *
	 *
	 * @see LazyMatrix
	 */
	public class CamToWorld extends LazyMatrix
	{
		@Override
		public Matrix compute(Integer dim)
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());

			m = dilate(m, dim).destroy();
			m = project(m, dim).destroy();
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

		Matrix project(Matrix m, int dim)
		{
			Projection p = new Projection(Source());
			Matrix mat = p.Matrix(dim).destroy();
			return mat.times(m);
		}

		Matrix rotate(Matrix m, int dim)
		{
			Rotation r = new Rotation(Source());
			Matrix n = r.Matrix(dim).destroy();
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
	 * A {@code WorldToCam} defines a {@code LazyMatrix}
	 * for a {@code ViewMap} which transforms a {@code Watcher}
	 * from world space to camera space.
	 *
	 * @author Waffles
	 * @since 10 Sep 2023
	 * @version 1.0
	 *
	 *
	 * @see LazyMatrix
	 */
	public class WorldToCam extends LazyMatrix
	{
		@Override
		public Matrix compute(Integer dim)
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());

			m = translate(m, dim).destroy();
			m = rotate(m, dim).destroy();
			m = project(m, dim).destroy();
			m = dilate(m, dim);

			return m;
		}

		Matrix translate(Matrix m, int dim)
		{
			Translation t = new Translation(Source());
			Matrix mat = t.Inverse(dim).destroy();
			return mat.times(m);
		}

		Matrix project(Matrix m, int dim)
		{
			Projection p = new Projection(src);
			Matrix mat = p.Inverse(dim).destroy();
			return mat.times(m);
		}

		Matrix rotate(Matrix m, int dim)
		{
			Rotation r = new Rotation(Source());
			Matrix mat = r.Inverse(dim).destroy();
			return mat.times(m);
		}

		Matrix dilate(Matrix m, int dim)
		{
			Dilation d = new Dilation(Source());
			Matrix mat = d.Inverse(dim).destroy();
			return mat.times(m);
		}
	}


	private Watcher src;
	private CamToWorld ctw;
	private WorldToCam wtc;

	/**
	 * Creates a new {@code ViewMap}.
	 *
	 * @param s  a watcher source
	 *
	 *
	 * @see Watcher
	 */
	public ViewMap(Watcher s)
	{
		ctw = new CamToWorld();
		wtc = new WorldToCam();

		src = s;
	}

	/**
	 * Creates a new {@code ViewMap}.
	 *
	 * @param iDim  a source dimension
	 * @param oDim  a target dimension
	 */
	public ViewMap(int iDim, int oDim)
	{
		this(new Eye(iDim, oDim));
	}


	@Override
	public LazyMatrix UTW()
	{
		return wtc;
	}

	@Override
	public LazyMatrix WTU()
	{
		return ctw;
	}

	@Override
	public Watcher Source()
	{
		return src;
	}
}
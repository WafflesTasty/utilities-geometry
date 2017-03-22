package zeno.util.geom.generators;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.interfaces.IShape3D;
import zeno.util.geom.shapes.solids.Cube;
import zeno.util.geom.shapes.solids.Cuboid;

/**
 * The {@code CuboidGenerator} class defines a vertex generator for cuboids.
 * 
 * @since Mar 22, 2017
 * @author Zeno
 * 
 * @see VertexGenerator
 */
public class CuboidGenerator extends VertexGenerator
{
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @since Apr 9, 2016
	 * @author Zeno
	 * 
	 * @see VertexGenerator
	 */
	public class OrderBy extends VertexGenerator.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param gen  a target generator
		 * @see CuboidGenerator
		 */
		public OrderBy(CuboidGenerator gen)
		{
			super(gen);
		}
	

		@Override
		public int[] TriangleStrip()
		{
			return new int[]
			{
				2, 0, 6, 4, 7, 5,
				4, 0, 5, 1, 7, 3,
				1, 0, 3, 2, 7, 6
			};
		}
		
		@Override
		public int[] Triangles()
		{
			return new int[]
			{
				2, 0, 6, 0, 4, 6,
				6, 4, 7, 4, 5, 7,
				5, 1, 7, 1, 3, 7,
				3, 2, 7, 2, 6, 7,
				4, 0, 5, 0, 1, 5,
				1, 0, 3, 0, 2, 3
			};
		}
		
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				2, 0, 0, 4, 4, 6, 6, 2,
				6, 4, 4, 5, 5, 7, 7, 6,
				4, 0, 0, 1, 1, 5, 5, 4,
				5, 1, 1, 3, 3, 7, 7, 5,
				1, 0, 0, 2, 2, 3, 3, 1,
				3, 2, 2, 6, 6, 7, 7, 3
			};
		}
	}
	
		
	/**
	 * Generates vertices for a {@code Cuboid}.
	 * 
	 * @param c  a cuboid to generate
	 * @return  a list of vertices
	 * @see Vector3
	 * @see Cuboid
	 */
	public Vector3[] generate(Cuboid c)
	{
		return generate((IShape3D) c);
	}
	
	/**
	 * Generates vertices for a {@code Cube}.
	 * 
	 * @param c  a cube to generate
	 * @return  a list of vertices
	 * @see Vector3
	 * @see Cube
	 */
	public Vector3[] generate(Cube c)
	{
		return generate((IShape3D) c);
	}
		
	
	Vector3[] generate(IShape3D s)
	{
		float xmin = s.XMin();
		float xmax = s.XMax();
		float ymin = s.YMin();
		float ymax = s.YMax();
		float zmin = s.ZMin();
		float zmax = s.ZMax();
		
		return new Vector3[]
		{
			new Vector3(xmin, ymin, zmin),
			new Vector3(xmin, ymin, zmax),
			new Vector3(xmin, ymax, zmin),
			new Vector3(xmin, ymax, zmax),
			new Vector3(xmax, ymin, zmin),
			new Vector3(xmax, ymin, zmax),
			new Vector3(xmax, ymax, zmin),
			new Vector3(xmax, ymax, zmax)
		};
	}
	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
	
	@Override
	public int Count()
	{
		return 8;
	}
}
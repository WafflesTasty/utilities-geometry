package zeno.util.geom.algorithms.vertices;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.algorithms.VertexArray;
import zeno.util.geom.geometry.shapes.surfaces.Rectangle;
import zeno.util.geom.geometry.shapes.surfaces.Square;
import zeno.util.geom.geometry.types.IShape2D;

/**
 * The {@code RectangleArray} class defines a vertex generator for rectangles.
 * 
 * @since Mar 22, 2017
 * @author Zeno
 * 
 * @see VertexArray
 */
public class RectangleArray extends VertexArray
{
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @since Apr 9, 2016
	 * @author Zeno
	 * 
	 * @see VertexArray
	 */
	public class OrderBy extends VertexArray.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param gen  a target generator
		 * @see RectangleArray
		 */
		public OrderBy(RectangleArray gen)
		{
			super(gen);
		}
	

		@Override
		public int[] LineStripAdjacency()
		{
			return new int[]
			{
				2,
				0, 1, 3, 2, 0,
				1
			};
		}
		
		@Override
		public int[] LinesAdjacency()
		{
			return new int[]
			{
				2, 0, 1, 3,
				0, 1, 3, 2,
				1, 3, 2, 0,
				3, 2, 0, 1
			};
		}
		
		
		@Override
		public int[] TriangleStrip()
		{
			return new int[]
			{
				0, 1, 2, 3
			};
		}
		
		@Override
		public int[] Triangles()
		{
			return new int[]
			{
				0, 1, 3,
				0, 2, 3
			};
		}
		
		
		@Override
		public int[] LineStrip()
		{
			return new int[]
			{
				0, 1, 3, 2, 0
			};
		}
		
		@Override
		public int[] LineLoop()
		{
			return new int[]
			{
				0, 1, 3, 2
			};
		}
			
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				0, 1,
				1, 3,
				3, 2,
				2, 0
			};
		}
	}
	
		
	/**
	 * Generates vertices for a {@code Rectangle}.
	 * 
	 * @param r  a rectangle to generate
	 * @return  a list of vertices
	 * @see Rectangle
	 * @see Vector2
	 */
	public Vector2[] generate(Rectangle r)
	{
		return generate((IShape2D) r);
	}
	
	/**
	 * Generates vertices for a {@code Square}.
	 * 
	 * @param s  a square to generate
	 * @return  a list of vertices
	 * @see Vector2
	 * @see Square
	 */
	public Vector2[] generate(Square s)
	{
		return generate((IShape2D) s);
	}
		
	
	Vector2[] generate(IShape2D s)
	{
		float xmin = s.XMin();
		float xmax = s.XMax();
		float ymin = s.YMin();
		float ymax = s.YMax();
		
		return new Vector2[]
		{
			new Vector2(xmin, ymin),
			new Vector2(xmin, ymax),
			new Vector2(xmax, ymin),
			new Vector2(xmax, ymax)
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
		return 4;
	}
}
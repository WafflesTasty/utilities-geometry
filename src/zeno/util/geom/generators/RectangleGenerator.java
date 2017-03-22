package zeno.util.geom.generators;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.interfaces.IShape2D;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.geom.shapes.surfaces.Square;

/**
 * The {@code RectangleGenerator} class defines a vertex generator for rectangles.
 * 
 * @since Mar 22, 2017
 * @author Zeno
 * 
 * @see VertexGenerator
 */
public class RectangleGenerator extends VertexGenerator
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
		 * @see RectangleGenerator
		 */
		public OrderBy(RectangleGenerator gen)
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
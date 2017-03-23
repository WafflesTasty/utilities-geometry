package zeno.util.geom.algorithms.vertices;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.algorithms.VertexArray;
import zeno.util.geom.shapes.lines.Line2D;
import zeno.util.geom.shapes.lines.Line3D;

/**
 * The {@code LineArray} class defines a vertex generator for lines.
 * 
 * @since Mar 22, 2017
 * @author Zeno
 * 
 * @see VertexArray
 */
public class LineArray extends VertexArray
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
		 * @see Line2D
		 */
		public OrderBy(VertexArray gen)
		{
			super(gen);
		}
		
		
		@Override
		public int[] LineStripAdjacency()
		{
			return LinesAdjacency();
		}
		
		@Override
		public int[] LinesAdjacency()
		{
			return new int[]
			{
				1, 0, 1, 0	
			};
		}
		
		
		@Override
		public int[] LineStrip()
		{
			return Lines();
		}
		
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				0, 1	
			};
		}
	}
	
		
	/**
	 * Generates vertices for a {@code Line2D}.
	 * 
	 * @param l  a line to generate
	 * @return  a list of vertices
	 * @see Vector2
	 * @see Line2D
	 */
	public Vector2[] generate(Line2D l)
	{
		return new Vector2[]
		{
			l.P1(), l.P2()	
		};
	}
	
	/**
	 * Generates vertices for a {@code Line3D}.
	 * 
	 * @param l  a line to generate
	 * @return  a list of vertices
	 * @see Vector3
	 * @see Line3D
	 */
	public Vector3[] generate(Line3D l)
	{
		return new Vector3[]
		{
			l.P1(), l.P2()	
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
		return 2;
	}
}
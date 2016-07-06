package zeno.util.geom;

import zeno.util.algebra.vectors.Vector;

/**
 * The {@code Geometry} interface is the base for any convex geometric shape.
 *
 * @author Zeno
 * @since Aug 22, 2015
 */
public interface Geometry
{	
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @author Zeno
	 * @since Apr 9, 2016
	 */
	public class OrderBy
	{
		private Geometry geom;
		
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param geom  a target geometry
		 * @see Geometry
		 */
		public OrderBy(Geometry geom)
		{
			this.geom = geom;
		}
		
		/**
		 * Returns the target {@code Geometry}.
		 * 
		 * @return  the source geometry
		 * @see IGeometry
		 */
		protected Geometry Geometry()
		{
			return geom;
		}
		
		/**
		 * Returns a vertex order as points.
		 * 
		 * @return  a points order
		 * @see Integer
		 */
		public final int[] Points()
		{
			int vcount = geom.VertexCount();
			
			int[] order = new int[vcount];
			for(int i = 0; i < vcount; i++)
			{
				order[i] = i;
			}
			
			return order;
		}
		
		
		/**
		 * Returns a vertex order as an adjacency triangle strip.
		 * 
		 * @return  an adjacency triangle strip order
		 * @see Integer
		 */
		public int[] TriangleStripAdjacency()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by triangle strip (adjacency).");
		}
		
		/**
		 * Returns a vertex order as adjacency triangles.
		 * 
		 * @return  an adjacency triangles order
		 * @see Integer
		 */
		public int[] TrianglesAdjacency()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by triangles (adjacency).");
		}
		
		
		/**
		 * Returns a vertex order as an adjacency line strip.
		 * 
		 * @return  an adjacency line strip order
		 * @see Integer
		 */
		public int[] LineStripAdjacency()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by line strip (adjacency).");
		}
		
		/**
		 * Returns a vertex order as adjacency lines.
		 * 
		 * @return  an adjacency lines order
		 * @see Integer
		 */
		public int[] LinesAdjacency()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by lines (adjacency).");
		}
		
		
		/**
		 * Returns a vertex order as a triangle strip.
		 * 
		 * @return  a triangle strip order
		 * @see Integer
		 */
		public int[] TriangleStrip()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by triangle strip.");
		}
		
		/**
		 * Returns a vertex order as a triangle fan.
		 * 
		 * @return  a triangle fan order
		 * @see Integer
		 */
		public int[] TriangleFan()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by triangle fan.");
		}
		
		/**
		 * Returns a vertex order as triangles.
		 * 
		 * @return  a triangles order
		 * @see Integer
		 */
		public int[] Triangles()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by triangles.");
		}
		
		
		/**
		 * Returns a vertex order as a line strip.
		 * 
		 * @return  a line strip order
		 * @see Integer
		 */
		public int[] LineStrip()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by line strip.");
		}
		
		/**
		 * Returns a vertex order as a line loop.
		 * 
		 * @return  a line loop order
		 * @see Integer
		 */
		public int[] LineLoop()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by line loop.");
		}
		
		/**
		 * Returns a vertex order as lines.
		 * 
		 * @return  a lines order
		 * @see Integer
		 */
		public int[] Lines()
		{
			throw new UnsupportedOperationException("Geometry unfit to order by lines.");
		}
	}

			
	/**
	 * Returns the vertex count of the {@code Geometry}.
	 * 
	 * @return  the geometry's vertex count
	 */
	public abstract int VertexCount();

	/**
	 * Returns all the vertices of the {@code Geometry}.
	 * 
	 * @return  the geometry's vertices
	 * @see Vector
	 */
	public abstract Vector[] Vertices();

	/**
	 * Returns the {Geometry}'s vertex order methods.
	 * 
	 * @return  the geometry's order methods
	 * @see OrderBy
	 */
	public default OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
}
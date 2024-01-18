package waffles.utils.geom.utilities;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.axial.spheroid.Circle;
import waffles.utils.geom.collidable.axial.spheroid.Ellipse;
import waffles.utils.geom.collidable.axial.spheroid.Sphere;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment;
import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle;
import waffles.utils.geom.utilities.vchains.VCHCircle;
import waffles.utils.geom.utilities.vchains.VCHCuboid;
import waffles.utils.geom.utilities.vchains.VCHEllipse;
import waffles.utils.geom.utilities.vchains.VCHOctahedron;
import waffles.utils.geom.utilities.vchains.VCHSegment;
import waffles.utils.geom.utilities.vchains.VCHTriangle;

/**
 * A {@code VChain} iterates over the vertices and normals of geometric objects in predictable ways.
 * The most common chains are accessible through the {{@link #of(Geometry, Mode)} method.
 * 
 * @author Waffles
 * @since 17 Feb 2020
 * @version 1.0
 */
public interface VChain
{
	/**
	 * Chooses an optimal vertex chain implementation for an arbitrary shape.
	 * 
	 * @param s  a source shape
	 * @param m  a chain mode
	 * @return   a vertex chain
	 * 
	 * 
	 * @see Geometry
	 * @see VChain
	 * @see Mode
	 */
	public static VChain of(Geometry s, Mode m)
	{
		if(s instanceof Circle) return new VCHCircle((Circle) s, m);
		if(s instanceof Ellipse) return new VCHEllipse((Ellipse) s, m);
		if(s instanceof Sphere) return new VCHOctahedron((Sphere) s, m);
		if(s instanceof Triangle) return new VCHTriangle((Triangle) s, m);
		if(s instanceof Segment) return new VCHSegment((Segment) s, m);
		if(s instanceof HyperCuboid) return new VCHCuboid((HyperCuboid) s, m);
		
		return null;
	}
	
	/**
	 * The {@code Mode} enum defines primitive vertex chain modes.
	 * 
	 * @author Waffles
	 * @since Sep 11, 2016
	 * @version 1.0
	 * 
	 * 
	 * @see VChain
	 */
	public static enum Mode
	{
		/**
		 * Each vertex is handled as a separate point.
		 */
		POINTS,
		
		/**
		 * Each vertex is connected with the last in a line.
		 */
		LINE_STRIP,
		/**
		 * Each vertex is connected with the last in a line.
		 * </br> The first and the last connect to make a closed loop.
		 */
		LINE_LOOP,
		/**
		 * Each pair of two separate vertices form a line.
		 */
		LINES,
		
		/**
		 * Each vertex is connected with the last in a line.
		 * </br> Includes adjacent vertices at the ends.
		 */
		LINE_STRIP_ADJ,
		/**
		 * Each pair of two separate vertices form a line.
		 * </br> Includes adjacent vertices at the ends.
		 */
		LINES_ADJ,
		
		/**
		 * Each pair of three adjacent vertices form a triangle.
		 */
		TRIANGLE_STRIP,
		/**
		 * Each pair of two adjacent vertices form a triangle
		 * with the first vertex as their third.
		 */
		TRIANGLE_FAN,
		/**
		 * Each pair of three separate vertices form a triangle.
		 */
		TRIANGLES,
		
		/**
		 * Each pair of three adjacent vertices form a triangle.
		 * </br> Includes adjacent vertices at the ends.
		 */
		TRIANGLE_STRIP_ADJ,
		/**
		 * Each pair of three separate vertices form a triangle.
		 * </br> Includes adjacent vertices at the ends.
		 */
		TRIANGLES_ADJ,
		
		/**
		 * Vertices are handled by the tesselation shaders.
		 */
		PATCHES;
	}
		
		
	/**
	 * Iterates the indices of the {@code VChain}.
	 * 
	 * @return  an index iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<Integer> Indices();

	/**
	 * Iterates the vertices of the {@code VChain}.
	 * 
	 * @return  a vertex iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<Vector> Vertices();
	
	/**
	 * Iterates the normals of the {@code VChain}.
	 * 
	 * @return  a normal iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<Vector> Normals();

	
	/**
	 * Returns the vertex count of the {@code VChain}.
	 * 
	 * @return  a vertex count
	 */
	public default int VertexCount()
	{
		int count = 0;
		for(Vector v : Vertices())
		{
			count++;
		}
		
		return count;
	}
	
	/**
	 * Returns the index count of the {@code VChain}.
	 * 
	 * @return  an index count
	 */
	public default int IndexCount()
	{
		int count = 0;
		for(int i : Indices())
		{
			count++;
		}
		
		return count;
	}
}
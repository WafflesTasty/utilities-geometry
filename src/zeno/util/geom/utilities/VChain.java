package zeno.util.geom.utilities;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ITriangle;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.collidables.geometry.planar.Ellipse;
import zeno.util.geom.collidables.geometry.spatial.Sphere;
import zeno.util.geom.utilities.chains.VCHCircle;
import zeno.util.geom.utilities.chains.VCHCuboid;
import zeno.util.geom.utilities.chains.VCHEllipse;
import zeno.util.geom.utilities.chains.VCHOctahedron;
import zeno.util.geom.utilities.chains.VCHSegment;
import zeno.util.geom.utilities.chains.VCHTriangle;

/**
 * The {@code VChain} interface defines an object decomposed into a vertex chain.
 * </br> The object is defined by a set of vertices ordered according to an index array.
 * 
 * @author Waffles
 * @since 17 Feb 2020
 * @version 1.0
 */
public interface VChain
{
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
		 * Adjacent version of {@code LINE_STRIP} for use
		 * with geometry shaders.
		 */
		LINE_STRIP_ADJ,
		/**
		 * Adjacent version of {@code LINES} for use
		 * with geometry shaders.
		 */
		LINES_ADJ,
		
		/**
		 * Each pair of three adjacent vertices form a triangle.
		 */
		TRIANGLE_STRIP,
		/**
		 * Each pair of two adjacent vertices form a triangle
		 * with the first vertex as its third.
		 */
		TRIANGLE_FAN,
		/**
		 * Each pair of three separate vertices form a triangle.
		 */
		TRIANGLES,
		
		/**
		 * Adjacent version of {@code TRIANGLE_STRIP} for use
		 * with geometry shaders.
		 */
		TRIANGLE_STRIP_ADJ,
		/**
		 * Adjacent version of {@code TRIANGLES} for use
		 * with geometry shaders.
		 */
		TRIANGLES_ADJ,
		
		/**
		 * Vertices are handled by the tesselation shaders.
		 */
		PATCHES;
	}
	
	
	/**
	 * Chooses an optimal vertex chain for an arbitrary shape.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * @return  a vertex chain
	 * 
	 * 
	 * @see IGeometry
	 * @see VChain
	 * @see Mode
	 */
	public static VChain of(IGeometry shape, Mode mode)
	{
		if(shape instanceof Circle) return new VCHCircle((Circle) shape, mode);
		if(shape instanceof Ellipse) return new VCHEllipse((Ellipse) shape, mode);
		if(shape instanceof Sphere) return new VCHOctahedron((Sphere) shape, mode);
		if(shape instanceof ITriangle) return new VCHTriangle((ITriangle) shape, mode);
		if(shape instanceof ISegment) return new VCHSegment((ISegment) shape, mode);
		if(shape instanceof ICuboid) return new VCHCuboid((ICuboid) shape, mode);
		
		return null;
	}
	
		
	/**
	 * Returns the indices of the {@code VChain}.
	 * 
	 * @return  an index iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<Integer> Indices();

	/**
	 * Returns the vertices of the {@code VChain}.
	 * 
	 * @return  a vertex iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<Vector> Vertices();
	
	/**
	 * Returns the normals of the {@code VChain}.
	 * 
	 * @return  a normal iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<Vector> Normals();
}
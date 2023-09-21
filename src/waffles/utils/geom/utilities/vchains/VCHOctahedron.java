package waffles.utils.geom.utilities.vchains;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.axial.spheroid.Sphere;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.tools.collections.Iterables;
import waffles.utils.tools.collections.iterators.counters.IntegerCounter;

/**
 * A {@code VCHOctahedron} generates vertex chains for an {@code Octahedron}.
 *
 * @author Waffles
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHOctahedron implements VChain
{	
	private static Integer[] LINES = new Integer[]
	{
		0, 2, 2, 3, 3, 5, 5, 0,
		1, 0, 1, 2, 1, 3, 1, 5,
		4, 0, 4, 2, 4, 3, 4, 5
	};
		
	private static Integer[] TRIANGLES = new Integer[]
	{
		0, 1, 2,  1, 2, 3,  1, 3, 5,  0, 1, 5,
		0, 2, 4,  2, 3, 4,  3, 4, 5,  0, 4, 5
	};
	
	private static Vector3[] VERTICES = new Vector3[6];
	
	static
	{
		VERTICES[ 0] = new Vector3(+1f,  0f,  0f);
		VERTICES[ 1] = new Vector3( 0f, +1f,  0f);
		VERTICES[ 2] = new Vector3( 0f,  0f, +1f);
		
		VERTICES[ 3] = new Vector3(-1f,  0f,  0f);
		VERTICES[ 4] = new Vector3( 0f, -1f,  0f);
		VERTICES[ 5] = new Vector3( 0f,  0f, -1f);
	}
		
	
	
	private Mode mode;
	private Vector3 cen;
	private float rad;
	
	/**
	 * Creates a new {@code VCHOctahedron}.
	 * 
	 * @param s  a source sphere
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Sphere
	 */
	public VCHOctahedron(Sphere s, Mode m)
	{
		this(s.Origin(), s.Radius(), m);
	}
	
	/**
	 * Creates a new {@code VCHOctahedron}.
	 * 
	 * @param c  an octahedron center
	 * @param r  an octahedron radius
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Vector3
	 */
	public VCHOctahedron(Vector3 c, float r, Mode m)
	{
		rad  = r;
		cen = c;
		mode = m;
	}
	
	/**
	 * Creates a new {@code VCHOctahedron}.
	 * 
	 * @param r  an octahedron radius
	 * @param m  a chain mode

	 */
	public VCHOctahedron(float r, Mode m)
	{
		this(new Vector3(), r, m);
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		switch(mode)
		{
		case LINES:
			return Iterables.of(LINES);
		case TRIANGLES:
			return Iterables.of(TRIANGLES);
		case POINTS:
			return () -> new IntegerCounter(6);
		case LINES_ADJ:
		case LINE_LOOP:
		case LINE_STRIP:
		case LINE_STRIP_ADJ:
		case PATCHES:
		case TRIANGLE_FAN:
		case TRIANGLES_ADJ:
		case TRIANGLE_STRIP:
		case TRIANGLE_STRIP_ADJ:
		default:
			return null;
		}
	}
	
	@Override
	public Iterable<Vector> Vertices()
	{
		return () -> new Iterator<>()
		{
			private Iterator<Vector> norms = Normals().iterator();
			
			
			@Override
			public boolean hasNext()
			{
				return norms.hasNext();
			}

			@Override
			public Vector3 next()
			{
				return (Vector3) norms.next().plus(cen);
			}
		};
	}
	
	@Override
	public Iterable<Vector> Normals()
	{		
		return () -> new Iterator<>()
		{
			private int curr;
			
			@Override
			public boolean hasNext()
			{
				return curr < 6;
			}

			@Override
			public Vector3 next()
			{
				Vector3 v = VERTICES[curr++];
				return v.times(rad);
			}
		};
	}
}
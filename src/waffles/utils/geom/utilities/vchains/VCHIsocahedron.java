package waffles.utils.geom.utilities.vchains;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.axial.spheroid.Sphere;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.tools.collections.Iterables;
import waffles.utils.tools.collections.iterators.counters.IntegerCounter;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code VCHIsocahedron} generates vertex chains for an {@code Isocahedron}.
 *
 * @author Waffles
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHIsocahedron implements VChain
{	
	private static Integer[] LINES = new Integer[]
	{
		0,  1,  1,  2, 2,  0,   1,  3,  3,  2,  3, 4,
		4,  2,  3,  5, 5,  4,   0,  6,  6,  2,  6, 4,
		6,  7,  7,  4, 5,  7,   0,  8,  8,  6,  8, 7,
		7,  9,  9,  8, 5,  9,   0, 10, 10,  8, 10, 9,
		9, 11, 11, 10, 5, 11,  10,  1,  1, 11, 11, 3
		
	};
		
	private static Integer[] TRIANGLES = new Integer[]
	{			
		0,  2,  1,   1,  2,  3,   2,  4,  3,   3,  4, 5,
		0,  6,  2,   2,  6,  4,   6,  7,  4,   4,  7, 5,
		0,  8,  6,   6,  8,  7,   8,  9,  7,   7,  9, 5,
		0, 10,  8,   8, 10,  9,  10, 11,  9,   9, 11, 5,
		0,  1, 10,  10,  1, 11,   1,  3, 11,  11,  3, 5
	};
	
	private static Vector3[] VERTICES = new Vector3[12];
	
	static
	{
		float phi = Floats.GOLD_RATIO;		
		float cos = phi / Floats.sqrt(phi + 2);
		float sin =   1 / Floats.sqrt(phi + 2);
		
		
		VERTICES[ 0] = new Vector3(0f, +cos, +sin);
		VERTICES[ 1] = new Vector3(0f, +cos, -sin);
		VERTICES[ 2] = new Vector3(+cos, +sin, 0f);
		VERTICES[ 3] = new Vector3(+sin, 0f, -cos);
		
		VERTICES[ 4] = new Vector3(+cos, -sin, 0f);
		VERTICES[ 5] = new Vector3(0f, -cos, -sin);
		VERTICES[ 6] = new Vector3(+sin, 0f, +cos);
		VERTICES[ 7] = new Vector3(0f, -cos, +sin);
		
		VERTICES[ 8] = new Vector3(-sin, 0f, +cos);
		VERTICES[ 9] = new Vector3(-cos, -sin, 0f);
		VERTICES[10] = new Vector3(-cos, +sin, 0f);
		VERTICES[11] = new Vector3(-sin, 0f, -cos);
	}
		
	
	
	private Mode mode;
	private Vector3 cen;
	private float rad;
	
	/**
	 * Creates a new {@code VCHIsocahedron}.
	 * 
	 * @param s  a source sphere
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Sphere
	 */
	public VCHIsocahedron(Sphere s, Mode m)
	{
		this(s.Origin(), s.Radius(), m);
	}
	
	/**
	 * Creates a new {@code VCHIsocahedron}.
	 * 
	 * @param c  an isocahedron center
	 * @param r  an isocahedron radius
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Vector3
	 */
	public VCHIsocahedron(Vector3 c, float r, Mode m)
	{
		cen = c;
		rad  = r;
		mode = m;
	}
	
	/**
	 * Creates a new {@code VCHIsocahedron}.
	 * 
	 * @param r  an isocahedron radius
	 * @param m  a chain mode
	 */
	public VCHIsocahedron(float r, Mode m)
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
			return () -> new IntegerCounter(12);
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
				return curr < 12;
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
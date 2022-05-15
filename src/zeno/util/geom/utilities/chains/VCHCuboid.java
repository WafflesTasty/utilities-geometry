package zeno.util.geom.utilities.chains;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.VChain;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Iterables;
import zeno.util.tools.helper.iterators.counters.IntegerCounter;

/**
 * The {@code VCHCuboid} class defines a {@code VChain} for cuboid shapes.
 *
 * @author Zeno
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHCuboid implements VChain
{	
	private static Integer[] LINES_2D = new Integer[]
	{
		0, 2, 2, 3,  3, 1, 1, 0
	};
	
	private static Integer[] LINES_ADJ_2D = new Integer[]
	{
		1, 0, 2, 3,  0, 2, 3, 1,
		2, 3, 1, 0,  3, 1, 0, 2
	};
	
	private static Integer[] LINE_LOOP_2D = new Integer[]
	{
		0, 2, 3, 1
	};
	
	private static Integer[] LINE_STRIP_2D = new Integer[]
	{
		0, 2, 3, 1, 0
	};
	
	private static Integer[] LINE_STRIP_ADJ_2D = new Integer[]
	{
		1, 0, 2, 3, 1, 0, 2
	};
	
	private static Integer[] TRIANGLES_2D = new Integer[]
	{
		0, 2, 3, 0, 1, 3
	};
	
	private static Integer[] TRIANGLE_FAN_2D = new Integer[]
	{
		0, 1, 3, 2
	};
	
	private static Integer[] TRIANGLE_STRIP_2D = new Integer[]
	{
		0, 2, 1, 3
	};
	

	private static Integer[] LINES_3D = new Integer[]
	{
		2, 0, 0, 1,  1, 3, 3, 2,
		3, 1, 1, 5,  5, 7, 7, 3,
		1, 0, 0, 4,  4, 5, 5, 1,
		5, 4, 4, 6,  6, 7, 7, 5,
		4, 0, 0, 2,  2, 6, 6, 4,
		6, 2, 2, 3,  3, 7, 7, 6
	};
	
	private static Integer[] TRIANGLES_3D = new Integer[]
	{
		2, 0, 3,  0, 1, 3,
		3, 1, 7,  1, 5, 7,
		5, 4, 7,  4, 6, 7,
		6, 2, 7,  2, 3, 7,
		1, 0, 5,  0, 4, 5,
		4, 0, 6,  0, 2, 6
	};

	private static Integer[] TRIANGLE_STRIP_3D = new Integer[]
	{
		2, 0, 3, 1, 7, 5,
		1, 0, 5, 4, 7, 6,
		4, 0, 6, 2, 7, 3
	};
	
	
	private Mode mode;
	private ICuboid shape;
	
	/**
	 * Creates a new {@code VCHCuboid}.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * 
	 * 
	 * @see ICuboid
	 */
	public VCHCuboid(ICuboid shape, Mode mode)
	{
		this.shape = shape;
		this.mode  = mode;
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		int dim = shape.Dimension();
		
		switch(mode)
		{
		case LINES:
			return dim == 2 ? Iterables.of(LINES_2D) : (dim == 3 ? Iterables.of(LINES_3D) : null);
		case LINES_ADJ:
			return dim == 2 ? Iterables.of(LINES_ADJ_2D) : null;
		case LINE_LOOP:
			return dim == 2 ? Iterables.of(LINE_LOOP_2D) : null;
		case LINE_STRIP:
			return dim == 2 ? Iterables.of(LINE_STRIP_2D) : null;
		case LINE_STRIP_ADJ:
			return dim == 2 ? Iterables.of(LINE_STRIP_ADJ_2D) : null;
		case TRIANGLES:
			return dim == 2 ? Iterables.of(TRIANGLES_2D) : (dim == 3 ? Iterables.of(TRIANGLES_3D) : null);
		case TRIANGLE_STRIP:
			return dim == 2 ? Iterables.of(TRIANGLE_STRIP_2D) : (dim == 3 ? Iterables.of(TRIANGLE_STRIP_3D) : null);
		case TRIANGLE_FAN:
			return dim == 2 ? Iterables.of(TRIANGLE_FAN_2D) : null;
		case POINTS:
			return () -> new IntegerCounter(Integers.pow(2, dim));
		case PATCHES:
		case TRIANGLES_ADJ:
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
			public Vector next()
			{
				return norms.next().plus(shape.Center());
			}
		};
	}
	
	@Override
	public Iterable<Vector> Normals()
	{
		return () -> new Iterator<>()
		{
			private int i = 0;
			private Vector min = shape.Size().times(-0.5f);
			private Vector max = shape.Size().times(+0.5f);
			
			
			@Override
			public boolean hasNext()
			{
				return i < Integers.pow(2, shape.Dimension());
			}

			@Override
			public Vector next()
			{
				Vector v = Vectors.create(shape.Dimension());
				for(int j = 0; j < shape.Dimension(); j++)
				{
					if(Integers.bitAt(i, j) == 0)
						v.set(min.get(j), j);
					else
						v.set(max.get(j), j);
				}
				
				i++; return v;
			}
		};
	}
}
package waffles.utils.geom.utilities.chiral.arrows;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.geom.utilities.chiral.Chirality;
import waffles.utils.lang.utilities.enums.Sign;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Cardinal} defines an n-dimensional cardinal arrow.
 * These are useful in performing relative index operations.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Arrow
 */
public class Cardinal extends Arrow
{
	/**
	 * Constructs a {@code Cardinal} from a {@code Point}.
	 * 
	 * @param p  an affine point
	 * @return  a cardinal arrow
	 * 
	 * 
	 * @see Point
	 */
	public static Cardinal create(Point p)
	{
		int d = p.Dimension();

		if(d == 2)
		{
			float x = p.aff(0);
			float y = p.aff(1);
			
			return Cardinal2D.create(x, y);
		}
		
		if(d == 3)
		{
			float x = p.aff(0);
			float y = p.aff(1);
			float z = p.aff(2);
			
			return Cardinal3D.create(x, y, z);
		}
		
		float[] vals = new float[d];
		for(int k = 0; k < d; k++)
		{
			float v = p.aff(k);
			vals[k] = Floats.sign(v);
		}
		
		return new Cardinal(vals);
	}
	
	/**
	 * Converts a {@code Sign} set to a floating-point array.
	 * 
	 * @param set  a sign set
	 * @return  a float array
	 * 
	 * 
	 * @see Sign
	 */
	public static float[] convert(Sign... set)
	{
		float[] val = new float[set.length];
		for(int k = 0; k < set.length; k++)
		{
			val[k] = set[k].Value();
		}
		
		return val;
	}
		
	/**
	 * A {@code Cardinal.Factory} generates {@code Cardinals}.
	 *
	 * @author Waffles
	 * @since 26 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arrow
	 */
	public class Factory extends Point.Factory
	{
		/**
		 * Creates a new {@code Factory}.
		 */
		public Factory()
		{
			super(Cardinal.this);
		}

		
		@Override
		public Transformator create(Matrix s)
		{
			if(s.Columns() == 0)
			{
				int n = s.Rows();
				return new Void(n);
			}
			
			Transformator p = super.create(s);
			return Cardinal.create((Point) p);
		}
		
		@Override
		public Vector Span()
		{
			int n = Dimension();
			Vector s = Vectors.create(n + 1);
			for(int k = 0; k < n; k++)
			{
				s.set(aff(k), k);
			}

			return s;
		}
	}	
	
	
	/**
	 * Creates a new {@code Cardinal}.
	 * 
	 * @param set  a sign set
	 * 
	 * 
	 * @see Sign
	 */
	public Cardinal(Sign... set)
	{
		this(convert(set));
	}
	
	/**
	 * Creates a new {@code Cardinal}.
	 * 
	 * @param vals  cardinal values
	 */
	protected Cardinal(float... vals)
	{
		super(vals);
	}
	
	/**
	 * Rotates the {@code Cardinal}.
	 * 
	 * @param c  a chirality
	 * @return   a cardinal
	 * 
	 * 
	 * @see Chirality
	 */
	public Cardinal spin(Chirality c)
	{
		return (Cardinal) c.map(this);
	}
	
	/**
	 * Flips the {@code Cardinal}.
	 * 
	 * @return  a cardinal
	 */
	public Cardinal flip()
	{
		Vector v = times(-1f).Vector();
		return new Cardinal(v.Data().Array());
	}


	@Override
	public Factory Factory()
	{
		return new Factory();
	}
}
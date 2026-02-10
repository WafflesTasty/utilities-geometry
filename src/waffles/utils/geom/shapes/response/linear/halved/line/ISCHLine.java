package waffles.utils.geom.shapes.response.linear.halved.line;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.linear.ISCLSpace;

/**
 * An {@code ISCHLine} computes an intersection {@code Response} between two halflines.
 *
 * @author Waffles
 * @since 11 Dec 2025
 * @version 1.1
 *
 * 
 * @see ISCLSpace
 */
public class ISCHLine extends ISCLSpace
{
	/**
	 * The {@code Hints} interface defines hints for a {@code ISCHLine}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see ISCLSpace
	 */
	public static interface Hints extends ISCLSpace.Hints
	{		
		@Override
		public abstract HLine T();
		
		@Override
		public abstract HLine S();
	}

	
	
	/**
	 * Creates a new {@code ISCHLine}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCHLine(Hints h)
	{
		super(h);
	}
	
	/**
	 * Creates a new {@code ISCHLine}.
	 *
	 * @param s  a source line
	 * @param t  a target line
	 *
	 *
	 * @see HLine
	 */
	public ISCHLine(HLine s, HLine t)
	{
		this(new Hints()
		{
			@Override
			public HLine S()
			{
				return s;
			}
			
			@Override
			public HLine T()
			{
				return t;
			}
		});
	}
	
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			Point y = Y();
			Matrix d = Direction();
			
			if(d.Columns() > 0)
			{
				Vector v = Hints().S().Direction();
				Vector w = Hints().T().Direction();
				
				Point x = y.plus(d);
				if(v.dot(w) < 0)
				{
					return Segment.create(x, y);
				}
			}

			return HLine.create(y, d);
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public Hints Hints()
	{
		return (Hints) super.Hints();
	}
	
	@Override
	public Vector L()
	{
		Vector l = super.L();
		if(l.get(0) < 0f)
		{
			l.set(0f, 0);
		}

		return l;
	}
	
	@Override
	public Vector M()
	{
		Vector m = super.M();
		if(m.get(0) > 0f)
		{
			m.set(0f, 0);
		}

		return m;
	}
}
package waffles.utils.geom.collide.response.linear.halved;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTASpace} computes a containment {@code Response} between a halfspace and an affine space.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class CNTASpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTASpace}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see Algorithmic
	 */
	public static interface Hints extends Algorithmic
	{
		/**
		 * Returns the target space of the {@code Hints}.
		 * 
		 * @return  a target space
		 * 
		 * 
		 * @see ASpace
		 */
		public abstract ASpace T();
		
		/**
		 * Returns the source space of the {@code Hints}.
		 * 
		 * @return  a source space
		 * 
		 * 
		 * @see HSpace
		 */
		public abstract HSpace S();
				
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	

	private Hints hints;
	private Boolean hasImpact;
	private Response rsp;

	/**
	 * Creates a new {@code CNTASpace}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public CNTASpace(Hints h)
	{
		hints = h;
		
		HSpace s = Hints().S();
		ASpace t = Hints().T();
		
		Point o = t.Origin();
		rsp = s.contain(o);
	}

	/**
	 * Creates a new {@code CNTASpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 * @see ASpace
	 */
	public CNTASpace(HSpace s, ASpace t)
	{
		this(new Hints()
		{
			@Override
			public HSpace S()
			{
				return s;
			}
			
			@Override
			public ASpace T()
			{
				return t;
			}
		});
	}

	/**
	 * Returns {@code Response} hints.
	 * 
	 * @return  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}


	@Override
	public HSpace Source()
	{
		return Hints().S();
	}

	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Hints().T();
		}

		int n = Dimension();
		return new Void(n);
	}

	@Override
	public boolean hasImpact()
	{
		if(hasImpact == null)
		{
			HSpace s = Hints().S();
			ASpace t = Hints().T();
			Vector v = s.Normal();
			Point o = t.Origin();
			
			hasImpact = false;
			if(rsp.hasImpact())
			{
				hasImpact = t.follows(v);
			}
		}

		return hasImpact;
	}

	@Override
	public Point Contact()
	{
		return Hints().T().Origin();
	}

	@Override
	public int cost()
	{
		return 2 * rsp.cost();
	}
}
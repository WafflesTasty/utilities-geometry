package waffles.utils.geom.response.hulls;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCConvex} computes an intersection response between convex sets.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCConvex implements Response
{
	private int dim;
	private Response rsp;
	private ConvexSet src, tgt;
	
	/**
	 * Creates a new {@code ISCConvex}.
	 * 
	 * @param s  a source convex
	 * @param t  a target convex
	 * 
	 * 
	 * @see ConvexSet
	 */
	public ISCConvex(ConvexSet s, ConvexSet t)
	{
		dim = src.Dimension();
		ConvexSet diff = difference();
		Vector o = Vectors.create(dim);
		rsp = diff.contain(o);
		
		src = s;
		tgt = t;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
		}
		
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{		
		return rsp.hasImpact();
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			return rsp.Penetration();
		}

		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return rsp.Distance();
		}

		return null;
	}
	
	@Override
	public Point Contact()
	{
		return null;
	}

	@Override
	public int Cost()
	{
		if(rsp == null)
		{
			rsp = computeResponse();
		}
		
		return 2 * rsp.Cost();
	}


	Response computeResponse()
	{
		int dim = src.Dimension();
		ConvexSet diff = difference();
		
		Vector o = Vectors.create(dim);
		return diff.contain(o);
	}
	
	ConvexSet difference()
	{
		return new ConvexSet()
		{
			@Override
			public Bounds Bounds()
			{
				return new Bounds()
				{
					@Override
					public Vector Minimum()
					{
						Vector sMin = src.Bounds().Minimum();
						Vector tMax = tgt.Bounds().Maximum();

						return sMin.minus(tMax);
					}
					
					@Override
					public Vector Maximum()
					{
						Vector sMax = src.Bounds().Maximum();
						Vector tMin = tgt.Bounds().Minimum();
						
						return sMax.minus(tMin);
					}
					
					@Override
					public float Diameter()
					{
						Vector s = Size();

						float sMax = 0f;
						for(int i = 0; i < s.Size(); i++)
						{
							float val = Floats.abs(s.get(i));
							if(sMax < val)
							{
								sMax = val;
							}
						}

						return sMax;
					}
				};
			}
			
			@Override
			public Bounds Bounds(GlobalMap map)
			{
				return null;
			}
			
			@Override
			public Extremum Extremum()
			{			
				return v ->
				{
					Extremum sExt = src.Extremum();
					Extremum tExt = tgt.Extremum();
					
					Vector v1 = tExt.along(v.times(+1f));
					if(v1 == null) return null;
					Vector v2 = sExt.along(v.times(-1f));
					if(v2 == null) return null;
					return v1.minus(v2);
				};
			}
		};
	}
}
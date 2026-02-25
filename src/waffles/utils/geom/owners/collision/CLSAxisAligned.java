package waffles.utils.geom.owners.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.owners.collision.response.CNTTransformator;
import waffles.utils.geom.owners.collision.response.ISCTransformator;
import waffles.utils.geom.owners.spatial.AxisAligned;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.RSPFlipped;
import waffles.utils.geom.spatial.maps.global.AxialMap;

/**
 * A {@code CLSAxisAligned} defines {@code Collision} for an {@code AxisAligned}.
 *
 * @author Waffles
 * @since Jul 25, 2019
 * @version 1.0
 *
 *
 * @see CLSGeometrical
 */
public class CLSAxisAligned extends CLSGeometrical
{
	/**
	 * Creates a new {@code CLSAxisAligned}.
	 *
	 * @param s  an axis-aligned source
	 *
	 *
	 * @see AxisAligned
	 */
	public CLSAxisAligned(AxisAligned s)
	{
		super(s);
	}


	@Override
	public Response inhabit(Collidable c)
	{
		AxisAligned s = Source();
		
		Geometry g = s.Shape();
		// For a source axial set...
		if(g instanceof AxialSet)
		{
			AxialMap m = s.Transform();
			AxialSet a = (AxialSet) g;
			a = (AxialSet) m.map(a);
			
			// ...eliminate everything.
			Response rsp = c.contain(a);
			return new RSPFlipped(rsp);
		}

		return super.inhabit(c);
	}

	@Override
	public Response intersect(Collidable c)
	{
		AxisAligned s = Source();

		Geometry g = s.Shape();
		// For a source axial set...
		if(g instanceof AxialSet)
		{
			AxialMap m = s.Transform();
			AxialSet a = (AxialSet) g;
			a = (AxialSet) m.map(a);
			
			// ...eliminate everything.
			Response rsp = c.intersect(a);
			return new RSPFlipped(rsp);
		}
		
		// Eliminate axial sets.
		if(c instanceof AxialSet)
		{
			AxialSet t = (AxialSet) c;
			
			return new ISCTransformator(s, t);
		}

		return super.intersect(c);
	}
	
	@Override
	public Response contain(Collidable c)
	{
		AxisAligned s = Source();
				
		Geometry g = s.Shape();
		// For a source axial set...
		if(g instanceof AxialSet)
		{
			// ...eliminate geometry.
			if(c instanceof Geometry)
			{
				AxialMap m = s.Transform();
				AxialSet a = (AxialSet) g;
				a = (AxialSet) m.map(a);			
				return a.contain(c);				
			}
		}
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			
			return new CNTTransformator(s, t);
		}
		
		// Eliminate axial sets.
		if(c instanceof AxialSet)
		{
			AxialSet t = (AxialSet) c;
			
			return new CNTTransformator(s, t);
		}
		
		return super.contain(c);
	}

	@Override
	public AxisAligned Source()
	{
		return (AxisAligned) super.Source();
	}
}
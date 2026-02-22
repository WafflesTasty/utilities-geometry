package waffles.utils.geom.owners.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.owners.collision.response.CNTTransformator;
import waffles.utils.geom.owners.spatial.AffineOriented;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.RSPFlipped;
import waffles.utils.geom.spatial.maps.global.RadialMap;

/**
 * A {@code CLSAffineOriented} defines {@code Collision} for an {@code AffineOriented}.
 *
 * @author Waffles
 * @since Jul 25, 2019
 * @version 1.0
 *
 *
 * @see CLSGeometrical
 */
public class CLSAffineOriented extends CLSGeometrical
{
	/**
	 * Creates a new {@code CLSAffineOriented}.
	 *
	 * @param s  an affine-oriented source
	 *
	 *
	 * @see AffineOriented
	 */
	public CLSAffineOriented(AffineOriented s)
	{
		super(s);
	}


	@Override
	public Response inhabit(Collidable c)
	{
		AffineOriented s = Source();
		
		Geometry g = s.Shape();
		// For a source sphere...
		if(g instanceof HyperSphere)
		{
			HyperSphere h = (HyperSphere) g;
			RadialMap m = s.Transform();
			
			Point o = h.Origin();
			o = (Point) m.map(o);
			float r = h.Radius();
			
			// ...eliminate everything.
			h = HyperSphere.create(o, r);
			Response rsp = c.contain(h);
			return new RSPFlipped(rsp);
		}

		return super.inhabit(c);
	}

	@Override
	public Response intersect(Collidable c)
	{
		AffineOriented s = Source();

		Geometry g = s.Shape();
		// For a source sphere...
		if(g instanceof HyperSphere)
		{
			HyperSphere h = (HyperSphere) g;
			RadialMap m = s.Transform();
			
			Point o = h.Origin();
			o = (Point) m.map(o);
			float r = h.Radius();
			
			// ...eliminate everything.
			return h.intersect(c);
		}

		return super.intersect(c);
	}
	
	@Override
	public Response contain(Collidable c)
	{
		AffineOriented s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			
			return new CNTTransformator(s, t);
		}
		
		Geometry g = s.Shape();
		// For a source sphere...
		if(g instanceof HyperSphere)
		{
			// ...eliminate geometry.
			if(c instanceof Geometry)
			{
				HyperSphere h = (HyperSphere) g;
				RadialMap m = s.Transform();
				
				Point o = h.Origin();
				o = (Point) m.map(o);
				float r = h.Radius();
				
				h = HyperSphere.create(o, r);		
				return h.contain(c);				
			}
		}

		return super.contain(c);
	}

	@Override
	public AffineOriented Source()
	{
		return (AffineOriented) super.Source();
	}
}
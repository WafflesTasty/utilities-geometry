package zeno.util.geom.collidables.collisions;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.Affine;
import zeno.util.geom.ICollidable;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.ICollision.Response;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSShaped} class defines collision for a shaped object.
 *
 * @author Zeno
 * @since Jul 25, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSShaped implements ICollision
{
	/**
	 * The {@code RSPAffine} class defines collision response with an affine space.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Response
	 */
	public class RSPAffine implements Response
	{
		private Affine space;
		private ICollidable shape;
		private Response response;
		
		/**
		 * Creates a new {@code RSPAffine}.
		 * 
		 * @param space  an affine space
		 */
		public RSPAffine(Affine space)
		{
			this.space = space;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Response().isEmpty();
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				response = Response();
				if(response.isEmpty())
					shape = Geometries.VOID;
				else
				{
					ITransformation tform = src.Transform();
					Affine a = (Affine) response.Shape();
					shape = tform.map(a);
				}
			}

			return shape;
		}

		@Override
		public Vector Penetration()
		{
			return null;
		}
		
		@Override
		public Vector Distance()
		{
			return null;
		}
		
		
		private Response Response()
		{
			if(response == null)
			{
				IGeometry shape = src.Shape();
				ITransformation tform = src.Transform();
				response = shape.intersect(tform.unmap(space));
			}
			
			return response;
		}
	}
	
	/**
	 * The {@code RSPPoint} class defines collision response with points.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Response
	 */
	public class RSPPoint implements Response
	{
		private Point p;
		private Response rsp;
		private Vector dst, pnt;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public RSPPoint(Point p)
		{
			this.p = p;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Response().isEmpty();
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;
			return p;
		}

		@Override
		public Vector Penetration()
		{
			if(pnt == null)
			{
				Point p = new Point(rsp.Penetration(), 0);
				ITransformation tform = src.Transform();
				p = (Point) tform.unmap(p);
				pnt = p.asVector();
			}

			return pnt;
		}
		
		@Override
		public Vector Distance()
		{
			if(dst == null)
			{
				Point p = new Point(rsp.Distance(), 0);
				ITransformation tform = src.Transform();
				p = (Point) tform.unmap(p);
				dst = p.asVector();
			}
			
			return dst;
		}
		
		
		private Response Response()
		{
			if(rsp == null)
			{
				IGeometry shape = src.Shape();
				ITransformation tform = src.Transform();
				rsp = shape.contain((Point) tform.unmap(p));
			}
			
			return rsp;
		}
	}
	
	
	private IGeometrical src;
	
	/**
	 * Creates a new {@code CLSShaped}.
	 * 
	 * @param s  a geometrical source
	 * 
	 * 
	 * @see IGeometrical
	 */
	public CLSShaped(IGeometrical s)
	{
		src = s;
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		ITransformation tform = src.Transform();
		IGeometry shape = src.Shape();
		
		if(c instanceof ISegment)
		{
			return shape.contains(tform.unmap((ISegment) c));
		}
		
		if(c instanceof Affine)
		{		
			if(!c.equals(Geometries.VOID))
			{
				return shape.contains(tform.unmap((Affine) c));
			}
			
			return true;
		}
		
		return null;
	}

	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate void geometry.
		if(c.equals(Geometries.VOID))
		{
			return c.intersect(src);
		}
		
		// Eliminate point geometry.
		if(c instanceof Point)
		{
			return contain((Point) c);
		}
		
		// Eliminate affine geometry.
		if(c instanceof Affine)
		{
			return new RSPAffine((Affine) c);
		}
		
		return null;
	}
	
	@Override
	public Response contain(Point p)
	{
		return new RSPPoint(p);
	}
}
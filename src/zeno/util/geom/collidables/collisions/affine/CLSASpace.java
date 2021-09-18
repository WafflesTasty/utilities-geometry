package zeno.util.geom.collidables.collisions.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code CLSASpace} class defines collision for an {@code Affine} {@link ASpace}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSASpace implements ICollision
{
	/**
	 * The {@code RSPASpace} class defines collision response for an affine space.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPASpace implements Response
	{
		private Vector x;
		private ASpace t;
		private ICollidable shape;
		
		/**
		 * Creates a new {@code RSPASpace}.
		 * 
		 * @param t  a target space
		 * 
		 * 
		 * @see ASpace
		 */
		public RSPASpace(ASpace t)
		{
			this.t = t;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return X() == null;
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				if(X() == null)
				{
					// The intersection is empty.
					shape = Geometries.VOID;
					return shape;
				}
				
				Vector p = s.Origin().asVector();				
				VSpace dir = s.Direction().add(t.Direction());
				// Otherwise, a common point is found.
				
				
				x = Vectors.resize(x, p.Size());
				x = s.Direction().Span().times(x);
				Point o = new Point(p.plus(x), 1f);
				
				Matrix m = dir.RowComplement();
				int rows = s.Dimension(); int cols = m.Columns();
				// Calculate the direction intersection.
				m = Matrices.resize(m, rows, cols);
				m = dir.Span().times(m);
				
				// Create the new affine subspace.
				VSpace v = VSpaces.create(m);
				shape = Geometries.span(o, v);
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
		
		private Vector X()
		{
			if(x == null)
			{
				Vector p = s.Origin().asVector();
				Vector q = t.Origin().asVector();
				
				VSpace dir = s.Direction().add(t.Direction());
				Vector pq = p.minus(q);
				
				// If p-q not in V+W...
				x = dir.coordinates(pq);
			}
			
			return x;
		}
	}
	
	
	private ASpace s;
	
	/**
	 * Creates a new {@code CLSASpace}.
	 * 
	 * @param space  a source space
	 * 
	 * 
	 * @see ASpace
	 */
	public CLSASpace(ASpace space)
	{
		this.s = space;
	}

	
	@Override
	public Response intersect(ICollidable c)
	{
		if(c instanceof ASpace)
		{
			return new RSPASpace((ASpace) c);
		}
		
		return null;
	}
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			
			
			Point p = s.Origin();
			VSpace dir1 = s.Direction();
			VSpace dir2 = t.Direction();
			return dir1.equals(dir2, ulps)
				&& t.contains(p);
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			
			
			VSpace dir = s.Direction().add(t.Direction());		
			Vector pq = Geometries.subtract(s.Origin(), t.Origin());
			return dir.contains(pq); 
		}
		
		return ICollision.super.intersects(c);
	}

	@Override
	public Boolean contains(ICollidable c)
	{		
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			
			
			Vector pq = Geometries.subtract(s.Origin(), t.Origin());
			VSpace dir = s.Direction().add(t.Direction());		
			return dir.Dimension() == t.Dimension()
				&& dir.contains(pq);
		}
		
		return null;
	}
}
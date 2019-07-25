package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.geometry.CLSEllipsoid;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.bounds.Bounds;
import zeno.util.tools.Floats;

/**
 * The {IEllipsoid} interface defines the collision operations for ellipsoid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IEllipsoid extends IGeometry
{
	public static class MappedBounds implements Bounds
	{
		private AffineMap map;
		private IEllipsoid shape;
		
		public MappedBounds(IEllipsoid e, AffineMap m)
		{
			shape = e;
			map = m;
		}
		
		@Override
		public float Radius()
		{
			Matrix e = Matrices.diagonal(shape.Size().times(0.5f));
			e.setOperator(Diagonal.Type());
			
			int dim = Dimension();
			Matrix p = map.Matrix(dim);
			p = Matrices.resize(p, dim, dim);
			p = e.times(p);
			
			float rad = 0f;
			for(int i = 0; i < dim; i++)
			{
				rad = Floats.max(rad, p.Row(i).norm());
			}
			
			return rad;
		}
		
		@Override
		public Vector Center()
		{
			Point p = new Point(shape.Center(), 1f);
			return ((Point) map.map(p)).VMatrix();
		}
		
		@Override
		public Vector Size()
		{
			Matrix e = Matrices.diagonal(shape.Size().times(0.5f));
			e.setOperator(Diagonal.Type());
			e = e.times(e);
			
			int dim = Dimension();
			Matrix p = map.Matrix(dim);
			p = p.times(p.transpose());
			p = Matrices.resize(p, dim, dim);
			p = e.times(p).times(e);
			
			Vector s = new Vector(dim);
			for(int i = 0; i < dim; i++)
			{
				float val = 2 * Floats.sqrt(p.get(i, i));
				s.set(val / p.Row(i).norm(), i);
			}
			
			return s;
		}
	}
	
	
	@Override
	public default Bounds Bounds(AffineMap map)
	{
		return new MappedBounds(this, map);
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSEllipsoid(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default float Diameter()
	{
		return Floats.max(Size().Values());
	}
}
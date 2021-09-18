package zeno.util.geom.collidables.collisions.convex;

import java.util.Iterator;

import zeno.util.algebra.algorithms.lsquares.LSQSVD;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.IConvex;
import zeno.util.geom.collidables.geometry.generic.IConvex.Extremum;
import zeno.util.geom.collidables.geometry.generic.IHull;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;
import zeno.util.tools.helper.Array;

/**
 * The {@code CLSCorral} class defines a point corral for the {@code CLSConvex} class.
 * </br> This mimicks the behavior of Wolfe's algorithm for convex minimization.
 *
 * @author Waffles
 * @since 01 Sep 2021
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSCorral
{	
	class FaceIterator implements Iterator<IHull>
	{
		private int index;
		
		@Override
		public boolean hasNext()
		{
			return index < pts.length;
		}

		@Override
		public IHull next()
		{
			int cols = pts.length;
			int rows = source.Dimension();
			Matrix span = Matrices.create(rows, cols-1);
			for(int c = 0; c < cols; c++)
			{
				if(c == index++) continue;
				
				Vector v = pts[c];
				for(int r = 0; r < rows; r++)
				{
					if(c < index)
						span.set(v.get(r), r, c-0);
					else
						span.set(v.get(r), r, c-1);
				}
			}
			
			return Geometries.hull(span);
		}
	}
	
	
	private float pVal;
	private int pIndex;
	private boolean isDegenerate;
	
	private Vector[] pts;
	private Vector pt, cVal;
	private IConvex source;
	
	/**
	 * Creates a new {@code CLSCorral}.
	 * 
	 * @param src  a source object
	 * @param p    a target point
	 * 
	 * 
	 * @see IConvex
	 * @see Point
	 */
	public CLSCorral(IConvex src, Point p)
	{
		pts = new Vector[0];
		cVal = Vectors.create(0);
		pt = p.asVector();
		source = src;
	}
	
	/**
	 * Checks an affine value in the {@code CLSCorral}.
	 * 
	 * @param aVal  an affine value
	 * @return  {@code true} if the value is convex
	 * 
	 * 
	 * @see Vector
	 */
	public boolean isConvex(Vector aVal)
	{
		pIndex = -1;
		pVal = Floats.MAX_VALUE;
		for(int i = 0; i < aVal.Size(); i++)
		{
			float iVal = aVal.get(i);
			if(iVal >= 0f)
			{
				continue;
			}
			
			iVal = iVal / (iVal - cVal.get(i));
			if(iVal < pVal)
			{
				pVal = iVal;
				pIndex = i;
			}
		}

		isDegenerate = pIndex+1 == aVal.Size();
		return pIndex == -1;
	}
	
	public boolean isDegenerate()
	{
		return isDegenerate;
	}

	/**
	 * Maximizes the affine set of the {@code CLSCorral}.
	 * 
	 * @param ext  a target extremum
	 * 
	 * 
	 * @see Extremum
	 */
	public void maximize(Extremum ext)
	{
		Matrix mat = span();
		int dim = mat.Columns();
		int max = mat.Rows();
		
		VSpace space = new VSpace(mat);
		Matrix com = space.Complement();
		Vector p = pts[dim - 1];
		
		int i = 0, j = 1;
		while(dim < max)
		{
			Vector v = com.Column(i).times(j);
			Vector w = ext.along(v);
			
			if(!space.contains(w.minus(p)))
			{
				space = new VSpace(mat);
				com = space.Complement();
				
				pts = Array.add.to(pts, w);
				i = 0; j = 1;
				dim++;
			}
			
			j = -j;
			if(j == 1)
				i++;
			if(i == com.Columns())
				break;
		}
	}

	
	/**
	 * Returns the face of the {@code CLSCorral}.
	 * 
	 * @return  a face iterable
	 * 
	 * 
	 * @see Iterable
	 * @see IHull
	 */
	public Iterable<IHull> Faces()
	{
		return () -> new FaceIterator();
	}
	
	/**
	 * Evaluates a value in the {@code CLSCorral}.
	 * 
	 * @param cVal  a convex value
	 * @return  a corral point
	 * 
	 * 
	 * @see Vector
	 */
	public Vector evaluate(Vector cVal)
	{
		int cols = pts.length;
		int rows = source.Dimension();
		Matrix mat = Matrices.create(rows, cols);
		for(int c = 0; c < cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				float val = pts[c].get(r);
				mat.set(val, r, c);
			}
		}
		
		return mat.times(cVal);
	}
	
	/**
	 * Projects a value on the {@code CLSCorral}.
	 * 
	 * @param aVal  an affine value
	 * @return  a projected value
	 * 
	 * 
	 * @see Vector
	 */
	public Vector project(Vector aVal)
	{
		pts = Array.remove.from(pts, pIndex);
		cVal = cVal.times(pVal).plus(aVal.times(1f - pVal));
		cVal = Vectors.drop(cVal, pIndex);
		return value(pt);
	}
	
	/**
	 * Adds a point to the {@code CLSCorral}.
	 * 
	 * @param p  a corral point
	 * @return  an affine value
	 * 
	 * 
	 * @see Vector
	 */
	public Vector add(Vector p)
	{
//		System.out.println("Adding point.");
//		Matrices.print(p);
		
		pts = Array.add.to(pts, p);
		cVal = extend(cVal);
		return value(pt);
	}
	
	
	Vector extend(Vector v)
	{
		float vLast = 1f;
		for(float val : v.Values())
		{
			vLast -= val;
		}
		
		Vector nVal = Vectors.resize(v, v.Size() + 1);
		nVal.set(vLast, nVal.Size() - 1);
		return nVal;
	}
	
	Vector value(Vector p)
	{		
		Matrix mat = span();
		int cols = pts.length-1;
//		System.out.println("Least squares:");
//		Matrices.print(mat);
		LSQSVD lsq = new LSQSVD(mat);
		Vector v = p.minus(pts[cols]);
		Vector aVal = lsq.approx(v);
		return extend(aVal);
	}
	
	Matrix span()
	{
		int cols = pts.length-1;
		int rows = source.Dimension();
		
		Matrix mat = Matrices.create(rows, cols);
		for(int c = 0; c < cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				float val = pts[c].get(r);
				val -= pts[cols].get(r);
				mat.set(val, r, c);
			}
		}
		
		return mat;
	}
}
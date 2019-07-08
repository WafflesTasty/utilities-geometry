package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.lines.Line2D;
import zeno.util.geom.collidables.affine.lines.Line3D;
import zeno.util.geom.collidables.affine.lines.LineND;
import zeno.util.geom.collidables.affine.spaces.FullASpace;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;

/**
 * The {@code ASpaces} class defines basic {@code ASpace} operations.
 *
 * @author Zeno
 * @since Jul 15, 2018
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public final class ASpaces
{
	/**
	 * Defines a full affine space for static access.
	 * 
	 * @param dim  an affine coördinate dimension
	 * @return  a full affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace full(int dim)
	{
		return new FullASpace(dim);
	}
	
	/**
	 * Defines the trivial affine space for static access.
	 * 
	 * @param dim  an affine coördinate dimension 
	 * @return  a trivial affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace trivial(int dim)
	{
		return new TrivialASpace(dim);
	}
	
	/**
	 * Creates an affine space from a homogenous matrix.
	 * 
	 * @param m  a homogenous matrix
	 * @return  an affine space
	 * 
	 * 
	 * @see ASpace
	 * @see Matrix
	 */
	public static ASpace create(Matrix m)
	{
		int rows = m.Rows();
		int cols = m.Columns();
		
		if(cols == 0)
			return trivial(m.Rows());
		if(cols == 1)
			return new Point((Vector) m);
		if(cols == 2)
		{
			Point p1 = new Point(m.Column(0));
			Point p2 = new Point(m.Column(1));
			
			if(rows == 3)
				return new Line2D(p1, p2);
			if(rows == 4)
				return new Line3D(p1, p2);
			
			return new LineND(p1, p2);
		}
		
		return new ASpace(m);
	}
	
	
	/**
	 * Defines an affine space that expands into a new coördinate count.
	 * The amount of coördinates change and can increase dimension as well.
	 * 
	 * @param s  a space to expand
	 * @param coords  a coördinate count to use
	 * @return  a new expanded affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace expand(ASpace s, int coords)
	{
		Matrix mat = s.AMatrix();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = mat;
		if(rows > coords)
		{
			m = Matrices.resize(m, coords + 1, cols);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous coördinates.
				m.set(mat.get(rows - 1, c), coords, c);
			}
		}
		else
		{
			m = Matrices.resize(m, coords + 1, coords + cols - rows + 1);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous coördinates.
				m.set(mat.get(rows - 1, c), coords, c);
				m.set(0f, rows - 1, c);
			}
			
			float val = mat.get(rows - 1, cols - 1);
			for(int c = cols; c <= coords + cols - rows; c++)
			{
				m.set(val, coords, c);
				m.set(val, rows + c - cols - 1, c);
				for(int r = 0; r < rows - 1; r++)
				{
					// Copy the final affine point.
					m.set(mat.get(r, cols - 1), r, c);
				}
			}
		}
				
		return create(m);
	}
	
	/**
	 * Defines an affine space that occupies a new coördinate count.
	 * The amount of coördinates change but this does not increase dimension.
	 * 
	 * @param s  a space to occupy
	 * @param coords  a coördinate count to use
	 * @return  a new occupying affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace occupy(ASpace s, int coords)
	{
		Matrix mat = s.AMatrix();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = Matrices.resize(mat, coords + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			// Move the original homogenous coördinates.
			m.set(mat.get(rows - 1, c), coords, c);
			if(rows <= coords)
			{
				m.set(0f, rows - 1, c);
			}
		}
			
		return create(m);
	}	
	
	
	private ASpaces()
	{
		// NOT APPLICABLE
	}
}
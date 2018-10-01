package zeno.util.geom._deprecated.tforms.types;

import zeno.util.algebra.linear.matrix.fixed.Matrix2x2;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformation;

/**
 * The {@code ITransformation2D} interface defines an object
 * capable of transforming an {@code ITransformable2D} object.
 *
 * @since Apr 27, 2016
 * @author Zeno
 * 
 * @see ITransformation
 */
public interface ITransformation2D extends ITransformation
{
	public abstract void setBasis(Matrix2x2 basis);
	
	public abstract void setOrigin(Vector2 origin);
	
	public abstract void setScale(Vector2 scale);
		
	
	public abstract Matrix2x2 Basis();
	
	public abstract Vector2 Origin();

	public abstract Vector2 Scale();
}
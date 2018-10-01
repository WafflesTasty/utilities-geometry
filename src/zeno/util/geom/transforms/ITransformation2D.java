package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.fixed.Matrix2x2;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformation;

/**
 * The {@code ITransformation2D} interface defines an object
 * capable of transforming an {@code ITransformable2D} object.
 *
 * @author Zeno
 * @since Apr 27, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface ITransformation2D extends ITransformation
{
	public abstract void setBasis(Matrix2x2 basis);
	
	public abstract void setOrigin(Vector2 origin);
	
	public abstract void setScale(Vector2 scale);
	
	public default void setOrigin(float x, float y)
	{
		setOrigin(new Vector2(x, y));
	}
	
	public default void setScale(float w, float h)
	{
		setScale(new Vector2(w, h));
	}
	
	
	public abstract Matrix2x2 Basis();
	
	public abstract Vector2 Origin();

	public abstract Vector2 Scale();

	public default Vector2 Right()
	{
		return Basis().Column(0);
	}
	
	public default Vector2 Up()
	{
		return Basis().Column(1);
	}
}
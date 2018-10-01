package zeno.util.geom.transforms;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformation;

/**
 * The {@code ITransformation3D} interface defines an object
 * capable of transforming an {@code ITransformable3D} object.
 *
 * @author Zeno
 * @since Apr 27, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface ITransformation3D extends ITransformation
{
	public abstract void setBasis(Matrix3x3 basis);
	
	public abstract void setOrigin(Vector3 origin);
	
	public abstract void setScale(Vector3 scale);
		
	public default void setOrigin(float x, float y, float z)
	{
		setOrigin(new Vector3(x, y, z));
	}
	
	public default void setScale(float w, float h, float d)
	{
		setOrigin(new Vector3(w, h, d));
	}
	
	
	public abstract Matrix3x3 Basis();
	
	public abstract Vector3 Origin();

	public abstract Vector3 Scale();

	public default Vector3 Forward()
	{
		return Basis().Column(1);
	}
	
	public default Vector3 Right()
	{
		return Basis().Column(0);
	}
	
	public default Vector3 Up()
	{
		return Basis().Column(2);
	}
}
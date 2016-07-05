package zeno.util.geom.tforms;

import zeno.util.algebra.Floats;
import zeno.util.algebra.matrices.fixed.Matrix4x4;
import zeno.util.geom.interfaces.IProjectable3D;

/**
 * The {@code Project3D} class defines a perspective
 * projection matrix from 3D to 2D space.
 *
 * @author Zeno
 * @since Mar 24, 2016
 * @see IProjectable3D
 */
public class Perspective3D implements IProjectable3D
{	
	private float zPlane;
	private float rBase, rMult;
	private float sHeight, sDepth;
		
	/**
	 * Creates a new {@code Perspective3D}.
	 */
	public Perspective3D()
	{
		rBase = 1f;
		rMult = 1f;
		
		sHeight = 1f;
		sDepth = Floats.MAX_VALUE;
		
		zPlane = 1f;
	}

	
	@Override
	public void projectFrom(float h, float d)
	{
		sHeight = h;
		sDepth = d;
	}
	
	@Override
	public void projectBaseRatio(float ratio)
	{
		rBase = ratio;
	}
	
	@Override
	public void projectRatio(float ratio)
	{
		rMult = ratio;
	}
	
	@Override
	public void projectTo(float plane)
	{
		zPlane = plane;
	}

	
	@Override
	public float getProjectPlane()
	{
		return zPlane;
	}

	@Override
	public float getProjectRatio()
	{
		return rMult;
	}

	@Override
	public float getSourceHeight()
	{
		return sHeight;
	}

	@Override
	public float getSourceDepth()
	{
		return sDepth;
	}
	
	
	@Override
	public Matrix4x4 getInverse()
	{
		float w = rBase * rMult * sHeight;
		float h = sHeight;
		float d = sDepth;
		float z = zPlane;
		
		
		Matrix4x4 m = Matrix4x4.createIdentity();
		
		m.set(0, 0, w / (2 * z));
		m.set(1, 1, h / (2 * z));
		
		m.set(2, 2, 0);
		m.set(3, 2, 1);
		
		m.set(2, 3, - d / (2 * z * (d + z)));
		m.set(3, 3, (d + 2 * z) / (2 * z * (d + z)));
		
		return m;
	}
	
	@Override
	public Matrix4x4 getMatrix()
	{
		float w = rBase * rMult * sHeight;
		float h = sHeight;
		float d = sDepth;
		float z = zPlane;
		
		
		Matrix4x4 m = Matrix4x4.createIdentity();

		m.set(0, 0,  2 * z / w);
		m.set(1, 1,  2 * z / h);
		
		m.set(2, 2, 1 + 2 * z / d);
		m.set(3, 2, -2 * z * (z + d) / d);
		
		m.set(2, 3, 1);
		m.set(3, 3, 0);

		return m;
	}
}
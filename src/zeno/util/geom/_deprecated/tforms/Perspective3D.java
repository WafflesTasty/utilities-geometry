package zeno.util.geom._deprecated.tforms;

import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.geom._deprecated.tforms.types.IProjectable3D;
import zeno.util.tools.Floats;

/**
 * The {@code Project3D} class defines a perspective projection matrix from 3D to 2D space.
 *
 * @since Mar 24, 2016
 * @author Zeno
 * 
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
	public Matrix4x4 Inverse()
	{
		float w = rBase * rMult * sHeight;
		float h = sHeight;
		float d = sDepth;
		float z = zPlane;
		
		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(w / (2 * z), 0, 0);
		m.set(h / (2 * z), 1, 1);
		
		m.set(0, 2, 2);
		m.set(1, 2, 3);
		
		m.set(- d / (2 * z * (d + z)), 3, 2);
		m.set((d + 2 * z) / (2 * z * (d + z)), 3, 3);
		
		return m;
	}
	
	@Override
	public Matrix4x4 Matrix()
	{
		float w = rBase * rMult * sHeight;
		float h = sHeight;
		float d = sDepth;
		float z = zPlane;
		
		
		Matrix4x4 m = Matrix4x4.identity();

		m.set(2 * z / w, 0, 0);
		m.set(2 * z / h, 1, 1);
		
		m.set(1 + 2 * z / d, 2, 2);
		m.set(-2 * z * (z + d) / d, 2, 3);
		
		m.set(1, 3, 2);
		m.set(0, 3, 3);

		return m;
	}
}
package zeno.util.geom.utilities;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.ITransformation;
import zeno.util.tools.Floats;

public class Transforms
{
	public static ITransformation abs(ITransformation t)
	{
		return new ITransformation()
		{
			@Override
			public Matrix Matrix(int dim)
			{
				Matrix m1 = t.Matrix(dim);
				Matrix m2 = Matrices.create(dim + 1, dim + 1);
				for(int c = 0; c <= dim; c++)
				{
					for(int r = 0; r <= dim; r++)
					{
						m2.set(Floats.abs(m1.get(r, c)), r, c);
					}
				}
				
				return m2;
			}
			
			@Override
			public Matrix Inverse(int dim)
			{
				Matrix m1 = t.Inverse(dim);
				Matrix m2 = Matrices.create(dim + 1, dim + 1);
				for(int c = 0; c <= dim; c++)
				{
					for(int r = 0; r <= dim; r++)
					{
						m2.set(Floats.abs(m1.get(r, c)), r, c);
					}
				}
				
				return m2;
			}
		};
	}
}
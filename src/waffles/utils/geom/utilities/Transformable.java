package waffles.utils.geom.utilities;

import waffles.utils.tools.patterns.properties.Immutable.Mutable;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Transformable} object defines a {@code Mutable} transformation.
 *
 * @author Waffles
 * @since 08 Oct 2025
 * @version 1.1
 */
@FunctionalInterface
public interface Transformable
{
	/**
	 * Defines a {@code Transformable} error value.
	 */
	public static final float ERROR = Floats.pow(2, -8);
	
	/**
	 * Returns the transform of the {@code Transformable}.
	 * 
	 * @return  transform data
	 * 
	 * 
	 * @see Mutable
	 */
	public abstract Mutable Transform();
}
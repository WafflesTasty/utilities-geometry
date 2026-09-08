package waffles.utils.geom.utilities;

import waffles.utils.tools.patterns.properties.Immutable;
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
	public static final double ERROR = Floats.pow(2, -16);
	
	/**
	 * Returns an {@code Immutable} transformation.
	 * 
	 * @return  a transformation
	 * 
	 * 
	 * @see Immutable
	 */
	public abstract Immutable Transform();
}
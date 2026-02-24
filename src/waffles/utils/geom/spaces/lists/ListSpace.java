package waffles.utils.geom.spaces.lists;

import waffles.utils.geom.spaces.Space;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code ListSpace} defines a {@code JavaList} as a {@code Space}.
 *
 * @author Waffles
 * @since 24 Feb 2026
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see JavaList
 * @see Space
 */
public abstract class ListSpace<O> extends JavaList<O> implements Space<O>
{
	@Override
	public Query<O> Query()
	{
		return () -> iterator();
	}
}
/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2012 Stephan Preibisch, Stephan Saalfeld, Tobias
 * Pietzsch, Albert Cardona, Barry DeZonia, Curtis Rueden, Lee Kamentsky, Larry
 * Lindsey, Johannes Schindelin, Christian Dietz, Grant Harris, Jean-Yves
 * Tinevez, Steffen Jaensch, Mark Longair, Nick Perry, and Jan Funke.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package net.imglib2;

/**
 * Implementation of the {@link Interval} interface.
 *
 *
 * @author Tobias Pietzsch
 * @author Stephan Preibisch
 */
public abstract class AbstractInterval extends AbstractEuclideanSpace implements Interval
{
	final protected long[] min;
	final protected long[] max;

	/**
	 * Creates a {@link AbstractInterval} from another {@link Interval}
	 *
	 * @param interval - another {@link Interval}
	 */
	public AbstractInterval ( final Interval interval )
	{
		super( interval.numDimensions() );
		this.min = new long[ n ];
		this.max = new long[ n ];

		interval.min( min );
		interval.max( max );
	}

	/**
	 * Creates an Interval with the boundaries [min, max] (both including)
	 *
	 * @param min - the position of the first elements in each dimension
	 * @param max - the position of the last elements in each dimension
	 */
	public AbstractInterval ( final long[] min, final long[] max )
	{
		super( min.length );
		assert min.length == max.length;

		this.min = min.clone();
		this.max = max.clone();
	}

	/**
	 * Creates an Interval with the boundaries [0, dimensions-1]
	 *
	 * @param dimensions - the size of the interval
	 */
	public AbstractInterval ( final long[] dimensions )
	{
		super( dimensions.length );
		this.min = new long[ n ];
		this.max = new long[ n ];

		for ( int d = 0; d < n; ++d )
			this.max[ d ] = dimensions[ d ] - 1;
	}

	@Override
	public double realMin( final int d )
	{
		assert d >= 0;
		assert d < n;

		return min[ d ];
	}

	@Override
	public void realMin( final double[] minimum )
	{
		assert minimum.length == n;

		for ( int d = 0; d < n; ++d )
			minimum[ d ] = this.min[ d ];
	}

	@Override
	public void realMin( final RealPositionable minimum )
	{
		assert minimum.numDimensions() == n;

		minimum.setPosition( this.min );
	}

	@Override
	public double realMax( final int d )
	{
		assert d >= 0;
		assert d < n;

		return max[ d ];
	}

	@Override
	public void realMax( final double[] maximum )
	{
		assert maximum.length == n;

		for ( int d = 0; d < n; ++d )
			maximum[ d ] = this.max[ d ];
	}

	@Override
	public void realMax( final RealPositionable m )
	{
		assert m.numDimensions() == n;

		m.setPosition( this.max );
	}

	@Override
	public long min( final int d )
	{
		assert d >= 0;
		assert d < n;

		return min[ d ];
	}

	@Override
	public void min( final long[] minimum )
	{
		assert minimum.length == n;

		for ( int d = 0; d < n; ++d )
			minimum[ d ] = this.min[ d ];
	}

	@Override
	public void min( final Positionable m )
	{
		assert m.numDimensions() == n;

		m.setPosition( this.min );
	}

	@Override
	public long max( final int d )
	{
		assert d >= 0;
		assert d < n;

		return max[ d ];
	}

	@Override
	public void max( final long[] maximum )
	{
		assert maximum.length == n;

		for ( int d = 0; d < n; ++d )
			maximum[ d ] = this.max[ d ];
	}

	@Override
	public void max( final Positionable m )
	{
		assert m.numDimensions() == n;

		m.setPosition( this.max );
	}

	@Override
	public void dimensions( final long[] dimensions )
	{
		assert dimensions.length == n;

		for ( int d = 0; d < n; ++d )
			dimensions[ d ] = max[ d ] - min[ d ] + 1;
	}

	@Override
	public long dimension( final int d )
	{
		assert d >= 0;
		assert d < n;

		return max[ d ] - min[ d ] + 1;
	}
}

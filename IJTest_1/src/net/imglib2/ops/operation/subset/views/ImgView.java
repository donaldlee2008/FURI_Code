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

package net.imglib2.ops.operation.subset.views;

import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.IterableRealInterval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.type.Type;
import net.imglib2.view.IterableRandomAccessibleInterval;
import net.imglib2.view.Views;

/**
 * Helper class to create a {@link ImgView} on an
 * {@link RandomAccessibleInterval} which behaves exactly as an {@link Img}.
 * 
 * @author Tobias Pietzsch
 * @author Christian Dietz (University of Konstanz)
 */
public class ImgView< T extends Type< T > > extends IterableRandomAccessibleInterval< T > implements Img< T >
{

	// factory
	private final ImgFactory< T > factory;

	// ImgView ii
	private final IterableInterval< T > ii;

	/**
	 * View on {@link Img} which is defined by a given Interval, but still is an
	 * {@link Img}.
	 * 
	 * @param RandomAccessibleInterval
	 *            Source interval for the view
	 * @param ImgFactory
	 *            <T> Factory to create img
	 */
	public ImgView( final RandomAccessibleInterval< T > in, ImgFactory< T > fac )
	{
		super( in );
		factory = fac;
		ii = Views.iterable( in );
	}

	@Override
	public ImgFactory< T > factory()
	{
		return factory;
	}

	@Override
	public Img< T > copy()
	{
		final Img< T > copy = factory.create( this, randomAccess().get().createVariable() );

		Cursor< T > srcCursor = localizingCursor();
		RandomAccess< T > resAccess = copy.randomAccess();

		while ( srcCursor.hasNext() )
		{
			srcCursor.fwd();
			resAccess.setPosition( srcCursor );
			resAccess.get().set( srcCursor.get() );
		}

		return copy;
	}

	@Override
	public Cursor< T > cursor()
	{
		return ii.cursor();
	}

	@Override
	public Cursor< T > localizingCursor()
	{
		return ii.localizingCursor();
	}

	@Override
	public boolean equalIterationOrder( IterableRealInterval< ? > f )
	{
		return iterationOrder().equals( f.iterationOrder() );
	}
}

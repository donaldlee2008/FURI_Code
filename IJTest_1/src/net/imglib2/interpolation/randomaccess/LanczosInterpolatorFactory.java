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

package net.imglib2.interpolation.randomaccess;

import net.imglib2.RandomAccessible;
import net.imglib2.RealInterval;
import net.imglib2.interpolation.InterpolatorFactory;
import net.imglib2.type.numeric.RealType;

/**
 * TODO
 *
 * @author ImgLib2 developers
 */
public class LanczosInterpolatorFactory<T extends RealType<T>> implements InterpolatorFactory< T, RandomAccessible< T > >
{
	int alpha;
	boolean clipping;
	double min, max;
	
	/**
	 * Creates a new {@link LanczosInterpolatorFactory} using the Lanczos (sinc) interpolation in a certain window
	 * 
	 * @param alpha - the rectangular radius of the window for perfoming the lanczos interpolation
	 * @param clipping - the lanczos-interpolation can create values that are bigger or smaller than the original values,
	 *        so they can be clipped to the range of the {@link Type} if wanted
	 */
	public LanczosInterpolatorFactory( final int alpha, final boolean clipping )
	{
		this.alpha = alpha;
		this.clipping = clipping;
		this.min = this.max = 0;
	}

	/**
	 * Creates a new {@link LanczosInterpolatorFactory} using the Lanczos (sinc) interpolation in a certain window
	 * 
	 * @param alpha - the rectangular radius of the window for perfoming the lanczos interpolation
	 * @param min - the lanczos-interpolation can create values that are bigger or smaller than the original values,
	 *        so they can be clipped to the range of the if wanted
	 * @param max - the lanczos-interpolation can create values that are bigger or smaller than the original values,
	 *        so they can be clipped to the range of the if wanted
	 */
	public LanczosInterpolatorFactory( final int alpha, final double min, final double max )
	{
		this.alpha = alpha;
		this.clipping = true;
		this.min = min;
		this.max = max;
	}

	/**
	 * Creates a new {@link LanczosInterpolatorFactory} using the Lanczos (sinc) interpolation in a certain window
	 * 
	 * @param min - the lanczos-interpolation can create values that are bigger or smaller than the original values,
	 *        so they can be clipped to the range of the if wanted
	 * @param max - the lanczos-interpolation can create values that are bigger or smaller than the original values,
	 *        so they can be clipped to the range of the if wanted
	 */
	public LanczosInterpolatorFactory( final double min, final double max )
	{
		this.alpha = 3;
		this.clipping = true;
		this.min = min;
		this.max = max;
	}

	/**
	 * Creates a new {@link LanczosInterpolatorFactory} with standard parameters (do clipping, alpha=3)
	 */
	public LanczosInterpolatorFactory()
	{
		this( 3, true );
	}
	
	@Override
	public LanczosInterpolator< T > create( final RandomAccessible< T > randomAccessible )
	{
		return new LanczosInterpolator< T >( randomAccessible, alpha, clipping, min, max );
	}

	/**
	 * For now, ignore the {@link RealInterval} and return
	 * {@link #create(RandomAccessible)}.
	 */
	@Override
	public LanczosInterpolator< T > create( final RandomAccessible< T > randomAccessible, final RealInterval interval )
	{
		return create( randomAccessible );
	}

	/**
	 * Set the rectangular radius of the window for perfoming the lanczos interpolation
	 * @param alpha - radius
	 */
	public void setAlpha( final int alpha ) { this.alpha = alpha; }
	
	/**
	 * The lanczos-interpolation can create values that are bigger or smaller than the original values,
	 * so they can be clipped to the range of the {@link RealType} if wanted
	 * 
	 * @param clipping - perform clipping (true)
	 */
	public void setClipping( final boolean clipping ) { this.clipping = clipping; }
	
	/**
	 * @return - rectangular radius of the window for perfoming the lanczos interpolation 
	 */
	public int getAlpha() { return alpha; }
	
	/**
	 * @return - if clipping to the {@link RealType} range will be performed 
	 */
	public boolean getClipping() { return clipping; }
}

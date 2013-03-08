/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2009 - 2012 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, and Max Planck
 * Institute of Molecular Cell Biology and Genetics.
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

package imagej.display.event.input;

import imagej.display.Display;
import imagej.input.Accelerator;
import imagej.input.InputModifiers;
import imagej.input.KeyCode;

/**
 * An event indicating keyboard activity in a display.
 * <p>
 * It is named {@code KyEvent} rather than {@code KeyEvent} to avoid name
 * clashes with the {@link java.awt.event.KeyEvent} hierarchy.
 * </p>
 * 
 * @author Curtis Rueden
 */
public abstract class KyEvent extends InputEvent {

	private final char character;
	private final KeyCode code;

	public KyEvent(final Display<?> display, final InputModifiers modifiers,
		final int x, final int y, final char character, final KeyCode code)
	{
		super(display, modifiers, x, y);
		this.character = character;
		this.code = code;
	}

	public char getCharacter() {
		return character;
	}

	public KeyCode getCode() {
		return code;
	}

	/**
	 * Converts the key event into a corresponding accelerator.
	 * 
	 * @return an accelerator matching the key code and modifiers of the event.
	 */
	public Accelerator getAccelerator() {
		return new Accelerator(getCode(), getModifiers());
	}

	// -- Object methods --

	@Override
	public String toString() {
		return super.toString() + "\n\tcharacter = '" + character +
			"'\n\tcode = " + code + "\n\taccelerator = " + getAccelerator();
	}

}

/*
 * Copyright 2013 Klaus Reimer <k@ailis.de>
 * See LICENSE.md for licensing information.
 * 
 * Based on libusb <http://www.libusb.org/>:  
 * 
 * Copyright 2001 Johannes Erdfelt <johannes@erdfelt.com>
 * Copyright 2007-2009 Daniel Drake <dsd@gentoo.org>
 * Copyright 2010-2012 Peter Stuge <peter@stuge.se>
 * Copyright 2008-2011 Nathan Hjelm <hjelmn@users.sourceforge.net>
 * Copyright 2009-2012 Pete Batard <pete@akeo.ie>
 * Copyright 2009-2012 Ludovic Rousseau <ludovic.rousseau@gmail.com>
 * Copyright 2010-2012 Michael Plante <michael.plante@gmail.com>
 * Copyright 2011-2012 Hans de Goede <hdegoede@redhat.com>
 * Copyright 2012 Martin Pieuchot <mpi@openbsd.org>
 * Copyright 2012-2013 Toby Gray <toby.gray@realvnc.com>
 */

package de.ailis.usb4java.libusb;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A collection of alternate settings for a particular USB interface.
 * 
 * @author Klaus Reimer (k@ailis.de)
 */
public final class Interface
{
    /** The native pointer to the descriptor structure. */
    private long interfacePointer;

    /**
     * Package-private constructor to prevent manual instantiation. Interfaces 
     * are always created by JNI.
     */
    Interface()
    {
        // Empty
    }
    
    /**
     * Returns the native pointer.
     * 
     * @return The native pointer.
     */
    public long getPointer()
    {
        return this.interfacePointer;
    }
        
    /**
     * Returns the array with interface descriptors. The length of this array is
     * determined by the {@link #numAltsetting()} field.
     * 
     * @return The array with interface descriptors.
     */
    public native InterfaceDescriptor[] altsetting();

    /**
     * Returns the number of alternate settings that belong to this interface.
     * 
     * @return The number of alternate settings.
     */
    public native int numAltsetting();

    /**
     * Returns a dump of this interface.
     *
     * @return The interface dump.
     */
    public String dump()
    {
        return dump(null);
    }

    /**
     * Returns a dump of this descriptor.
     *
     * @param handle
     *            The USB device handle for resolving string descriptors. If
     *            null then no strings are resolved.
     * @return The descriptor dump.
     */
    public String dump(final DeviceHandle handle)
    {
        final StringBuilder builder = new StringBuilder();
        for (final InterfaceDescriptor descriptor : altsetting())
        {
            builder.append(descriptor.dump(handle));
        }
        return builder.toString();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.interfacePointer).toHashCode();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Interface other = (Interface) obj;
        return this.interfacePointer == other.interfacePointer;
    }
    
    @Override
    public String toString()
    {
        return dump();
    }
}

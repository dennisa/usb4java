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

import java.util.Iterator;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * List of devices as returned by
 * {@link LibUsb#getDeviceList(Context, DeviceList)}.
 *
 * @author Klaus Reimer (k@ailis.de)
 */
public final class DeviceList implements Iterable<Device>
{
    /** The native pointer to the devices array. */
    private long deviceListPointer;

    /** The number of devices in the list. */
    private int size;

    /**
     * Constructs a new device list. Must be passed to
     * {@link LibUsb#getDeviceList(Context, DeviceList)} before using it.
     */
    public DeviceList()
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
        return this.deviceListPointer;
    }
        
    /**
     * Returns the number of devices in the list.
     * 
     * @return The number of devices in the list.
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Returns the device with the specified index.
     * 
     * @param index
     *            The device index.
     * @return The device or null when index is out of bounds.
     */
    public native Device get(final int index);

    @Override
    public Iterator<Device> iterator()
    {
        return new DeviceListIterator(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.deviceListPointer)
            .toHashCode();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final DeviceList other = (DeviceList) obj;
        return this.deviceListPointer == other.deviceListPointer;
    }
}

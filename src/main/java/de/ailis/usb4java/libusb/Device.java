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
 * Structure representing a USB device detected on the system.
 * 
 * This is an opaque type for which you are only ever provided with a pointer,
 * usually originating from {@link LibUsb#getDeviceList(Context, DeviceList)}.
 * 
 * Certain operations can be performed on a device, but in order to do any I/O
 * you will have to first obtain a device handle using
 * {@link LibUsb#open(Device, DeviceHandle)}.
 * 
 * Devices are reference counted with {@link LibUsb#refDevice(Device)} and
 * {@link LibUsb#unrefDevice(Device)}, and are freed when the reference count
 * reaches 0. New devices presented by
 * {@link LibUsb#getDeviceList(Context, DeviceList)} have a reference count of
 * 1, and {@link LibUsb#freeDeviceList(DeviceList, boolean)} can optionally
 * decrease the reference count on all devices in the list.
 * {@link LibUsb#open(Device, DeviceHandle)} adds another reference which is
 * later destroyed by {@link LibUsb#close(DeviceHandle)}.
 * 
 * @author Klaus Reimer (k@ailis.de)
 */
public final class Device
{
    /** The native pointer to the device structure. */
    private long devicePointer;

    /**
     * Package-private constructor to prevent manual instantiation. Devices are
     * always created by JNI.
     */
    Device()
    {
        // Empty
    }
    
    /**
     * Returns the native pointer to the device structure.
     * 
     * @return The native pointer to the device structure.
     */
    public long getPointer()
    {
        return this.devicePointer;
    }
        
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.devicePointer).toHashCode();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Device other = (Device) obj;
        return this.devicePointer == other.devicePointer;
    }

    @Override
    public String toString()
    {
        return String.format("libusb device 0x%x", this.devicePointer);
    }
}

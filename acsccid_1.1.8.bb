SUMMARY = "PC/SC driver for Linux/Mac OS X"
HOMEPAGE = "http://acsccid.sourceforge.net/"
DESCRIPTION = "acsccid is a PC/SC driver for Linux/Mac OS X and it supports ACS CCID smart card readers. This library provides a PC/SC IFD handler implementation and communicates with the readers through the PC/SC Lite resource manager (pcscd)."
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS += "pcsc-lite libusb flex perl pkgconfig"

SRC_URI  = "https://downloads.sourceforge.net/acsccid/${BP}.tar.bz2"
SRC_URI += "file://blacklist-libnfc.conf"
SRC_URI[sha256sum] = "f86fd846bc88594a569ea27040cb441d933b7eca8d51d2a90bacf161e7740051"

S = "${WORKDIR}/${BP}"

do_install:append() {
    install -d ${D}/${sysconfdir}
    install -d ${D}/${sysconfdir}/modprobe.d
    install -m 0755 ${WORKDIR}/blacklist-libnfc.conf ${D}/${sysconfdir}/modprobe.d
}

inherit autotools pkgconfig

FILES:${PN} += "${libdir}/pcsc/drivers/ifd-acsccid.bundle/Contents/*"
FILES:${PN} += "${sysconfdir}/modprobe.d/blacklist-libnfc.conf"

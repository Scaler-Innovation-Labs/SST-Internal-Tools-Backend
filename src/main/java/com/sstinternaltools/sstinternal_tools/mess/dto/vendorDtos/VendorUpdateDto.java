package com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos;

public class VendorUpdateDto {
    private String vendorName;

    public VendorUpdateDto(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

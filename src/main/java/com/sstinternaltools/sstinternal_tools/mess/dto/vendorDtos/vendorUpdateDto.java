package com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos;

public class vendorUpdateDto {
    private String vendorName;

    public vendorUpdateDto(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

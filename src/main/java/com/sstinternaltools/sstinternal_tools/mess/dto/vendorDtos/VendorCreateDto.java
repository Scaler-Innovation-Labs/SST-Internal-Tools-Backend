package com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos;

import jakarta.validation.constraints.NotBlank;

public class VendorCreateDto {
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;

    public VendorCreateDto(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

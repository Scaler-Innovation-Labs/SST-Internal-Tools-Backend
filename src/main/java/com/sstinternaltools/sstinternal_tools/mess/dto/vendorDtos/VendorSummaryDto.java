package com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos;

import jakarta.validation.constraints.NotBlank;

public class VendorSummaryDto {
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;

    public VendorSummaryDto(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

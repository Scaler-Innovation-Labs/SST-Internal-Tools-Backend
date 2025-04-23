package com.sstinternaltools.sstinternal_tools.mess.dto.vendorDtos;

import jakarta.validation.constraints.NotBlank;

public class VendorResponseDto {
    @NotBlank(message = "Vendor Id cannot be blank")
    private Long vendorId;
    @NotBlank(message = "Vendor name cannot be blank")
    private String vendorName;

    public VendorResponseDto(Long vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}

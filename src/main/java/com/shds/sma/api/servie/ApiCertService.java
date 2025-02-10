package com.shds.sma.api.servie;

import com.shds.sma.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.api.dto.cert.ApiCertSaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiCertService {
    Page<ApiCertResponseDto> getAllCerts(Pageable pageable);

    ApiCertResponseDto getCert(Long certId);

    ApiCertResponseDto createCert(ApiCertSaveRequestDto apiCertSaveRequestDto);

    ApiCertResponseDto updateCert(ApiCertModRequestDto apiCertModRequestDto);

    void deleteCert(Long certId);
}

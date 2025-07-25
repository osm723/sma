package com.shds.sma.external.api.controller;

import com.shds.sma.external.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.external.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.external.api.dto.cert.ApiCertSaveRequestDto;
import com.shds.sma.external.api.service.ApiCertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiCertController {
    
    private final ApiCertService apiCertService;

    @GetMapping("/certs")
    public ResponseEntity<Page<ApiCertResponseDto>> getAllCerts(Pageable pageable) {
        Page<ApiCertResponseDto> certs = apiCertService.getAllCerts(pageable);
        return ResponseEntity.ok(certs);
    }

    @GetMapping("/cert/{certId}")
    public ResponseEntity<ApiCertResponseDto> getCert(@PathVariable Long certId) {
        ApiCertResponseDto cert = apiCertService.getCert(certId);
        return ResponseEntity.ok(cert);
    }

    @PostMapping("/cert")
    public ResponseEntity<ApiCertResponseDto> createCert(@RequestBody ApiCertSaveRequestDto apiCertSaveRequestDto) {
        ApiCertResponseDto createdCert = apiCertService.createCert(apiCertSaveRequestDto);
        return ResponseEntity.ok(createdCert);
    }

    @PutMapping("/cert")
    public ResponseEntity<ApiCertResponseDto> updateCert(@RequestBody ApiCertModRequestDto apiCertModRequestDto) {
        ApiCertResponseDto updatedCert = apiCertService.updateCert(apiCertModRequestDto);
        return ResponseEntity.ok(updatedCert);
    }

    @DeleteMapping("/cert/{certId}")
    public ResponseEntity<Void> deleteCert(@PathVariable Long certId) {
        apiCertService.deleteCert(certId);
        return ResponseEntity.ok().build();
    }
}

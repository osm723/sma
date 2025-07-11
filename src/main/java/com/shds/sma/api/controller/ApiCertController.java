package com.shds.sma.api.controller;

import com.shds.sma.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.api.dto.cert.ApiCertSaveRequestDto;
import com.shds.sma.api.service.ApiCertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(certs, HttpStatus.OK);
    }

    @GetMapping("/cert/{certId}")
    public ResponseEntity<ApiCertResponseDto> getCert(@PathVariable Long certId) {
        ApiCertResponseDto cert = apiCertService.getCert(certId);
        return new ResponseEntity<>(cert, HttpStatus.OK);
    }

    @PostMapping("/cert")
    public ResponseEntity<ApiCertResponseDto> createCert(@RequestBody ApiCertSaveRequestDto apiCertSaveRequestDto) {
        ApiCertResponseDto createdCert = apiCertService.createCert(apiCertSaveRequestDto);
        return new ResponseEntity<>(createdCert, HttpStatus.OK);
    }

    @PutMapping("/cert")
    public ResponseEntity<ApiCertResponseDto> updateCert(@RequestBody ApiCertModRequestDto apiCertModRequestDto) {
        ApiCertResponseDto updatedCert = apiCertService.updateCert(apiCertModRequestDto);
        return new ResponseEntity<>(updatedCert, HttpStatus.OK);
    }

    @DeleteMapping("/cert/{certId}")
    public ResponseEntity<Void> deleteCert(@PathVariable Long certId) {
        apiCertService.deleteCert(certId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

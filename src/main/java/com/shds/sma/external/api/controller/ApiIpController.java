package com.shds.sma.external.api.controller;

import com.shds.sma.external.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.external.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.external.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.external.api.service.ApiIpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiIpController {

    private final ApiIpService apiIpService;

    @GetMapping("/ips")
    public ResponseEntity<Page<ApiIpResponseDto>> getAllIps(Pageable pageable) {
        Page<ApiIpResponseDto> ips = apiIpService.getAllIps(pageable);
        return ResponseEntity.ok(ips);
    }

    @GetMapping("/ip/{ipId}")
    public ResponseEntity<ApiIpResponseDto> getIp(@PathVariable Long ipId) {
        ApiIpResponseDto ip = apiIpService.getIp(ipId);
        return ResponseEntity.ok(ip);
    }

    @PostMapping("/ip")
    public  ResponseEntity<ApiIpResponseDto> createIp(@RequestBody ApiIpSaveRequestDto apiIpSaveRequestDto) {
        ApiIpResponseDto createdIp = apiIpService.createIp(apiIpSaveRequestDto);
        return ResponseEntity.ok(createdIp);
    }

    @PutMapping("/ip")
    public ResponseEntity<ApiIpResponseDto> updateIp(@RequestBody ApiIpModRequestDto apiIpModRequestDto) {
        ApiIpResponseDto updatedIp = apiIpService.updateIp(apiIpModRequestDto);
        return ResponseEntity.ok(updatedIp);
    }

    @DeleteMapping("/ip/{ipId}")
    public ResponseEntity<Void> deleteIp(@PathVariable Long ipId) {
        apiIpService.deleteIp(ipId);
        return ResponseEntity.ok().build();
    }
}

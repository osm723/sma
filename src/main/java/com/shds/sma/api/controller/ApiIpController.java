package com.shds.sma.api.controller;

import com.shds.sma.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.api.service.ApiIpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(ips, HttpStatus.OK);
    }

    @GetMapping("/ip/{ipId}")
    public ResponseEntity<ApiIpResponseDto> getIp(@PathVariable Long ipId) {
        ApiIpResponseDto ip = apiIpService.getIp(ipId);
        return new ResponseEntity<>(ip, HttpStatus.OK);
    }

    @PostMapping("/ip")
    public  ResponseEntity<ApiIpResponseDto> createIp(@RequestBody ApiIpSaveRequestDto apiIpSaveRequestDto) {
        ApiIpResponseDto createdIp = apiIpService.createIp(apiIpSaveRequestDto);
        return new ResponseEntity<>(createdIp, HttpStatus.OK);
    }

    @PutMapping("/ip")
    public ResponseEntity<ApiIpResponseDto> updateIp(@RequestBody ApiIpModRequestDto apiIpModRequestDto) {
        ApiIpResponseDto updatedIp = apiIpService.updateIp(apiIpModRequestDto);
        return new ResponseEntity<>(updatedIp, HttpStatus.OK);
    }

    @DeleteMapping("/ip/{ipId}")
    public ResponseEntity<Void> deleteIp(@PathVariable Long ipId) {
        apiIpService.deleteIp(ipId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

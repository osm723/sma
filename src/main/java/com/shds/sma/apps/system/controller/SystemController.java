package com.shds.sma.apps.system.controller;

import com.shds.sma.apps.system.dto.*;
import com.shds.sma.apps.system.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    @GetMapping("/manage")
    public String systemMain() {
        return "/system/systemManage";
    }

    @GetMapping("/manage/ip")
    public String systemIP(SystemIpRequestDto systemIpRequestDto, Model model, Pageable pageable) {
        Page<SystemIpResponseDto> systemIps =  systemService.findSystemIpByCond(systemIpRequestDto, pageable);
        model.addAttribute("systemIps", systemIps);
        setSystemsModel(model, systemIpRequestDto);
        return "/system/systemIp";
    }

    @GetMapping("/manage/cert")
    public String systemCert(SystemCertRequestDto systemCertRequestDto, Model model, Pageable pageable) {
        Page<SystemCertResponseDto> systemCerts =  systemService.findSystemCertByCond(systemCertRequestDto, pageable);
        model.addAttribute("systemCerts", systemCerts);
        setSystemsModel(model, systemCertRequestDto);
        return "/system/systemCert";
    }

    @GetMapping("/manage/manager")
    public String systemManager(SystemManagerRequestDto systemManagerRequestDto, Model model, Pageable pageable) {
        Page<SystemManagerResponseDto> systemManagers = systemService.findSystemMemberByCond(systemManagerRequestDto, pageable);
        model.addAttribute("systemManagers", systemManagers);
        setSystemsModel(model, systemManagerRequestDto);

        return "/system/systemManager";
    }

    private <T> void setSystemsModel(Model model, T dto) {
        List<SystemResponseDto> systems = systemService.findSystemAll();
        model.addAttribute("systems", systems);
        model.addAttribute("cond", dto);
    }
}

package com.shds.sma.system.contoller;

import com.shds.sma.admin.dto.system.SystemResponseDto;
import com.shds.sma.system.dto.*;
import com.shds.sma.system.service.SystemService;
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

    @GetMapping("/main")
    public String systemMain() {
        return "/system/systemMain";
    }

    @GetMapping("/ip")
    public String systemIP(SystemIpRequestDto systemIpRequestDto, Model model, Pageable pageable) {
        Page<SystemIpResponseDto> systemIps =  systemService.findSystemIpByCond(systemIpRequestDto, pageable);
        model.addAttribute("systemIps", systemIps);
        setSystemsModel(model, systemIpRequestDto);

        return "/system/systemIp";
    }

    @GetMapping("/cert")
    public String systemCert(SystemCertRequestDto systemCertRequestDto, Model model, Pageable pageable) {
        Page<SystemCertResponseDto> systemCerts =  systemService.findSystemCertByCond(systemCertRequestDto, pageable);
        model.addAttribute("systemCerts", systemCerts);
        setSystemsModel(model, systemCertRequestDto);

        return "/system/systemCert";
    }

    @GetMapping("/manager")
    public String systemManager(SystemManagerRequestDto systemManagerRequestDto, Model model, Pageable pageable) {
        Page<SystemManagerResponseDto> systemManagers = systemService.findMemberByCond(systemManagerRequestDto, pageable);
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

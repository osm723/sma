package com.shds.sma.apps.cert.controller;

import com.shds.sma.common.helper.ModelHelper;
import com.shds.sma.apps.cert.service.CertService;
import com.shds.sma.apps.cert.dto.CertRequestDto;
import com.shds.sma.apps.cert.dto.CertResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cert")
@RequiredArgsConstructor
public class CertController {

    private final CertService certService;

    private final ModelHelper modelHelper;

    /**
     * 인증서관리 화면
     * certManage
     * @return String
     */
    @GetMapping("/manage")
    public String certManage(CertRequestDto certRequestDto, Pageable pageable, Model model) {
        Page<CertResponseDto> certs = certService.findCertByCond(certRequestDto, pageable);
        model.addAttribute("certs", certs);
        model.addAttribute("cond", certRequestDto);
        return "/cert/certManage";
    }

    /**
     * 인증서관리 상세화면
     * certDetail
     * @return String
     */
    @GetMapping("/manage/detail")
    public String certDetail(Long certId, Model model) {
        CertResponseDto cert = certService.findCertById(certId);
        if (cert.getApproval() != null) {
            cert.setDrafterId(cert.getApproval().getDrafterId());
            cert.setApproverId(cert.getApproval().getApproverId());
        }
        modelHelper.setCertModel(model, cert);
        return "/cert/certManageDetail";
    }

}

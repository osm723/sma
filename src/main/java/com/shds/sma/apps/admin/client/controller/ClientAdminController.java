package com.shds.sma.apps.admin.client.controller;

import com.shds.sma.apps.admin.client.dto.ClientModRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientRequestDto;
import com.shds.sma.apps.admin.client.dto.ClientResponseDto;
import com.shds.sma.apps.admin.client.dto.ClientSaveRequestDto;
import com.shds.sma.apps.admin.client.service.ClientAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.shds.sma.common.constants.Constants.CLIENT_UNUSE_SUCCESS;
import static com.shds.sma.common.constants.Constants.CLIENT_USE_SUCCESS;
import static com.shds.sma.common.constants.Constants.UrlPath.ADMIN_CLIENT_URL;

@Controller
@RequestMapping(ADMIN_CLIENT_URL)
@RequiredArgsConstructor
public class ClientAdminController {

    private final ClientAdminService clientAdminService;

    /**
     * 그룹사 조회화면 (조건)
     * client
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping
    public String client(ClientRequestDto clientRequestDto, Pageable pageable, Model model) {
        Page<ClientResponseDto> clients = clientAdminService.findClientByCond(clientRequestDto, pageable);
        model.addAttribute("clients", clients);
        model.addAttribute("cond", clientRequestDto);
        return "/admin/client/clientMain";
    }

    /**
     * 그룹사 상세화면
     * clientDetail
     * @param clientId
     * @param model
     * @return String
     */
    @GetMapping("/detail")
    public String clientDetail(Long clientId, Model model) {
        ClientResponseDto client = clientAdminService.findClientById(clientId);
        model.addAttribute("client", client);
        return "/admin/client/clientDetail";
    }

    /**
     * 그룹사 등록화면 폼
     * clientSaveForm
     * @return String
     */
    @GetMapping("/save")
    public String clientSaveForm() {
        return "/admin/client/clientSaveForm";
    }

    /**
     * 그룹사 저장
     * clientSave
     * @param clientSaveRequestDto
     * @return String
     */
    @PostMapping("/save")
    public String clientSave(ClientSaveRequestDto clientSaveRequestDto) {
        clientAdminService.saveClient(clientSaveRequestDto);
        return "redirect:/admin/client";
    }

    /**
     * 그룹사 수정
     * clientModified
     * @param clientModRequestDto
     * @return String
     */
    @PostMapping("/modified")
    public String clientModified(ClientModRequestDto clientModRequestDto) {
        clientAdminService.modifiedClient(clientModRequestDto);
        return "redirect:/admin/client/detail?clientId="+clientModRequestDto.getClientId();
    }

    /**
     * 그룹사 삭제
     * clientRemove
     * @param clientId
     * @return ResponseEntity<String>
     */
    @PostMapping("/remove")
    public ResponseEntity<String> clientRemove(@RequestParam Long clientId) {
        clientAdminService.removeClient(clientId);
        return ResponseEntity.ok(CLIENT_UNUSE_SUCCESS);
    }

    /**
     * 그룹사 사용
     * clientUse
     * @param clientId
     * @return ResponseEntity<String>
     */
    @PostMapping("/use")
    public ResponseEntity<String> clientUse(@RequestParam Long clientId) {
        clientAdminService.useClient(clientId);
        return ResponseEntity.ok(CLIENT_USE_SUCCESS);
    }
}

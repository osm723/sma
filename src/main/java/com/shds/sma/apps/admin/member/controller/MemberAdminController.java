package com.shds.sma.apps.admin.member.controller;

import com.shds.sma.apps.admin.member.dto.MemberModRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberRequestDto;
import com.shds.sma.apps.admin.member.dto.MemberResponseDto;
import com.shds.sma.apps.admin.member.dto.MemberSaveRequestDto;
import com.shds.sma.apps.admin.member.entity.type.EmpStatus;
import com.shds.sma.apps.admin.member.service.MemberAdminService;
import com.shds.sma.common.helper.ModelHelper;
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

import static com.shds.sma.common.constants.Constants.MEMBER_UPDATE_SUCCESS;
import static com.shds.sma.common.constants.Constants.UrlPath.ADMIN_MEMBER_URL;

@Controller
@RequestMapping(ADMIN_MEMBER_URL)
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    private final ModelHelper modelHelper;

    /**
     * 직원 조회화면 (조건)
     * member
     * @param
     * @param pageable
     * @param model
     * @return String
     */
    @GetMapping
    public String member(MemberRequestDto memberRequestDto, Pageable pageable, Model model) {
        Page<MemberResponseDto> members = memberAdminService.findMemberByCond(memberRequestDto, pageable);
        model.addAttribute("members", members);
        model.addAttribute("cond", memberRequestDto);
        return "/admin/member/memberMain";
    }

    /**
     * 직원 상세화면
     * memberDetail
     * @param memberId
     * @param model
     * @return String
     */
    @GetMapping("/detail")
    public String memberDetail(Long memberId, Model model) {
        MemberResponseDto member = memberAdminService.findMemberById(memberId);
        modelHelper.setMemberModel(model, member);
        return "/admin/member/memberDetail";
    }

    /**
     * 직원 등록화면 폼
     * memberSaveForm
     * @return String
     */
    @GetMapping("/save")
    public String memberSaveForm(Model model) {
        modelHelper.setMemberModel(model, new MemberSaveRequestDto());
        return "/admin/member/memberSaveForm";
    }

    /**
     * 직원 저장
     * memberSave
     * @param memberSaveRequestDto
     * @return String
     */
    @PostMapping("/save")
    public String memberSave(MemberSaveRequestDto memberSaveRequestDto) {
        memberAdminService.saveMember(memberSaveRequestDto);
        return "redirect:/admin/member";
    }

    /**
     * 직원 수정
     * memberModified
     * @param memberModRequestDto
     * @return String
     */
    @PostMapping("/modified")
    public String memberModified(MemberModRequestDto memberModRequestDto) {
        memberAdminService.modifiedMember(memberModRequestDto);
        return "redirect:/admin/member/detail?memberId="+memberModRequestDto.getMemberId();
    }

    /**
     * 직원 재직정보 변경
     * memberChangeStatus
     * @param memberId
     * @param empStatus
     * @return ResponseEntity<String>
     */
    @PostMapping("/remove")
    public ResponseEntity<String> memberChangeStatus(@RequestParam Long memberId, EmpStatus empStatus) {
        memberAdminService.memberChangeStatus(memberId, empStatus);
        return ResponseEntity.ok(MEMBER_UPDATE_SUCCESS);
    }
}

package com.shds.sma.external.api.controller;

import com.shds.sma.external.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.external.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.external.api.dto.member.ApiMemberResponseDto;
import com.shds.sma.external.api.service.ApiMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ApiMemberController {

    private final ApiMemberService apiMemberService;

    @GetMapping("/members")
    public ResponseEntity<Page<ApiMemberResponseDto>> getAllMembers(Pageable pageable) {
        Page<ApiMemberResponseDto> members = apiMemberService.getAllMembers(pageable);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiMemberResponseDto> getMember(@PathVariable Long memberId) {
        ApiMemberResponseDto member = apiMemberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/member")
    public  ResponseEntity<ApiMemberResponseDto> createMember(@RequestBody ApiMemberSaveRequestDto apiMemberSaveRequestDto) {
        ApiMemberResponseDto createdMember = apiMemberService.createMember(apiMemberSaveRequestDto);
        return ResponseEntity.ok(createdMember);
    }

    @PutMapping("/member")
    public ResponseEntity<ApiMemberResponseDto> updateMember(@RequestBody ApiMemberModRequestDto apiMemberModRequestDto) {
        ApiMemberResponseDto updatedMember = apiMemberService.updateMember(apiMemberModRequestDto);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        apiMemberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }


}

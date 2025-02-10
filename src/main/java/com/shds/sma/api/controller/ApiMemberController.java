package com.shds.sma.api.controller;

import com.shds.sma.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.api.dto.member.ApiMemberResponseDto;
import com.shds.sma.api.servie.ApiMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiMemberController {

    private final ApiMemberService apiMemberService;

    @GetMapping("/members")
    public ResponseEntity<Page<ApiMemberResponseDto>> getAllMembers(Pageable pageable) {
        Page<ApiMemberResponseDto> members = apiMemberService.getAllMembers(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiMemberResponseDto> getMember(@PathVariable Long memberId) {
        ApiMemberResponseDto member = apiMemberService.getMember(memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/member")
    public  ResponseEntity<ApiMemberResponseDto> createMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto) {
        ApiMemberResponseDto createdMember = apiMemberService.createMember(apiMemberSaveRequestDto);
        return new ResponseEntity<>(createdMember, HttpStatus.OK);
    }

    @PutMapping("/member")
    public ResponseEntity<ApiMemberResponseDto> updateMember(ApiMemberModRequestDto apiMemberModRequestDto) {
        ApiMemberResponseDto updatedMember = apiMemberService.updateMember(apiMemberModRequestDto);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        apiMemberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

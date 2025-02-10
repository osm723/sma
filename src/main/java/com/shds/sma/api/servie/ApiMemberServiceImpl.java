package com.shds.sma.api.servie;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.admin.repositroy.member.MemberRepository;
import com.shds.sma.api.dto.member.ApiMemberModRequestDto;
import com.shds.sma.api.dto.member.ApiMemberSaveRequestDto;
import com.shds.sma.api.dto.member.ApiMemberResponseDto;
import com.shds.sma.common.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiMemberServiceImpl implements ApiMemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiMemberResponseDto> getAllMembers(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(ApiMemberResponseDto::new);
    }

    @Override
    public ApiMemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        return modelMapper.map(member, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto createMember(ApiMemberSaveRequestDto apiMemberSaveRequestDto) {
        Member createdMember = memberRepository.save(modelMapper.map(apiMemberSaveRequestDto, Member.class));
        return modelMapper.map(createdMember, ApiMemberResponseDto.class);
    }

    @Override
    public ApiMemberResponseDto updateMember(ApiMemberModRequestDto apiMemberModRequestDto) {
        Member updatedMember = memberRepository.findById(apiMemberModRequestDto.getMemberId()).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        updatedMember.memberModified(apiMemberModRequestDto);
        return modelMapper.map(updatedMember, ApiMemberResponseDto.class);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member deletedMember = memberRepository.findById(memberId).orElseThrow(() -> new BizException("존재하지 않는 직원입니다."));
        deletedMember.empStatusChange(deletedMember.getEmpStatus());
    }

}

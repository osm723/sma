package com.shds.sma.api.servie;

import com.shds.sma.api.dto.ip.ApiIpModRequestDto;
import com.shds.sma.api.dto.ip.ApiIpResponseDto;
import com.shds.sma.api.dto.ip.ApiIpSaveRequestDto;
import com.shds.sma.api.dto.notice.ApiNoticeModRequestDto;
import com.shds.sma.common.exception.BizException;
import com.shds.sma.ip.entity.Ip;
import com.shds.sma.ip.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiIpServiceImpl implements ApiIpService {

    private final IpRepository ipRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiIpResponseDto> getAllIps(Pageable pageable) {
        Page<Ip> ips = ipRepository.findAll(pageable);
        return ips.map(ApiIpResponseDto::new);
    }

    @Override
    public ApiIpResponseDto getIp(Long ipId) {
        Ip ip = ipRepository.findById(ipId).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        return modelMapper.map(ip, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto createIp(ApiIpSaveRequestDto apiIpSaveRequestDto) {
        Ip createdIp = ipRepository.save(modelMapper.map(apiIpSaveRequestDto, Ip.class));
        return modelMapper.map(createdIp, ApiIpResponseDto.class);
    }

    @Override
    public ApiIpResponseDto updateIp(ApiIpModRequestDto apiIpModRequestDto) {
        Ip updatedIp = ipRepository.findById(apiIpModRequestDto.getIpId()).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        return modelMapper.map(updatedIp, ApiIpResponseDto.class);
    }

    @Override
    public void deleteIp(Long ipId) {
        Ip deletedIp = ipRepository.findById(ipId).orElseThrow(() -> new BizException("존재하지 않는 IP 입니다."));
        deletedIp.setValidityN();
    }
}

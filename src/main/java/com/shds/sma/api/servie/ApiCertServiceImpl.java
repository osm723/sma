package com.shds.sma.api.servie;

import com.shds.sma.api.dto.cert.ApiCertModRequestDto;
import com.shds.sma.api.dto.cert.ApiCertResponseDto;
import com.shds.sma.api.dto.cert.ApiCertSaveRequestDto;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.repository.CertRepository;
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
public class ApiCertServiceImpl implements ApiCertService {

    private final CertRepository certRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ApiCertResponseDto> getAllCerts(Pageable pageable) {
        Page<Cert> certs = certRepository.findAll(pageable);
        return certs.map(ApiCertResponseDto::new);
    }

    @Override
    public ApiCertResponseDto getCert(Long certId) {
        Cert cert = certRepository.findById(certId).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        return modelMapper.map(cert, ApiCertResponseDto.class);
    }

    @Override
    public ApiCertResponseDto createCert(ApiCertSaveRequestDto apiCertSaveRequestDto) {
        Cert createdCert = certRepository.save(modelMapper.map(apiCertSaveRequestDto, Cert.class));
        return modelMapper.map(createdCert, ApiCertResponseDto.class);
    }
    @Override
    public ApiCertResponseDto updateCert(ApiCertModRequestDto apiCertModRequestDto) {
        Cert updatedCert = certRepository.findById(apiCertModRequestDto.getCertId()).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        updatedCert.certModified(apiCertModRequestDto);
        return modelMapper.map(updatedCert, ApiCertResponseDto.class);
    }
    @Override
    public void deleteCert(Long certId) {
        Cert deletedCert = certRepository.findById(certId).orElseThrow(() -> new BizException("존재하지 않는 인증서 입니다."));
        deletedCert.setValidityN();
    }
}

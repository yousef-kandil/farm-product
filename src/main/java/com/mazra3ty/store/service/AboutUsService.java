package com.mazra3ty.store.service;

import com.mazra3ty.store.DTO.AboutUs.AboutUsRequest;
import com.mazra3ty.store.DTO.AboutUs.AboutUsResponse;
import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.entity.AboutUs;
import com.mazra3ty.store.repository.AboutUsRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AboutUsService {
    private final AboutUsRepository aboutUsRepository;

    public AboutUsResponse createAboutUs(AboutUsRequest request) {

        AboutUs aboutUs = ObjectMapperUtils.map(request, AboutUs.class);
        aboutUsRepository.save(aboutUs);
        return ObjectMapperUtils.map(aboutUs, AboutUsResponse.class);
    }


    public AboutUsResponse getAboutUsById(Long id) {

        AboutUs aboutUs = aboutUsRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.ABOUT_US_NOT_FOUND));

        return ObjectMapperUtils.map(aboutUs, AboutUsResponse.class);
    }


    public AboutUsResponse updateAboutUsById(Long id, AboutUsRequest request) {

        AboutUs aboutUs = aboutUsRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.ABOUT_US_NOT_FOUND));

        ObjectMapperUtils.map(request, aboutUs);
        aboutUsRepository.save(aboutUs);
        return ObjectMapperUtils.map(aboutUs, AboutUsResponse.class);
    }
}

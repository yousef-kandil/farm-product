package com.mazra3ty.store.DTO.AboutUs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AboutUsRequest {

    private String text;

    private String imageUrl;

    private String facebookUrl;

    private String twitterUrl;

    private String linkedInUrl;

    private String instagramUrl;

    private String whatsappNumber;
}

package com.mazra3ty.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ABOUT_US")
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "TEXT", columnDefinition = "TEXT")
    private String text;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "FACEBOOK_URL")
    private String facebookUrl;

    @Column(name = "TWITTER_URL")
    private String twitterUrl;

    @Column(name = "LINKEDIN_URL")
    private String linkedInUrl;

    @Column(name = "INSTAGRAM_URL")
    private String instagramUrl;

    @Column(name = "WHATSAPP_NUMBER")
    private String whatsappNumber;
}

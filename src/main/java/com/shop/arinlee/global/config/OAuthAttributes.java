package com.shop.arinlee.global.config;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name, String email){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameArrtibuteName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameArrtibuteName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(Type.GOGLE)
                .role(Role.ADMIN)
                .build();
    }
}

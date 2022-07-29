package com.shop.arinlee.domain.member.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(length = 50)
    private String address;

    @Column(length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Type memberType;

    public Member update(String memberName){
        this.memberName = memberName;

        return this;
    }


    @Builder
    public Member(Type memberType, String email, String password, String memberName,String address, Role role) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.address = address;
        this.role = role;
    }

}

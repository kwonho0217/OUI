package com.emotionoui.oui.member.controller;

import com.emotionoui.oui.member.dto.req.SearchMemberReq;
import com.emotionoui.oui.member.entity.Member;
import com.emotionoui.oui.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원정보 가져오기
     * @param member
     * @return
     */
    @GetMapping
    public ResponseEntity<Member> getMember(@AuthenticationPrincipal Member member){
        // 만약 탈퇴한 회원이라면 예외처리
        if(member.getIsDeleted()==1){
            System.out.println("탈퇴한 회원입니다.");
        }

        Member member1 = Member.builder()
                .email(member.getEmail())
                .memberId(member.getMemberId())
                .img(member.getImg())
                .regdate(member.getRegdate())
                .nickname(member.getNickname())
            .build();

        System.out.println("getMember: "+member1.getEmail());
        return new ResponseEntity<>(member1, HttpStatus.OK);
    }

    /**
     * 공유 다이어리에 추가할 멤버 검색
     * @param searchMemberReq
     * @return 추가할 memberEmail
     */
    @GetMapping("/search")
    public ResponseEntity<String> searchMember(@AuthenticationPrincipal Member member, @RequestBody SearchMemberReq searchMemberReq){
        String creatorEmail = member.getEmail();
        String searchedMember= memberService.searchMember(creatorEmail, searchMemberReq);
        return ResponseEntity.ok(searchedMember);
    }
}

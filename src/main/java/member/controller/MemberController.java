package member.controller;

import lombok.RequiredArgsConstructor;
import member.dto.MemberRequestDto;
import member.dto.MemberResponseDto;
import member.repository.MemberRepository;
import member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public MemberResponseDto createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.createMembers(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(
            @PathVariable Long memberId
    ) {
        return memberService.getMember(memberId);
    }

    @PutMapping("/members/{memberId}")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.updateMember(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}

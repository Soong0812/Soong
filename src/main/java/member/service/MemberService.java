package member.service;

import lombok.RequiredArgsConstructor;
import member.dto.MemberRequestDto;
import member.dto.MemberResponseDto;
import member.entity.Member;
import member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //CRUD의 C
    @Transactional
    public MemberResponseDto createMembers(MemberRequestDto memberRequest) {
        Member savedMember = memberRepository.save(new Member(memberRequest.getName()));
        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }

    //CRUD의 R (단건 조회)
    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {
        Member memberResponseDto = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );

        return new MemberResponseDto(
                memberResponseDto.getId(),
                memberResponseDto.getName()
        );
    }

    //CRUD의 R (전체 조회)
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> dtoList = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(
                    member.getId(),
                    member.getName()
            );
            dtoList.add(memberResponseDto);
        }
        return dtoList;
    }

    //CRUD의 U (단건)
    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        member.updateName(memberRequestDto.getName());

        return new MemberResponseDto(member.getId(), member.getName());
    }

    //CRUD의 D
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        memberRepository.deleteById(id);
    }
}

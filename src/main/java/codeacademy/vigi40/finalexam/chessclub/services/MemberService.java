package codeacademy.vigi40.finalexam.chessclub.services;

import codeacademy.vigi40.finalexam.chessclub.entities.Member;
import codeacademy.vigi40.finalexam.chessclub.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Page<Member> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void addMember(Member member) {
        this.memberRepository.saveAndFlush(member);
    }

    public void deleteMemberById(Long id) {
        this.memberRepository.deleteById(id);
    }

    public void editMemberById(Long id, Member member) {
        Optional<Member> oldMemberOptional = memberRepository.findById(id);

        if (oldMemberOptional.isEmpty()) {
            return;
        }

        Member oldMember = oldMemberOptional.get();

        if (member.getName() != null && !oldMember.getName().equals(member.getName())) {
            oldMember.setName(member.getName());
        }

        if (member.getLastName() != null && !oldMember.getLastName().equals(member.getLastName())) {
            oldMember.setLastName(member.getLastName());
        }

        if (member.getEmail() != null && !oldMember.getEmail().equals(member.getEmail())) {
            oldMember.setEmail(member.getEmail());
        }

        if (member.getPersonalCode() != null && !oldMember.getPersonalCode().equals(member.getPersonalCode())) {
            oldMember.setPersonalCode(member.getPersonalCode());
        }

        if (member.getStartDate() != null && !oldMember.getStartDate().equals(member.getStartDate())) {
            oldMember.setStartDate(member.getStartDate());
        }

        memberRepository.saveAndFlush(oldMember);
    }
}

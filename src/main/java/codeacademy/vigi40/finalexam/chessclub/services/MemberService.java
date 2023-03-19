package codeacademy.vigi40.finalexam.chessclub.services;

import codeacademy.vigi40.finalexam.chessclub.dto.AddMemberDto;
import codeacademy.vigi40.finalexam.chessclub.entities.Member;
import codeacademy.vigi40.finalexam.chessclub.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static codeacademy.vigi40.finalexam.chessclub.converters.MemberConverter.validateEmail;
import static codeacademy.vigi40.finalexam.chessclub.converters.MemberConverter.validatePersonalCode;
import static codeacademy.vigi40.finalexam.chessclub.converters.MemberConverter.validateStartDate;

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
        return memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with given ID is not found"));
    }

    public void addMember(Member member) {
        this.memberRepository.saveAndFlush(member);
    }

    public void deleteMemberById(Long id) {
        Member member = getMemberById(id);
        this.memberRepository.delete(member);
    }

    public void editMemberById(Long id, AddMemberDto memberDto) {
        Member oldMember = getMemberById(id);

        if (memberDto.getName() != null && !oldMember.getName().equals(memberDto.getName())) {
            oldMember.setName(memberDto.getName());
        }

        if (memberDto.getLastName() != null && !oldMember.getLastName().equals(memberDto.getLastName())) {
            oldMember.setLastName(memberDto.getLastName());
        }

        if (memberDto.getEmail() != null && !oldMember.getEmail().equals(memberDto.getEmail())) {
            if (!validateEmail(memberDto.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not valid");
            }

            oldMember.setEmail(memberDto.getEmail());
        }

        if (memberDto.getPersonalCode() != null && !oldMember.getPersonalCode().equals(memberDto.getPersonalCode())) {
            if (!validatePersonalCode(memberDto.getPersonalCode())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personal code is not valid");
            }

            oldMember.setPersonalCode(memberDto.getPersonalCode());
        }

        if (memberDto.getStartDate() != null && !oldMember.getStartDate().equals(memberDto.getStartDate())) {
            if (!validateStartDate(memberDto.getStartDate())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date is not valid");
            }

            oldMember.setStartDate(memberDto.getStartDate());
        }

        memberRepository.saveAndFlush(oldMember);
    }
}

package codeacademy.vigi40.finalexam.chessclub.converters;

import codeacademy.vigi40.finalexam.chessclub.dto.AddMemberDto;
import codeacademy.vigi40.finalexam.chessclub.dto.MemberDto;
import codeacademy.vigi40.finalexam.chessclub.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberConverter {

    public static Member convertAddMemberDtoToEntity(AddMemberDto memberDto){
        Member member = null;
        if(memberDto != null){
            member = new Member();
            member.setName(memberDto.getName());
            member.setLastName(memberDto.getLastName());
            member.setEmail(memberDto.getEmail());
        }
        return member;
    }

    public static MemberDto convertMemberEntityToDto(Member member) {
        MemberDto memberDto = null;
        if (member != null) {
            memberDto = new MemberDto();
            memberDto.setId(member.getId());
            memberDto.setName(member.getName());
            memberDto.setLastName(member.getLastName());
            memberDto.setEmail(member.getEmail());
        }
        return memberDto;
    }

    public static List<MemberDto> convertMemberEntityListToDto(List<Member> members) {
        List<MemberDto> mamberDtoList = null;
        for (Member m : members) {
            if (mamberDtoList == null) {
                mamberDtoList = new ArrayList<>();
            }
            mamberDtoList.add(convertMemberEntityToDto(m));
        }
        return mamberDtoList;
    }
}

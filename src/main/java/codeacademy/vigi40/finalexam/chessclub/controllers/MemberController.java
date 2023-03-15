package codeacademy.vigi40.finalexam.chessclub.controllers;

import codeacademy.vigi40.finalexam.chessclub.converters.MemberConverter;
import codeacademy.vigi40.finalexam.chessclub.dto.AddMemberDto;
import codeacademy.vigi40.finalexam.chessclub.dto.MemberDto;
import codeacademy.vigi40.finalexam.chessclub.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chess-club")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<MemberDto> getMembers(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return MemberConverter.convertMemberEntityListToDto(this.memberService.getMembers(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public AddMemberDto getMemberById(@PathVariable Long id) {
        return MemberConverter.convertMemberEntityToAddMemberDto(this.memberService.getMemberById(id));
    }

    @PostMapping
    public void addMember(@RequestBody AddMemberDto memberDto) {
        this.memberService.addMember(MemberConverter.convertAddMemberDtoToEntity(memberDto));
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id){
        this.memberService.deleteMemberById(id);
    }

    @PatchMapping("/{id}")
    public void editMemberById(@PathVariable Long id, @RequestBody AddMemberDto memberDto){
        this.memberService.editMemberById(id, MemberConverter.convertAddMemberDtoToEntity(memberDto));
    }
}

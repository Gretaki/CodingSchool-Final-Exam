package codeacademy.vigi40.finalexam.chessclub.controllers;

import codeacademy.vigi40.finalexam.chessclub.converters.MemberConverter;
import codeacademy.vigi40.finalexam.chessclub.dto.AddMemberDto;
import codeacademy.vigi40.finalexam.chessclub.dto.MemberDto;
import codeacademy.vigi40.finalexam.chessclub.entities.Member;
import codeacademy.vigi40.finalexam.chessclub.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/chess-club")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<Member> memberPage = this.memberService.getMembers(paging);
        List<MemberDto> members = MemberConverter.convertMemberEntityListToDto(memberPage);
        Map<String, Object> response = new HashMap<>();
        response.put("members", members);
        response.put("currentPage", memberPage.getNumber());
        response.put("totalItems", memberPage.getTotalElements());
        response.put("totalPages", memberPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public void deleteMemberById(@PathVariable Long id) {
        this.memberService.deleteMemberById(id);
    }

    @PatchMapping("/{id}")
    public void editMemberById(@PathVariable Long id, @RequestBody AddMemberDto memberDto) {
        this.memberService.editMemberById(id, MemberConverter.convertAddMemberDtoToEntity(memberDto));
    }
}

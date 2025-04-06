package com.example.WigellAPIDemo.controllers;

import com.example.WigellAPIDemo.entities.Member;
import com.example.WigellAPIDemo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MemberController
{
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> getAllMembers()
    {
        return memberService.getAllMembers();
    }

    @GetMapping("/member/{id}")
    @ResponseBody
    public Member getMemberById(@PathVariable Long id)
    {
        return memberService.getMemberById(id);
    }

    @PutMapping("/updatemember")
    @ResponseBody
    public ResponseEntity<Member> updateMember(@RequestBody Member member)
    {
        Member updatedMember = memberService.updateMember(member);

        return ResponseEntity.ok(updatedMember);
    }

    @PostMapping("/addmember")
    @ResponseBody
    public ResponseEntity<Member> addMember(@RequestBody Member member)
    {
        Member createdMember = memberService.addMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @DeleteMapping("/deletemember/{id}")
    @ResponseBody
    public ResponseEntity<Member> deleteMemberRest(@PathVariable Long id)
    {
        memberService.deleteMember(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/deletemember")
    public String showAllMembers(Model model)
    {
        model.addAttribute("members", memberService.getAllMembers());
        return "deleteMembers";
    }

    @GetMapping("/deletememberbyid/{id}")
    public String deleteMemberWeb(@PathVariable Long id)
    {
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }
}

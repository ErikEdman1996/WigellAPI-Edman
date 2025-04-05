package com.example.WigellAPIDemo.controllers;

import com.example.WigellAPIDemo.entities.Member;
import com.example.WigellAPIDemo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Member updateMember(@RequestBody Member member)
    {
        return memberService.updateMember(member);
    }

    @PostMapping("/addmember")
    @ResponseBody
    public Member addMember(@RequestBody Member member)
    {
        return memberService.addMember(member);
    }

    @DeleteMapping("/deletemember/{id}")
    @ResponseBody
    public void deleteMemberRest(@PathVariable Long id)
    {
        memberService.deleteMember(id);
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

package com.example.WigellAPIDemo.services;

import com.example.WigellAPIDemo.entities.Member;
import com.example.WigellAPIDemo.exceptions.ResourceNotFoundException;
import com.example.WigellAPIDemo.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements MemberServiceInterface
{

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers()
    {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id)
    {
        Optional<Member> member = memberRepository.findById(id);

        if(member.isPresent())
        {
            return member.get();
        }

        throw new ResourceNotFoundException("Member", "id", id);
    }

    @Override
    public Member updateMember(Member member)
    {
        if(memberRepository.findById(member.getId()).isPresent())
        {
            return memberRepository.save(member);
        }

        throw new ResourceNotFoundException("Member", "id", member.getId());
    }

    @Override
    public Member addMember(Member member)
    {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id)
    {
        if(memberRepository.findById(id).isPresent())
        {
            memberRepository.deleteById(id);
            System.out.println("Member with id " + id + " deleted successfully");
        }
        else
        {
            System.out.println("Member with id " + id + " not found");
            throw new ResourceNotFoundException("Member", "id", id);
        }
    }
}

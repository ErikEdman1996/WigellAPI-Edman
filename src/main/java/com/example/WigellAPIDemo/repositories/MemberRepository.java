package com.example.WigellAPIDemo.repositories;

import com.example.WigellAPIDemo.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    //CRUD ingår
}

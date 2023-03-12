package codeacademy.vigi40.finalexam.chessclub.repositories;

import codeacademy.vigi40.finalexam.chessclub.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

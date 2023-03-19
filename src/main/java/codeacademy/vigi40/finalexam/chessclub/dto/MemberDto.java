package codeacademy.vigi40.finalexam.chessclub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private Gender gender;

    private int age;

    private float monthsOfExperience;

    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE,
        OTHER
    }
}

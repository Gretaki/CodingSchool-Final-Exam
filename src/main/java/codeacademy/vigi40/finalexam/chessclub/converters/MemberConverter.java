package codeacademy.vigi40.finalexam.chessclub.converters;

import codeacademy.vigi40.finalexam.chessclub.dto.AddMemberDto;
import codeacademy.vigi40.finalexam.chessclub.dto.MemberDto;
import codeacademy.vigi40.finalexam.chessclub.entities.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class MemberConverter {

    public static Member convertAddMemberDtoToEntity(AddMemberDto memberDto) {
        Member member = null;
        if (memberDto != null) {
            if (!validateEmail(memberDto.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not valid");
            }

            if (!validatePersonalCode(memberDto.getPersonalCode())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personal code is not valid");
            }

            if (!validateStartDate(memberDto.getStartDate())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date is not valid");
            }

            member = new Member();
            member.setName(memberDto.getName());
            member.setLastName(memberDto.getLastName());
            member.setEmail(memberDto.getEmail());
            member.setPersonalCode(memberDto.getPersonalCode());
            member.setStartDate(memberDto.getStartDate());
        }
        return member;
    }

    private static boolean validateEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regex)
                .matcher(email)
                .matches();
    }

    private static boolean validatePersonalCode(String personalCode) {
        return personalCode.length() == 11 &&
                Long.parseLong(personalCode) > 0 &&
                Long.parseLong(personalCode.substring(0, 1)) < 7 &&
                Long.parseLong(personalCode.substring(3, 5)) <= 12 &&
                Long.parseLong(personalCode.substring(5, 7)) <= 31;
    }

    private static boolean validateStartDate(LocalDate startDate) {
        return LocalDate.now().isAfter(startDate);
    }

    public static MemberDto convertMemberEntityToDto(Member member) {
        MemberDto memberDto = null;
        if (member != null) {
            memberDto = new MemberDto();
            memberDto.setId(member.getId());
            memberDto.setName(member.getName());
            memberDto.setLastName(member.getLastName());
            memberDto.setEmail(member.getEmail());
            memberDto.setGender(calculateGender(member.getPersonalCode()));
            memberDto.setAge(calculateAge(member.getPersonalCode()));
            memberDto.setMonthsOfExperience(calculateExperience(member.getStartDate()));
        }
        return memberDto;
    }

    public static AddMemberDto convertMemberEntityToAddMemberDto(Member member) {
        AddMemberDto memberDto = null;
        if (member != null) {
            memberDto = new AddMemberDto();
            memberDto.setName(member.getName());
            memberDto.setLastName(member.getLastName());
            memberDto.setEmail(member.getEmail());
            memberDto.setPersonalCode(member.getPersonalCode());
            memberDto.setStartDate(member.getStartDate());
        }
        return memberDto;
    }

    private static MemberDto.Gender calculateGender(String personalCode) {
        int firstDigit = Character.getNumericValue(personalCode.charAt(0));
        MemberDto.Gender gender;
        if (firstDigit % 2 == 0) {
            gender = MemberDto.Gender.FEMALE;
        } else {
            gender = MemberDto.Gender.MALE;
        }
        return gender;
    }

    private static int calculateAge(String personalCode) {
        int firstDigit = Character.getNumericValue(personalCode.charAt(0));
        String birthDate = personalCode.substring(1, 7);

        switch (firstDigit) {
            case 1, 2 -> birthDate = "18" + birthDate;
            case 3, 4 -> birthDate = "19" + birthDate;
            default -> birthDate = "20" + birthDate;
        }

        LocalDate formattedBirthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        return Period.between(formattedBirthDate, LocalDate.now()).getYears();
    }

    private static float calculateExperience(LocalDate startDate) {
        Period period = Period.between(startDate, LocalDate.now());
        return period.getYears() * 12 + period.getMonths();
    }
}

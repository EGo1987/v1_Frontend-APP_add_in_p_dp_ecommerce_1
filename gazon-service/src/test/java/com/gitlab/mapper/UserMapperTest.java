package com.gitlab.mapper;

import com.gitlab.dto.*;
import com.gitlab.model.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testcontainers.shaded.org.hamcrest.Matchers.containsInAnyOrder;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void should_map_user_to_Dto() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_ADMIN"));

        Set<BankCard> bankCardSet = new HashSet<>();
        bankCardSet.add(new BankCard(1L, "0000000000000000", LocalDate.now(), 777));

        Set<PersonalAddress> personalAddresses = new HashSet<>();
        personalAddresses.add(new PersonalAddress(
                "apartment",
                "floor",
                "entrance",
                "doorCode",
                "postCode"));

        Passport passport = new Passport(
                1L,
                Passport.Citizenship.RUSSIA,
                "user",
                "user",
                "patronym",
                LocalDate.now(),
                LocalDate.now(),
                "098765",
                "issuer",
                "issuerN");

        User user = new User(1L,
                "user",
                "user",
                "answer",
                "question",
                "user",
                "user",
                LocalDate.now(),
                User.Gender.MALE,
                "89007777777",
                passport,
                LocalDate.now(),
                bankCardSet,
                personalAddresses,
                roleSet);

        UserDto actualResult = mapper.toDto(user);

        assertNotNull(actualResult);
        assertEquals(user.getId(), actualResult.getId());
        assertEquals(user.getEmail(), actualResult.getEmail());
        assertEquals(user.getPassword(), actualResult.getPassword());
        assertEquals(user.getSecurityQuestion(), actualResult.getSecurityQuestion());
        assertEquals(user.getAnswerQuestion(), actualResult.getAnswerQuestion());
        assertEquals(user.getFirstName(), actualResult.getFirstName());
        assertEquals(user.getLastName(), actualResult.getLastName());
        assertEquals(user.getBirthDate(), actualResult.getBirthDate());
        assertEquals(user.getGender(), actualResult.getGender());
        assertEquals(user.getPhoneNumber(), actualResult.getPhoneNumber());

        assertEquals(user.getCreateDate(), LocalDate.now());

        containsInAnyOrder(user.getPersonalAddress(), actualResult.getPersonalAddress());
        containsInAnyOrder(user.getBankCards(), actualResult.getBankCards());
        containsInAnyOrder(user.getRoles(), actualResult.getRoles());

        assertEquals(user.getPassport(), actualResult.getPassport());

    }

    @Test
    void should_map_userDto_to_Entity() {
        Set<RoleDto> roleSet = new HashSet<>();
        roleSet.add(new RoleDto(1L, "ROLE_ADMIN"));

        Set<BankCardDto> bankCard = new HashSet<>();
        bankCard.add(new BankCardDto(
                1L,
                "1111222233334444",
                LocalDate.now(),
                423
        ));

        Set<PersonalAddressDto> personalAddress = new HashSet<>();
        personalAddress.add(new PersonalAddressDto(
                1L,
                "address",
                "directions",
                "apartment",
                "floor",
                "entrance",
                "doorCode",
                "postCode"));

        PassportDto passport = new PassportDto(
                1L,
                Passport.Citizenship.RUSSIA,
                "user",
                "user",
                "patronym",
                LocalDate.now(),
                LocalDate.now(),
                "098765",
                "issuer",
                "issuerN");

        UserDto userDto = new UserDto(
                1L,
                "mail@mail.ru",
                "user",
                "answer",
                "question",
                "user",
                "user",
                LocalDate.now(),
                User.Gender.MALE,
                "89007777777",
                passport,
                personalAddress,
                bankCard,
                roleSet);

        User actualResult = mapper.toEntity(userDto);

        assertNotNull(actualResult);
        assertEquals(userDto.getId(), actualResult.getId());
        assertEquals(userDto.getEmail(), actualResult.getEmail());
        assertEquals(userDto.getPassword(), actualResult.getPassword());
        assertEquals(userDto.getSecurityQuestion(), actualResult.getSecurityQuestion());
        assertEquals(userDto.getAnswerQuestion(), actualResult.getAnswerQuestion());
        assertEquals(userDto.getFirstName(), actualResult.getFirstName());
        assertEquals(userDto.getLastName(), actualResult.getLastName());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getGender(), actualResult.getGender());
        assertEquals(userDto.getPhoneNumber(), actualResult.getPhoneNumber());

        containsInAnyOrder(userDto.getPersonalAddress(), actualResult.getPersonalAddress());
        containsInAnyOrder(userDto.getBankCards(), actualResult.getBankCards());
        containsInAnyOrder(userDto.getRoles(), actualResult.getRoles());

        assertEquals(userDto.getPassport(), actualResult.getPassport());

    }
}

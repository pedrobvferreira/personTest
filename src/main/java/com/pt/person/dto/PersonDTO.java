package com.pt.person.dto;

import lombok.Data;
import com.pt.person.model.Person;

@Data
public class PersonDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String date;

	public static PersonDTO toDTO(Person obj) {
        PersonDTO dto = new PersonDTO();
        dto.setId(obj.getId());
        dto.setFirstName(obj.getFirstName());
        dto.setLastName(obj.getLastName());
        dto.setPhoneNumber(obj.getPhoneNumber());
        dto.setDate(obj.getDate());
        return dto;
    }

    public static Person toEntity(PersonDTO dto) {
        Person obj = new Person();
        obj.setId(dto.getId());
        obj.setFirstName(dto.getFirstName());
        obj.setLastName(dto.getLastName());
        obj.setPhoneNumber(dto.getPhoneNumber());
        obj.setDate(dto.getDate());
        return obj;
    }

}
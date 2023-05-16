package com.test.security.dto;

import com.test.security.utils.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentRequest {

    private String className;
    private String teacherFirstName;
    private String teacherLastName;
    private Integer page; // page starting from 0
    private Integer size; // number for each page

    public String getFullName() {
        // Check if both teacherFirstName and teacherLastName are null
        if (teacherFirstName == null && teacherLastName == null) {
            return null;
        }
        // Check if one of them is null and the other is not
        if((teacherFirstName != null && teacherLastName ==null) || (teacherFirstName == null && teacherLastName !=null)){
            throw new CustomException("FirstName and LastName must be complete or both null");
        }
        // Concatenate the values, remove leading and trailing spaces, convert to lowercase, and replace multiple spaces with a single space
        return (teacherFirstName+teacherLastName).trim().toLowerCase().replaceAll("\\s+", "");
    }

    public int getPage(){
        if(page == null){
            throw new CustomException("Page number must be provided");
        }
        return page;
    }

    public int getSize() {
        if (size == null) {
            throw new CustomException("Page size must be provided");
        }
        return size;
    }
}

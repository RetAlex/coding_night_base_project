package ua.edu.ukma.e_request.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class FondRoom {
    private Long id;
    private String name;
    private String building;
    private String username;

    public FondRoom(){}
}

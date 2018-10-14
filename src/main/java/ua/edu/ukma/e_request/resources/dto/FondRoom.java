package ua.edu.ukma.e_request.resources.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FondRoom {
    private Long id;
    private String name;
    private String building;
    private String username;
}

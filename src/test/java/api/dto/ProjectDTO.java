package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private LeaderDTO leader;

    public ProjectDTO(String id) {
        this.id = id;
    }

    public ProjectDTO(String name, String shortName, String description, LeaderDTO leader) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.leader = leader;
    }
}

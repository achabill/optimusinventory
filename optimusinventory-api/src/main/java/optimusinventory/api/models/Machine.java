package optimusinventory.api.models;

import org.springframework.data.annotation.Id;

import javax.validation.Valid;

public class Machine {
    @Id
    private String id;
    @Valid
    private String name;
    private String dateCreated;
    @Valid
    private MachineType machineType;
    private String description;

    public Machine() {
    }

    public Machine(String name, String dateCreated, MachineType machineType, String description) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.machineType = machineType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

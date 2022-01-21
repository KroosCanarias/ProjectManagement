package es.ulpgc.dis;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager implements Worker{
    private String firstName;
    private String lastName;

    private List<Developer> managedTeam;

    private int workLoad;

    public ProjectManager() {
        this.managedTeam = new ArrayList<Developer>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad=workLoad;
    }
    public int getWorkLoad(){
        return workLoad;
    }

    public List<Developer> getManagedTeam() {
        return managedTeam;
    }

    public void addManagedDeveloper(Developer developer) {
        this.managedTeam.add(developer);
    }
}

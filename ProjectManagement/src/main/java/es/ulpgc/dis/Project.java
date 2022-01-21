package es.ulpgc.dis;

import java.util.*;

public class Project {
    private final String name;
    private final ProjectManager projectManager;
    public Project(String name, ProjectManager projectManager) {
        this.name = name;
        this.projectManager = projectManager;
    }
    public String getName(){
        return name;
    }
    public ProjectManager getProjectManager(){
        return projectManager;
    }

}

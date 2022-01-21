package es.ulpgc.dis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectWorkLoad {
    public static Map<String, Integer> getWorkLoadProjects(List <Project> projectList) {
        Map<String, Integer> workLoadProjects = new HashMap<String, Integer>();

        for (Project project: projectList) {
            int workLoad = 0;
            ProjectManager manager= project.getProjectManager();
            workLoad += manager.getWorkLoad();
            List<Developer> managedTeam =manager.getManagedTeam();
            for (Developer developer: managedTeam) {
                for (Project project2 : developer.projects) {
                    if (project.getName().equals(project2.getName())) {
                        workLoad += developer.getWorkLoad();
                    }
                }
            }
            workLoadProjects.put(project.getName(), workLoad);
        }
        return workLoadProjects;
    }
}

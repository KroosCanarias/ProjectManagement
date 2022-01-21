package es.ulpgc.dis;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ProjectTest {
    ProjectManager projectManager0;
    ProjectManager projectManager1;
    Project projectA;
    Project projectB;
    Developer developer0;
    Developer developer1;
    Developer developer2;
    Developer developer3;
    Developer developer4;

    List<Project> projects;
    @Before
    public void init(){
        projectManager0 = new ProjectManager();
        projectManager1 = new ProjectManager();
        projectA = new Project("Project A", projectManager0);
        projectB = new Project("Project B", projectManager1);
        developer0 = new Developer("Jessica", "Jones");
        developer1 = new Developer("Charlie", "Taylor");
        developer2 = new Developer("William", "Brown");
        developer3 = new Developer("Sophie", "Wilson");
        developer4= new Developer("Emily", "Yhomas");
        projects = new ArrayList<Project>();
    }
    @Test
    public void empty_projects(){
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        assertTrue(workLoadProjects.isEmpty());
    }
    @Test
    public void added_one_project(){
        projects.add(projectA);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        assertEquals(workLoadProjects.size(),1);
    }
    @Test
    public void added_two_project(){
        projects.add(projectA);
        projects.add(projectB);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        assertEquals(workLoadProjects.size(),2);
    }
    @Test
    public void workLoad_one_project_empty_developer(){
        projectManager0.setWorkLoad( 1800); // full time in one project
        projectManager0.setFirstName("James");
        projectManager0.setLastName("Johnson");
        projects.add(projectA);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        int workLoad= workLoadProjects.get(projectA.getName());
        assertEquals(workLoad,1800);
    }
    @Test
    public void workLoad_one_project_one_developer(){
        projectManager0.setWorkLoad( 1800); // full time in one project
        projectManager0.setFirstName("James");
        projectManager0.setLastName("Johnson");
        developer1.setWorkLoad( 1800); // full time in one project
        developer1.projects.add(projectA);
        projectManager0.addManagedDeveloper(developer1);
        projects.add(projectA);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        int workLoad= workLoadProjects.get(projectA.getName());
        assertEquals(workLoad,3600);
    }
    @Test
    public void workLoad_one_project_two_developer(){
        projectManager0.setWorkLoad( 1800); // full time in one project
        projectManager0.setFirstName("James");
        projectManager0.setLastName("Johnson");
        developer1.setWorkLoad( 1800); // full time in one project
        developer1.projects.add(projectA);
        developer2.setWorkLoad( 1800); // full time in one project
        developer2.projects.add(projectA);
        projectManager0.addManagedDeveloper(developer1);
        projectManager0.addManagedDeveloper(developer2);
        projects.add(projectA);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        int workLoad= workLoadProjects.get(projectA.getName());
        assertEquals(workLoad,5400);
    }
    @Test
    public void workLoad_two_project_empty_developer(){
        projectManager0.setWorkLoad( 1800); // full time in one project
        projectManager0.setFirstName("James");
        projectManager0.setLastName("Johnson");
        projectManager1.setWorkLoad( (int) (1800.0 * 0.5)); // part time 50% in one project
        projectManager1.setFirstName("Isabella");
        projectManager1.setLastName("Smith");
        projects.add(projectA);
        projects.add(projectB);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        int workLoadProjectA= workLoadProjects.get(projectA.getName());
        int workLoadProjectB=workLoadProjects.get(projectB.getName());
        assertEquals(workLoadProjectA+workLoadProjectB,2700);
    }
    @Test
    public void workLoad_two_project_with_developers(){
        developer0.setWorkLoad( (int) (1800.0 * 0.75 * 0.5)); // part time 75% in two projects
        developer0.projects.add(projectA);
        developer0.projects.add(projectB);
        developer1.setWorkLoad( 1800); // full time in one project
        developer1.projects.add(projectA);
        developer2.setWorkLoad((int) (1800.0 * 0.5)); // full time in two projects
        developer2.projects.add(projectA);
        developer2.projects.add(projectB);
        developer3.setWorkLoad((int) (1800.0 * 0.5)); // part time 50%
        developer3.projects.add(projectB);
        Developer developer4 = new Developer("Emily", "Yhomas");
        developer4.setWorkLoad( (int) (1800.0 * 0.5)); // part time 50% in one project
        developer4.projects.add(projectB);
        projectManager0.setWorkLoad( 1800); // full time in one project
        projectManager0.setFirstName("James");
        projectManager0.setLastName("Johnson");
        projectManager0.addManagedDeveloper(developer0);
        projectManager0.addManagedDeveloper(developer1);
        projectManager0.addManagedDeveloper(developer2);
        projectManager1.setWorkLoad( (int) (1800.0 * 0.5)); // part time 50% in one project
        projectManager1.setFirstName("Isabella");
        projectManager1.setLastName("Smith");
        projectManager1.addManagedDeveloper(developer0);
        projectManager1.addManagedDeveloper(developer2);
        projectManager1.addManagedDeveloper(developer3);
        projectManager1.addManagedDeveloper(developer4);
        projects.add(projectB);
        projects.add(projectA);
        Map<String, Integer> workLoadProjects = ProjectWorkLoad.getWorkLoadProjects(projects);
        int workLoadProjectA= workLoadProjects.get(projectA.getName());
        int workLoadProjectB=workLoadProjects.get(projectB.getName());
        assertEquals(workLoadProjectA+workLoadProjectB,9450);
    }
}
package constraints;

import java.util.*;
import java.util.Map.Entry;

import conflict.Conflict;
import course.Course;

public class CourseConstraints{


    public static HashMap<String,HashMap<String,String>> constraintsMap = new HashMap<String,HashMap<String,String>>();
    
    private static CourseConstraints singleton = new CourseConstraints();
    
    private CourseConstraints() {
        
    }
    public static CourseConstraints getInstance() {
        return singleton;
    }

    public void addConstraint(String lhs,String type, String rhs) {
        if(!constraintsMap.containsKey(lhs)) {
            constraintsMap.put(lhs, new HashMap<String,String>());
        }
        constraintsMap.get(lhs).put(rhs,type);
    }
    public boolean hasConstraints(Course course) {
        return constraintsMap.containsKey(course.getName());
    }
    public boolean coursesHaveConflict(Course lhsCourse, Course rhsCourse) {
        if(constraintsMap.containsKey(lhsCourse.getName())) {
            if(constraintsMap.get(lhsCourse.getName()).get(rhsCourse.getName()).equals("<")) {
                if(lhsCourse.getSemTaken() >= rhsCourse.getSemTaken()) {
                    return true;
                }
            }else {
                if(lhsCourse.getSemTaken() > rhsCourse.getSemTaken()) {
                    return true;
                }
            }
        }
        return false;
    }
    public HashMap<String,String> getCourseConflicts(Course course){
        return constraintsMap.get(course.getName());       
    }
    public Collection<String> getCoursesWithConflicts(){
        return constraintsMap.keySet();
    }

    public void print() {
        System.out.println("lhsConstraints: ");
        for(Entry<String, HashMap<String,String>> ents : constraintsMap.entrySet()) {
            
            System.out.print(ents.getKey() + " ");
            
            for(Entry<String,String> entries : ents.getValue().entrySet()) {
                System.out.print(entries.getValue() + " " + entries.getKey() + " ");
            }
            System.out.println();
        }
    }

    }



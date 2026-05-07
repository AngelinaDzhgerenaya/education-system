package org.example.educationsystem.routes;

public class EducationRoutes {

    public final static String ROOT = "/api";

    public final static String TEACHERS = ROOT + "/teachers";

    public final static String SCHOOLCLASSES = ROOT + "/classes";

    public final static String SCHEDULE = SCHOOLCLASSES + "/{id}" + "/schedule";



    public final static  String INIT = ROOT +"/init";

}

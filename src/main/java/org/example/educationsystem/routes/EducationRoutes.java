package org.example.educationsystem.routes;

public class EducationRoutes {

    public final static String ROOT = "/api";

    public final static String TEACHERS = ROOT + "/teachers";

    public final static String TEACHERSBYID = TEACHERS + "/{id}";

    public final static String SCHOOLCLASSES = ROOT + "/classes";

    public final static String SCHOOLCLASSESBYID = SCHOOLCLASSES + "/{id}";

    public final static String SCHEDULECLASS = SCHOOLCLASSES + "/{id}" + "/schedule";

    public final static String SCHEDULETECHER = TEACHERS + "/{id}" + "/schedule";

    public final static String SCHEDULECRETE = ROOT + "/schedule";

    public final static String SCHEDULEBYID = SCHEDULECRETE + "/{id}";


    public final static  String INIT = ROOT +"/init";

}

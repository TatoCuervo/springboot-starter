package com.tatocuervo.springbootstarter.routes;

public class Routes {
    public static final String API_VERSION = "api/v1.0";

    public static final String AUTHENTICATE = API_VERSION + "/authenticate";

    public static final String USER = API_VERSION + "/user";

    public static final String TOPICS = API_VERSION + "/topics";

    public static final String COURSES = API_VERSION + "/topic/{topicId}/courses";

    public static final String LESSONS = API_VERSION + "/topic/{topicId}/course/{courseId}/lessons";
}

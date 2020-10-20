package com.tatocuervo.springbootstarter.lesson;

import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Lessons")
@RestController
@RequestMapping(path = Routes.LESSONS)
public class LessonController {
}

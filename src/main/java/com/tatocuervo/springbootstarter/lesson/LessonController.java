package com.tatocuervo.springbootstarter.lesson;

import com.tatocuervo.springbootstarter.routes.Routes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.LESSONS)
public class LessonController {
}

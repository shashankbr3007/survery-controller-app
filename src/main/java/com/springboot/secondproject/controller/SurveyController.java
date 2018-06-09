package com.springboot.secondproject.controller;

import com.springboot.secondproject.model.Question;
import com.springboot.secondproject.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("surveys/{surveyId}/questions")
    public List<Question> getQuestionsforSurvey(@PathVariable String surveyId){
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping("surveys/{surveyId}/questions/{questionId}")
    public Question retriveQuestion(@PathVariable String surveyId,@PathVariable String questionId){
        return surveyService.retrieveQuestion(surveyId,questionId);
    }


    @PostMapping("surveys/{surveyId}/questions")
    public ResponseEntity<Void> addQuestionstoSurvey(@PathVariable String surveyId,@RequestBody Question newquestion){

        Question question = surveyService.addQuestion(surveyId,newquestion);

        if(question == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}

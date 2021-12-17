package com.shelest.antlrApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private ExpressionEvaluator expressionEvaluator;

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    public ModelAndView evaluate(@RequestParam("expressionInput") String expression) {
        ModelAndView modelAndView = new ModelAndView("index");
        double res = expressionEvaluator.evaluate(expression);
        modelAndView.addObject("result", Double.toString(res));
        modelAndView.addObject("expressionInput", expression);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("error", ex.getClass()+": "+ex.getMessage());
        return modelAndView;
    }

}

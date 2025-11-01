package com.example.api.api_automation.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.api.api_automation.stepdefinitions",
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class TestRunner { }

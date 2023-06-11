#!/usr/bin/env groovy
import groovy.transform.Field
import com.demo.*

def CT(pipelineContext, Closure setup) {
    CT test = new CT()
    test.init(pipelineContext)
    setup.delegate = test
    setup.resolveStrategy = Closure.DELEGATE_ONLY
    setup()
    return test.generate()
}
package com.demo

def init(pipelineContext) {
    this.pipelineContext = pipelineContext
}

def generate() {
    pipelineContext.echo("pipelineContext")
    return {
        echo 'generate start'
        script {
            sh 'ls -la'
        }
        echo 'generate end'
    }
}
return this

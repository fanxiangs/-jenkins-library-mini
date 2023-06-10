package com.demo

class Utilities {
    static def exec(script, args) {       
        script.timestamps {
            def result = script.sh(returnStatus: true, script: 'ls -al')
            script.echo "${script.env.HOME}  ${args} result: ${result}"
            script.pwd
        }
    }
}
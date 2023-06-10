package com.demo

class Utilities {
    static def mvn(script, args) {
        script.sh "ls  -al"
        script.echo "${script.env.HOME}  ${args}"
    }
}
package com.demo

class Utilities {
    static def exec(script, args) {       
        script.timestamps {
            script.sh "ls  -al"
            script.echo "${script.env.HOME}  ${args}"
            script.help
        }
    }
}
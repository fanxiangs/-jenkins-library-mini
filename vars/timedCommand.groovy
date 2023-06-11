def call(Closure commands) {
    timestamps {
        commands() 
    }
}

def timedCommand14(body) {
    // collect assignments passed in into our mapping
    def settings = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = settings
    body()

    // now, time the commands
    timestamps {
        cmdOutput = sh (script:"${settings.cmd}", returnStdout:true).trim()
    }
    echo cmdOutput
    echo "${settings}"
    writeFile file: "${settings.logFilePath}", text: "${cmdOutput}"
}


package org.demo

def call(Closure command) {
    timestamps {
        script {
            command()
        }
    }
}

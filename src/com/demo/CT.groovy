package com.demo

def generate() {
    return {
        echo 'generate'
        script {
            sh 'ls -la'
        }
    }

}

def getVersion(versionFilePath="VERSION") {
    def versionFile = readFile file: versionFilePath
    def versionLines = versionFile.split('\n')

    // 解析版本信息
    def versionMap = [:]
    versionLines.each { line ->
        if (line =~ /^(\w+)\s*=\s*(.+)$/) {
            versionMap[it[1]] = it[2]
        }
    }
    return versionMap

def updateVersion() {
    def file = new File(filePath)
    file.write(content)
}



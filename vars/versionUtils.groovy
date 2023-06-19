import org.yaml.snakeyaml.Yaml

def readVersionYaml(versionYamlFilePath = 'VERSION') {
    def versionYamlContent = readFile(versionYamlFilePath)
    def versionMap = new Yaml().load(versionYamlContent)
    return versionMap
}

def updatePreRelease(versionYamlFilePath = 'VERSION') {
    def versionMap = readVersionYaml(versionYamlFilePath)
    
    def preRelease = versionMap.VERSION_PRERELEASE ?: 0
    
    // Increment the preRelease value by 1
    preRelease++
    
    // Update the preRelease value in the versionMap
    versionMap.VERSION_PRERELEASE = preRelease
    
    // Write the updated versionMap back to the VERSION file
    def yaml = new Yaml()
    def updatedVersionYamlContent = yaml.dump(versionMap)
    writeFile file: versionYamlFilePath, text: updatedVersionYamlContent
    
    return preRelease
}

def getVersion(versionYamlFilePath = 'VERSION') {
    def versionMap = readVersionYaml(versionYamlFilePath)
    return "${versionMap.VERSION_MAJOR}.${versionMap.VERSION_MINOR}.${versionMap.VERSION_RELEASE}.${versionMap.VERSION_PRERELEASE}"
}

return [readVersionYaml: readVersionYaml, updatePreRelease: updatePreRelease, getVersion: getVersion]

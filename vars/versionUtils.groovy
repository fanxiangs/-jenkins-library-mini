import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions

def readVersionYaml(versionYamlFilePath = 'VERSION') {
    def versionYamlContent = readFile(versionYamlFilePath)
    def yaml = new Yaml()
    def versionMap = yaml.load(versionYamlContent)
    return versionMap
}

def updatePreRelease(versionYamlFilePath = 'VERSION') {
    def versionMap = readVersionYaml(versionYamlFilePath)
    
    def preRelease = versionMap.VERSION_PRERELEASE ?: 0
    
    // Increment the preRelease value by 1
    preRelease++
    
    // Update the preRelease value in the versionMap
    versionMap.VERSION_PRERELEASE = preRelease
    
    // Write the updated versionMap back to the VERSION file in YAML format
    writeVersionMapToYaml(versionMap, versionYamlFilePath)
    
    return preRelease
}

def getVersion(versionYamlFilePath = 'VERSION') {
    def versionMap = readVersionYaml(versionYamlFilePath)
    def version = "${versionMap.VERSION_MAJOR}.${versionMap.VERSION_MINOR}.${versionMap.VERSION_RELEASE}"
    if (versionMap.VERSION_PRERELEASE) {
        version += ".${versionMap.VERSION_PRERELEASE}"
    }
    return version
}

@NonCPS
def writeVersionMapToYaml(versionMap, versionYamlFilePath) {
    def dumperOptions = new DumperOptions()
    dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK)
    def yaml = new Yaml(dumperOptions)
    def updatedVersionYamlContent = yaml.dump(versionMap)
    writeFile file: versionYamlFilePath, text: updatedVersionYamlContent
}

return [
    readVersionYaml: readVersionYaml,
    updatePreRelease: updatePreRelease,
    getVersion: getVersion
]

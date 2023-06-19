import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml

class VersionUtils {
    String versionYamlFilePath = 'VERSION'
    
    def readVersionYaml() {
        def versionYamlContent = readFile(versionYamlFilePath)
        def versionMap = new Yaml().load(versionYamlContent)
        return versionMap
    }
    
    def updatePreRelease() {
        def versionMap = readVersionYaml()
        
        def preRelease = versionMap.VERSION_PRERELEASE ?: 0
        
        // Increment the preRelease value by 1
        preRelease++
        
        // Update the preRelease value in the versionMap
        versionMap.VERSION_PRERELEASE = preRelease
        
        // Write the updated versionMap back to the VERSION file
        def dumperOptions = new DumperOptions()
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK)
        def yaml = new Yaml(dumperOptions)
        def updatedVersionYamlContent = yaml.dump(versionMap)
        writeFile file: versionYamlFilePath, text: updatedVersionYamlContent
        
        return preRelease
    }
}

return new VersionUtils()

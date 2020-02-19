# Dataweave Migrant CLI
This tool is intended to help with the DW 1.x - DW 2.x scripts migration effort.

## Build and Package
Execute: `mvn clean package`

## Run
`java -jar dataweave-migrant-cli-1.0.0-SNAPSHOT.jar [options]`

Option | Description | Mandatory | Default
------------ | ------------ | ------------ | ------------
-files | List of .dwl file paths separated by ';' | Yes if not using -filesDir | -
-filesDir | Root directory to search .dwl files to migrate | Yes, if not using -files | -
-outputDir | Output directory to place the migrated scripts | Yes | -
-help | Shows the help | No | -

## Example
`java -jar dataweave-migrant-cli-1.0.0-SNAPSHOT.jar -filesDir myUser/myDwOldScripts/ -outputDir myUser/output`

WARNING: Use a different output destination, if you use the same input and output directory, your scripts will be overridden

## Test Files

Use Cases / Script tests located in: `src/test/resources/dw`

## Final Note
Enjoy and provide feedback / contribute :)

pushd "%~dp0"
rmdir /s PowerBI.Api\Source\
C:\ProgramData\chocolatey\bin\AutoRest.exe -CodeGenerator Java -Modeler Swagger -Input swagger.json -Namespace microsoft.powerbi.api.v1 -output PowerBI.Api\Source\ -name PowerBIClient -AddCredentials
popd
pause

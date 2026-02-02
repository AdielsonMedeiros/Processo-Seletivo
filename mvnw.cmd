@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.2.0
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE__=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {$scriptDir='%~dp0telerik'; $env:PSModulePath=''; $LASTEXITCODE=0; [Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12; $result=Invoke-Expression '& ''%~dp0.mvn\wrapper\MavenWrapperDownloader.ps1''' ; Split-Path -Leaf '%~dp0.mvn\wrapper\maven-wrapper.jar'; exit $LASTEXITCODE }" 2^>NUL`) DO @(
  IF NOT "%%A"=="" (
    IF "%%A"=="calling" (SET __MVNW_CMD__=%%B) ELSE (
      IF "%%A"=="error" (SET __MVNW_ERROR__=%%B) ELSE (
        IF "%%B"=="" (SET __MVNW_CMD__=%%A)
      )
    )
  )
)
@SET PSModulePath=%__MVNW_PSMODULEP_SAVE__%

@IF NOT "%__MVNW_CMD__%"=="" GOTO :execute
@ECHO.
@ECHO Error: Could not find or download Apache Maven wrapper
@IF NOT "%__MVNW_ERROR__%"=="" @ECHO Reason: %__MVNW_ERROR__%
@ECHO.
@ECHO Please download the maven-wrapper.jar from https://maven.apache.org/wrapper/
@ECHO and place it in the .mvn/wrapper directory.
@ECHO.

:execute
@SET MVNW_REPOURL=https://repo.maven.apache.org/maven2
@IF NOT "%MVNW_REPOURL%"=="" @SET MVNW_REPOURL=%MVNW_REPOURL%
@SET WRAPPER_JAR="%~dp0.mvn\wrapper\maven-wrapper.jar"

@FOR /F "tokens=*" %%G IN ('dir /b /s "%~dp0.mvn\wrapper\maven-wrapper.jar" 2^>nul') DO @SET WRAPPER_JAR="%%G"

@IF EXIST %WRAPPER_JAR% GOTO :runWrapper

@ECHO Downloading Maven Wrapper...
@powershell -Command "&{[Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%MVNW_REPOURL%/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar' -OutFile '%~dp0.mvn\wrapper\maven-wrapper.jar'}"

:runWrapper
@SET MAVEN_PROJECTBASEDIR=%~dp0
@SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

"%JAVA_HOME%\bin\java.exe" ^
  %MAVEN_OPTS% ^
  -classpath %WRAPPER_JAR% ^
  "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" ^
  %WRAPPER_LAUNCHER% %*
@IF ERRORLEVEL 1 GOTO :error
@GOTO :end

:error
@IF NOT "%OS%"=="Windows_NT" @EXIT 1
@IF "%COMSPEC%"=="" (SET COMSPEC=cmd.exe)
@IF "%SHELL%"=="" @SET "SHELL=%COMSPEC%"
@EXIT /B 1

:end

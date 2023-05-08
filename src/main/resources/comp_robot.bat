@echo off

set "javaClass=%1"
set "classpath=libs\robocode.jar;%ROBOCODE_HOME%\robots"

javac -verbose -encoding UTF-8 ^
  -classpath "%classpath%" "%javaClass%" ^
  -d "%ROBOCODE_HOME%\robots\"

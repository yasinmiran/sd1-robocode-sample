#!/usr/bin/env zsh

javaClass="$1"
classpath="libs/robocode.jar:$ROBOCODE_HOME/robots"

# create the directory for your robot.
# mkdir -p "$ROBOCODE_HOME/robots/zen"

javac -verbose -encoding UTF-8 \
  -classpath "$classpath" "$javaClass" \
  -d "$ROBOCODE_HOME/robots/"

#!/bin/bash
# Execute Sonar Scanner analysis for this project.

WORKING_DIR=.
VERSION=3.3.0.1492
EXECUTABLE=/opt/sonarqube/sonar-scanner-$VERSION-linux/bin/sonar-scanner

cd "$WORKING_DIR"
"$EXECUTABLE"

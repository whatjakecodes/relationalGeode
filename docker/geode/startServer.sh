#!/usr/bin/env sh

sleep 30 # gives the locator to startup
/geode/bin/gfsh run --file=/scripts/startServer.gfsh

while true; do sleep 600; done
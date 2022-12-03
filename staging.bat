@echo off 
title "Staging"
git add . 
git commit -m "Staging"
git push -f origin  main
@echo off
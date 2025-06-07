@echo off
echo Pulling latest code at %time%
cd /d "%~dp0"
git pull origin develop
echo Pull completed at %time% 
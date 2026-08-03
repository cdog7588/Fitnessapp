@echo off
if not exist "%ProgramFiles(x86)%\Inno Setup 6\ISCC.exe" (
  echo Inno Setup is not installed. Install it from https://jrsoftware.org/isinfo.php
  exit /b 1
)
"%ProgramFiles(x86)%\Inno Setup 6\ISCC.exe" "%~dp0FitnessApp.iss"

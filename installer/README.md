# FitnessApp Windows Installer

This folder contains the files needed to build a Windows installer for FitnessApp.

## Files
- `FitnessApp.iss` — Inno Setup script for the installer
- `build-installer.bat` — helper script to compile the installer

## Build
1. Install Inno Setup 6 from https://jrsoftware.org/isinfo.php
2. Run `build-installer.bat`
3. The installer will be created as `FitnessAppSetup.exe`

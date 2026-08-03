[Setup]
AppName=FitnessApp
AppVersion=1.0.0
DefaultDirName={autopf}\FitnessApp
DefaultGroupName=FitnessApp
OutputDir=.
OutputBaseFilename=FitnessAppSetup
Compression=lzma2
SolidCompression=yes
PrivilegesRequired=admin

[Files]
Source: "..\scripts\launch-fitnessapp.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\FitnessApp.cmd"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\launch-fitnessapp.ps1"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\start-backend.ps1"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\start-frontend.ps1"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\open-app-window.ps1"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\open-logs.ps1"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\scripts\FitnessApp-Logs.cmd"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{group}\FitnessApp"; Filename: "{app}\FitnessApp.cmd"; IconFilename: "{sys}\shell32.dll"; IconIndex: 44
Name: "{commondesktop}\FitnessApp"; Filename: "{app}\FitnessApp.cmd"; IconFilename: "{sys}\shell32.dll"; IconIndex: 44
Name: "{group}\FitnessApp Logs"; Filename: "{app}\FitnessApp-Logs.cmd"; IconFilename: "{sys}\shell32.dll"; IconIndex: 70

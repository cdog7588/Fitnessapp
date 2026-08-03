$ErrorActionPreference = 'Stop'
$root = 'C:\dev\fitness-intelligence-backend\fitnessapp'
$installerDir = Join-Path $root 'installer'
$buildDir = Join-Path $installerDir 'build'
$appsDir = Join-Path $buildDir 'Apps'
$shortcutDir = Join-Path $buildDir 'Shortcuts'

New-Item -ItemType Directory -Force -Path $buildDir | Out-Null
New-Item -ItemType Directory -Force -Path $appsDir | Out-Null
New-Item -ItemType Directory -Force -Path $shortcutDir | Out-Null

Copy-Item (Join-Path $root 'scripts\launch-fitnessapp.bat') (Join-Path $appsDir 'launch-fitnessapp.bat') -Force
Copy-Item (Join-Path $root 'scripts\FitnessApp.cmd') (Join-Path $appsDir 'FitnessApp.cmd') -Force

$wsShell = New-Object -ComObject WScript.Shell
$shortcut = $wsShell.CreateShortcut((Join-Path $shortcutDir 'FitnessApp.lnk'))
$shortcut.TargetPath = (Join-Path $appsDir 'FitnessApp.cmd')
$shortcut.WorkingDirectory = $appsDir
$shortcut.IconLocation = 'C:\Windows\System32\imageres.dll, 69'
$shortcut.Save()

Write-Host "Installer assets created at $buildDir"

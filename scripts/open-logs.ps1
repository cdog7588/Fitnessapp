$ErrorActionPreference = 'Stop'
$logRoot = Join-Path $env:LOCALAPPDATA 'FitnessApp\logs'
New-Item -ItemType Directory -Force -Path $logRoot | Out-Null
Start-Process explorer.exe $logRoot

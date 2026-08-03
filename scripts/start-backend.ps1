param(
    [string]$LogFile = ''
)

$ErrorActionPreference = 'Stop'

$repo = Split-Path -Parent $PSScriptRoot
Set-Location $repo

$existing = Get-NetTCPConnection -LocalPort 8100 -State Listen -ErrorAction SilentlyContinue
if ($existing) {
	$message = 'Backend already running on http://localhost:8100. Skipping duplicate start.'
	Write-Host $message
	if ($LogFile) { "[$(Get-Date -Format s)] $message" | Out-File -FilePath $LogFile -Encoding utf8 -Append }
	return
}

if ($LogFile) {
    "[$(Get-Date -Format s)] Starting backend" | Out-File -FilePath $LogFile -Encoding utf8 -Append
    .\mvnw.cmd spring-boot:run *>> $LogFile
}
else {
    .\mvnw.cmd spring-boot:run
}

param(
	[string]$LogFile = ''
)

$ErrorActionPreference = 'Stop'

$root = Split-Path -Parent $PSScriptRoot
$repo = Join-Path $root 'frontend'
Set-Location $repo
$node = 'C:\Program Files\nodejs\node.exe'
$npm = 'C:\Program Files\nodejs\node_modules\npm\bin\npm-cli.js'

$existing = Get-NetTCPConnection -LocalPort 3000 -State Listen -ErrorAction SilentlyContinue
if ($existing) {
	$message = 'Frontend already running on http://localhost:3000. Skipping duplicate start.'
	Write-Host $message
	if ($LogFile) { "[$(Get-Date -Format s)] $message" | Out-File -FilePath $LogFile -Encoding utf8 -Append }
	return
}

if (-not (Test-Path (Join-Path $repo 'node_modules'))) {
	if ($LogFile) {
		"[$(Get-Date -Format s)] Installing frontend dependencies" | Out-File -FilePath $LogFile -Encoding utf8 -Append
		& $node $npm install *>> $LogFile
	}
	else {
		& $node $npm install
	}
}

if ($LogFile) {
	"[$(Get-Date -Format s)] Starting frontend dev server" | Out-File -FilePath $LogFile -Encoding utf8 -Append
	& $node $npm run dev -- --host 0.0.0.0 *>> $LogFile
}
else {
	& $node $npm run dev -- --host 0.0.0.0
}

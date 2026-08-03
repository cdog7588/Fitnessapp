$ErrorActionPreference = 'Stop'

$base = $PSScriptRoot
$backend = Join-Path $base 'start-backend.ps1'
$frontend = Join-Path $base 'start-frontend.ps1'
$window = Join-Path $base 'open-app-window.ps1'

$logRoot = Join-Path $env:LOCALAPPDATA 'FitnessApp\logs'
New-Item -ItemType Directory -Force -Path $logRoot | Out-Null

$backendLog = Join-Path $logRoot 'backend.log'
$frontendLog = Join-Path $logRoot 'frontend.log'
$launcherLog = Join-Path $logRoot 'launcher.log'

"[$(Get-Date -Format s)] Launch started" | Out-File -FilePath $launcherLog -Encoding utf8 -Append

# Single splash/loading window while background services start.
Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing

$form = New-Object System.Windows.Forms.Form
$form.Text = 'FitnessApp'
$form.StartPosition = 'CenterScreen'
$form.Size = New-Object System.Drawing.Size(440, 180)
$form.FormBorderStyle = 'FixedDialog'
$form.MaximizeBox = $false
$form.MinimizeBox = $false
$form.TopMost = $true

$label = New-Object System.Windows.Forms.Label
$label.Text = 'Starting FitnessApp...'
$label.AutoSize = $true
$label.Location = New-Object System.Drawing.Point(24, 24)

$status = New-Object System.Windows.Forms.Label
$status.Text = 'Booting backend and frontend services'
$status.AutoSize = $true
$status.Location = New-Object System.Drawing.Point(24, 56)

$progress = New-Object System.Windows.Forms.ProgressBar
$progress.Style = 'Marquee'
$progress.MarqueeAnimationSpeed = 25
$progress.Size = New-Object System.Drawing.Size(380, 20)
$progress.Location = New-Object System.Drawing.Point(24, 92)

$form.Controls.Add($label)
$form.Controls.Add($status)
$form.Controls.Add($progress)
$form.Show()
$form.Refresh()

try {
	Start-Process powershell -WindowStyle Hidden -ArgumentList "-NoProfile -ExecutionPolicy Bypass -File `"$backend`" -LogFile `"$backendLog`""
	Start-Sleep -Seconds 2
	Start-Process powershell -WindowStyle Hidden -ArgumentList "-NoProfile -ExecutionPolicy Bypass -File `"$frontend`" -LogFile `"$frontendLog`""

	$timeoutSeconds = 90
	$ready = $false
	for ($i = 0; $i -lt $timeoutSeconds; $i++) {
		$backendReady = Get-NetTCPConnection -LocalPort 8100 -State Listen -ErrorAction SilentlyContinue
		$frontendReady = Get-NetTCPConnection -LocalPort 3000 -State Listen -ErrorAction SilentlyContinue
		if ($backendReady -and $frontendReady) {
			$ready = $true
			break
		}
		Start-Sleep -Seconds 1
		[System.Windows.Forms.Application]::DoEvents()
	}

	if (-not $ready) {
		throw "Startup timed out. Check logs in $logRoot"
	}

	"[$(Get-Date -Format s)] Services ready" | Out-File -FilePath $launcherLog -Encoding utf8 -Append
	& $window
}
catch {
	"[$(Get-Date -Format s)] Launch failed: $($_.Exception.Message)" | Out-File -FilePath $launcherLog -Encoding utf8 -Append
	[System.Windows.Forms.MessageBox]::Show(
		"FitnessApp failed to start.`n`nCheck logs here:`n$logRoot",
		'FitnessApp Startup Error',
		[System.Windows.Forms.MessageBoxButtons]::OK,
		[System.Windows.Forms.MessageBoxIcon]::Error
	) | Out-Null
}
finally {
	$form.Close()
	$form.Dispose()
}

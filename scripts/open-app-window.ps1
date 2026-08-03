$ErrorActionPreference = 'Stop'
$url = 'http://localhost:3000'

$edgeCandidates = @(
    "${env:ProgramFiles(x86)}\Microsoft\Edge\Application\msedge.exe",
    "${env:ProgramFiles}\Microsoft\Edge\Application\msedge.exe"
)

foreach ($edge in $edgeCandidates) {
    if (Test-Path $edge) {
        Start-Process -FilePath $edge -ArgumentList "--app=$url", "--new-window"
        return
    }
}

$chromeCandidates = @(
    "${env:ProgramFiles(x86)}\Google\Chrome\Application\chrome.exe",
    "${env:ProgramFiles}\Google\Chrome\Application\chrome.exe"
)

foreach ($chrome in $chromeCandidates) {
    if (Test-Path $chrome) {
        Start-Process -FilePath $chrome -ArgumentList "--app=$url", "--new-window"
        return
    }
}

Start-Process $url
